package levels;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import blocks.Block;
import collision.Velocity;
import sprite.Backround;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class LevelData implements LevelInformation {

    private String nameOfLevel;
    private int numOfBalls;
    private List<Velocity> listOfVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private int numOfBlocks;
    private List<Block> listOfBlocks;
    private Image image = null;
    private Color color = null;

    /**
     * this is the constructor for LevelData.
     * @param nameOfLevel - as the name of the level
     * @param listOfVelocities - as the list of velocities
     * @param paddleSpeed - as the paddle speed
     * @param paddleWidth - as the paddle width
     * @param numOfBlocks - as the number of blocks
     * @param listOfBlocks - as the list of blocks
     */
    public LevelData(String nameOfLevel, List<Velocity> listOfVelocities, int paddleSpeed, int paddleWidth,
            int numOfBlocks, List<Block> listOfBlocks) {
        this.nameOfLevel = nameOfLevel;
        this.listOfVelocities = listOfVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numOfBlocks = numOfBlocks;
        this.listOfBlocks = listOfBlocks;
        this.numOfBalls = listOfVelocities.size();
    }

    /**
     * this method set the backround with image.
     * @param imageI - as in Image object
     */
    public void setBackroundImage(Image imageI) {
        this.image = imageI;
    }

    /**
     * this method set the backround with color.
     * @param colorI - as a Color object
     */
    public void setBackroundColor(Color colorI) {
        this.color = colorI;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.listOfVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        if (nameOfLevel == null) {
            return "null";
        }

        return nameOfLevel;
    }

    @Override
    public Sprite getBackground() {
        // if color is null
        if (this.color == null) {
            // return backround with the image
            return new Backround(this.image);
        } else { // return backround with the color
            return new Backround(this.color);
        }
    }

    @Override
    public List<Block> blocks() {
        return this.listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}