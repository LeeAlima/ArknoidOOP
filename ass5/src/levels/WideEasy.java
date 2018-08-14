package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import backrounds.Level2Backround;
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
public class WideEasy implements LevelInformation {
    private int width;
    private int hight;
    private Level2Backround levelBackround;

    /**
     * this is the constructor of WideEasy.
     * @param width
     *            - as the level screen width
     * @param hight
     *            - as the level screen hight
     */
    public WideEasy(int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.levelBackround = new Level2Backround(width, hight);
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(-5 + i, -2);
            listOfVelocity.add(v);
        }
        // returning the list
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return (int) (this.width / 1.5);
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.levelBackround;
    }

    @Override
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> listOfBlocks = new ArrayList<>();
        int x = 0;
        // creating blocks in 1 line
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            java.awt.Color randCo = getColor(i);
            Block b = new Block(new Rectangle(new Point(this.width / 16 + 1 + x, 0.4 * this.hight), this.width / 16,
                    this.hight / 18), randCo, 1);
            listOfBlocks.add(b);
            x += this.width / 16;
        }
        // returning the list of blocks
        return listOfBlocks;
    }

    /**
     * this method creates new colors.
     * @param i
     *            - as a number
     * @return a new color
     */
    public java.awt.Color getColor(int i) {
        if (i == 0 || i == 13) {
            return new Color(255, 153, 153);
        }
        if (i == 1 || i == 12) {
            return new Color(255, 204, 153);
        }
        if (i == 2 || i == 11) {
            return new Color(255, 255, 153);
        }
        if (i == 3 || i == 10) {
            return new Color(204, 255, 153);
        }
        if (i == 4 || i == 9) {
            return new Color(153, 255, 153);
        }
        if (i == 5 || i == 8) {
            return new Color(153, 255, 204);
        }
        if (i == 6 || i == 7) {
            return new Color(153, 204, 255);
        }
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}