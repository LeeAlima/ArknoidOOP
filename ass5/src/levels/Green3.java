package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import backrounds.Level3Backround;
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
public class Green3 implements LevelInformation {
    private int width;
    private int hight;
    private Level3Backround levelBackround;

    /**
     * this is the constructor of Green3.
     * @param width
     *            - as the level screen width
     * @param hight
     *            - as the level screen hight
     */
    public Green3(int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.levelBackround = new Level3Backround(width, hight);
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        // adding velocity objects to the list
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(-5 * Math.pow(-1, i), -2);
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.levelBackround;
    }

    @Override
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> listOfBlocks = new ArrayList<>();
        int numberOfBlocks = 11;
        int x = 0;
        int y = 0;
        int number;
        // the external loop runs on the lines of blocks
        for (int i = 0; i < 5; i++) {
            number = 1;
            java.awt.Color randCo = getColor(i);
            numberOfBlocks--;
            // the internal loop runs on the blocks of each line
            for (int j = 0; j < numberOfBlocks; j++) {
                // creating a new block
                if (i == 0) {
                    number = 2;
                }
                Block b = new Block(new Rectangle(new Point(0.9 * this.width + x, 0.15 * this.hight + y),
                        this.width / 15, this.hight / 24), randCo, number);
                x -= this.width / 15;
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
            return new Color(204, 204, 255);
        }
        if (i == 1) {
            return new Color(204, 229, 255);
        }
        if (i == 2) {
            return new Color(204, 255, 255);
        }
        if (i == 3) {
            return new Color(204, 255, 229);
        }
        if (i == 4) {
            return new Color(204, 255, 204);
        }
        if (i == 5) {
            return new Color(229, 255, 204);
        }
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}