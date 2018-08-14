package levels;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blocks.Block;
import blocks.BlocksFromSymbolsFactory;
import collision.Velocity;
import game.BlocksDefinitionReader;
import game.ColorsParser;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class BuildALevel {

    private List<String> level = new ArrayList<>();
    private TreeMap<String, String> map;
    private BlocksFromSymbolsFactory factory;

    /**
     * this is the constructor of BuildALevel.
     * @param level - as a list of string
     */
    public BuildALevel(List<String> level) {
        this.level = level;
        this.map = new TreeMap<String, String>();
        // call the splitLevelDetails method
        splitLevelDetails();
    }

    /**
     * this method seperate the data of the level in a way to make it easier to
     * build a level.
     * @return LevelInformation - as a level
     */
    public LevelInformation seperateLeveData() {
        try {
            // put the value of "level_name" in levelName
            String levelName = map.get("level_name");
            // create a list of velocities
            List<Velocity> velocities = getVelocitiesForLevel();
            // find the speed of the paddle
            int paddleSpeed = Integer.parseInt(map.get("paddle_speed"));
            // find the width of the paddle
            int paddleWidth = Integer.parseInt(map.get("paddle_width"));
            // find the number of blocks
            int numOfBlocks = Integer.parseInt(map.get("num_blocks"));
            // creating a list of blocks
            List<Block> blockList = createBlocks();
            String backround = map.get("background");
            // create a level based on the Level Data
            LevelData oneLevel = new LevelData(levelName, velocities, paddleSpeed, paddleWidth, numOfBlocks, blockList);
            if (backround.contains("image")) {
                backround = backround.replaceAll("[()]", "");
                backround = backround.substring(5);
                // create an InputStream object
                InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream(backround);
                Image image = null;
                // try to load the image
                try {
                    image = ImageIO.read(imageIS);
                } catch (IOException e) {
                    e.printStackTrace();
                } // set the backround
                oneLevel.setBackroundImage(image);
                imageIS.close();
                return oneLevel;
            } else {
                oneLevel.setBackroundColor(new ColorsParser().colorFromString(backround));
                return oneLevel;
            }
        } catch (Exception e) {
            messageToUser();
        }
        return null;
    }

    /**
     * this method created a list of velocities.
     * @return a list of Velocity.
     */
    public List<Velocity> getVelocitiesForLevel() {
        List<Velocity> listOfVelocities = new ArrayList<>();
            String velocities = this.map.get("ball_velocities");
            // create an array of velocities
            String[] listOfVelocitiesString = velocities.split(" ");
            int size = listOfVelocitiesString.length;
            // run over all of the possible velocities
            for (int i = 0; i < size; i++) {
                // in every velocity split it to speed and angel
                String[] oneVel = listOfVelocitiesString[i].split(",");
                Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(oneVel[0]), Integer.parseInt(oneVel[1]));
                // add the veloctiy to the list of velocities
                listOfVelocities.add(v);
            }
            return listOfVelocities;
    }

    /**
     * this method is activated in case of missing information. it prints an
     * error message to the user and close the program.
     */
    public void messageToUser() {
        // show a message dialog - the message is an error message.
        JOptionPane.showMessageDialog(new JFrame(),
                "You are missing some parameters in the level definitions, Please check it", "Error!",
                JOptionPane.ERROR_MESSAGE);
        // exit the program
        System.exit(0);
    }

    /**
     * this is a private method which is used in order to split the data.
     */
    private void splitLevelDetails() {
        final int zero = 0;
        final int one = 1;
        // goes over all of the levels
        for (int i = 0; i < level.size(); i++) {
            // if the level.get(i) contains ":"
            if (level.get(i).contains(":")) {
                // split the line
                String[] keyAndVal = level.get(i).trim().split(":");
                // put the key and the value in the map
                this.map.put(keyAndVal[zero], keyAndVal[one].trim());
            } else {
                break;
            }
        }
    }

    /**
     * this method creates a list of blocks.
     * @return a new list of blocks
     */
    private List<Block> createBlocks() {
        ArrayList<String> listOfBlocksAndSpacers = new ArrayList<String>();
        boolean buffer = false;
        for (int i = 0; i < level.size(); i++) {
            // if it starts with END_BLOCKS
            if (level.get(i).startsWith("END_BLOCKS")) {
                buffer = false;
            } // if the buffer is true
            if (buffer) {
                listOfBlocksAndSpacers.add(level.get(i));
            } // if it starts with START_BLOCKS
            if (level.get(i).startsWith("START_BLOCKS")) {
                buffer = true;
            } else if (level.get(i).startsWith("END_BLOCKS")) {
                buffer = false;
            }
        }
        // find the x position where it all starts
        int startX = Integer.parseInt(this.map.get("blocks_start_x"));
        int xForSave = startX;
        // find the y position where it all starts
        int startY = Integer.parseInt(this.map.get("blocks_start_y"));
        List<Block> listOfBlocks = new ArrayList<>();
        String[] s;
        setBlocks();
        // go over the list of blocks of spacers
        for (int i = 0; i < listOfBlocksAndSpacers.size(); i++) {
            // split it with empty lines
            s = listOfBlocksAndSpacers.get(i).split("");
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals("")) {
                    continue;
                } // if it is a block symbol
                if (this.factory.isBlockSymbol(s[j])) {
                    // add to listOfBlocks a block
                    listOfBlocks.add(this.factory.getBlock(s[j], startX, startY));
                    // continue to the next block with the next location
                    startX += this.factory.getBlock(s[j], startX, startY).getCollisionRectangle().getWidth();
                } else if (this.factory.isSpaceSymbol(s[j])) { // move following
                                                               // spacers
                    startX += this.factory.getSpaceWidth(s[j]);
                }
            }
            startX = xForSave;
            startY += Integer.parseInt(this.map.get("row_height"));
        }
        // put the blocks in a new blocks list and return it
        List<Block> listOfBlocksCopy = new ArrayList<>();
        for (int z = 0; z < listOfBlocks.size(); z++) {
            listOfBlocksCopy.add(listOfBlocks.get(z).copyBlock());
        }
        return listOfBlocksCopy;
    }

    /**
     * this method set the blocks.
     */
    private void setBlocks() {
        // load the class
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        // create an InputStream object
        InputStream inputStream = cl.getResourceAsStream(this.map.get("block_definitions"));
        // initialize this factory with a factory
        this.factory = BlocksDefinitionReader.fromReader(new InputStreamReader(inputStream));
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
