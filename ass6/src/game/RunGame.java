package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.ArrangeLevelsSets;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class RunGame {
    private String str;

    /**
     * this is the constructor for RunGame.
     * @param str - as a string
     */
    public RunGame(String str) {
        this.str = str;
    }

    /**
     * this method runs the program.
     */
    public void startGame() {
        GUI gui = new GUI("arknoid", 800, 600);
        final AnimationRunner runner = new AnimationRunner(gui);
        final KeyboardSensor keyborad = runner.getGui().getKeyboardSensor();
        // creating an InputStreamReader object
        InputStreamReader inputStreamForProject = new InputStreamReader(
                ClassLoader.getSystemClassLoader().getResourceAsStream(str));
        // creating a LineNumberReader to read all of the lines in the file
        LineNumberReader numberReader = new LineNumberReader(inputStreamForProject);
        String lineData;
        // creating and initialize an HighScoresTable object
        HighScoresTable highScoresTable = null;
        try {
            highScoresTable = HighScoresTable.loadFromFile((new File("highScores.txt")));
        } catch (IOException e) {
            System.out.println("something went wrong while loading the file");
            e.printStackTrace();
        } // if the table is empty - create one!
        if (highScoresTable.getHighScores().isEmpty()) {
            highScoresTable = new HighScoresTable(5);
        } // create a new table similiraty to the previous one that was created
        final HighScoresTable scoresTable = highScoresTable;
        // creating 3 lists
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> definitions = new ArrayList<>();
        // creating a buffer with the numberReader stream
        BufferedReader buffer = new BufferedReader(numberReader);
        // try to read lines with the buffer and split the lines to keys,
        // messages and definitions
        try {
            while ((lineData = buffer.readLine()) != null) {
                if (lineData.equals("")) {
                    continue;
                } else if (lineData.contains(":")) {
                    String[] toSplit = lineData.split(":");
                    keys.add(toSplit[0]);
                    messages.add(toSplit[1]);
                } else {
                    definitions.add(lineData);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file");
            e.printStackTrace();
        } finally {
            try { // close the buffer
                numberReader.close();
                buffer.close();
            } catch (IOException e) {
                System.err.println("Could not close reader");
                e.printStackTrace();
            }
        }
        try {
            inputStreamForProject.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // create a sub menu
        Menu<Task<Void>> subMenu = new MenuAnimation<>("Arkanoid", keyborad, runner);
        // if the file is llegal ( for every key there is a message and a
        // definitions)
        if (keys.size() == messages.size() && keys.size() == definitions.size()) {
            // go over the keys
            for (int i = 0; i < keys.size(); i++) {
                InputStreamReader inputStream = new InputStreamReader(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(definitions.get(i)));
                // create list of levels based on the definitions txt and add
                // them to the sub menu
                List<LevelInformation> listToRunOver = new ArrangeLevelsSets().fromReader(inputStream);
                subMenu.addSelection(keys.get(i), messages.get(i), new Task<Void>() {
                    // run the levels
                    public Void run() {
                        GameFlow flow = new GameFlow(800, 600, scoresTable, gui, keyborad);
                        flow.runLevels(listToRunOver);
                        return null;
                    }
                });
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // create a main menu
        Menu<Task<Void>> mainMenu = new MenuAnimation<>("Arkanoid", keyborad, runner);
        mainMenu.addSubMenu("s", "Start game", subMenu); // s - as the key to
                                                         // open the sub menu
        mainMenu.addSelection("h", "High Scores", new Task<Void>() {
            // show the highScoreAnimation with the scores table
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyborad, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(scoresTable)));
                return null;
            }
        });
        mainMenu.addSelection("q", "Exit", new Task<Void>() {
            // q to quit game
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        while (true) {
            // run the main menu
            runner.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            // as long the task is not null - run it.
            if (task != null) {
                task.run();
                mainMenu.setStop(false);
            }
        }

    }
}
