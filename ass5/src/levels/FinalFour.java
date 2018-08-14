package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import backrounds.Level4Backround;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class FinalFour implements LevelInformation {
    private int width;
    private int hight;
    private Level4Backround levelBackround;

    /**
     * this is the constructor of FinalFour.
     * @param width
     *            - as the level screen width
     * @param hight
     *            - as the level screen hight
     */
    public FinalFour(int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.levelBackround = new Level4Backround(width, hight);
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        // adding velocities to the list
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(-5 + i * 5, -4);
            listOfVelocity.add(v);
        }
        // returning the list
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return (int) (this.width / 5);
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.levelBackround;
    }

    @Override
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> listOfBlocks = new ArrayList<>();
        int numberOfBlocks = 15;
        int x = 0;
        int y = 0;
        int number;
        // the external loop runs on the lines of blocks
        for (int i = 0; i < 7; i++) {
            number = 1;
            java.awt.Color randCo = getColor(i);
            // the internal loop runs on the blocks of each line
            for (int j = 0; j < numberOfBlocks; j++) {
                // creating a new block
                Block b = new Block(new Rectangle(new Point(0.88 * this.width + x, 0.15 * this.hight + y),
                        this.width / 17, this.hight / 24), randCo, number);
                x -= this.width / 17;
                listOfBlocks.add(b);
            }
            x = 0;
            y += this.hight / 24;
        }
        // returning the list
        return listOfBlocks;
    }

    /**
     * this method creates new colors.
     * @param i
     *            - as a number
     * @return a new color
     */
    public java.awt.Color getColor(int i) {
        if (i == 0) {
            return new Color(64, 64, 64);
        }
        if (i == 1) {
            return new Color(96, 96, 96);
        }
        if (i == 2) {
            return new Color(128, 128, 128);
        }
        if (i == 3) {
            return new Color(160, 160, 160);
        }
        if (i == 4) {
            return new Color(192, 192, 192);
        }
        if (i == 5) {
            return new Color(224, 224, 224);
        }
        if (i == 6) {
            return new Color(255, 255, 255);
        }
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}