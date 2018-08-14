package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import backrounds.Level1Backround;
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
public class DirectHit implements LevelInformation {
    private int width;
    private int hight;
    private Level1Backround levelBackround;

    /**
     * this is the constructor of DirectHit.
     * @param width
     *            - as the level screen width
     * @param hight
     *            - as the level screen hight
     */
    public DirectHit(int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.levelBackround = new Level1Backround(width, hight);
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        Velocity v = new Velocity(0, -5);
        listOfVelocity.add(v);
        // returning the list with 1 velocity
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return this.width / 7;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.levelBackround;
    }

    @Override
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> listOfBlocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(this.width / 2 - this.width / 30, 0.3 * this.hight),
                this.width / 15, this.hight / 15), Color.RED, 1);
        listOfBlocks.add(b);
        // returning the list with 1 block
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}