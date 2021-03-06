package levels;

import java.util.List;

import blocks.Block;
import blocks.Enemy;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public interface LevelInformation {

    /**
     * this method return the paddle speed.
     * @return a number - that represent the speed of the paddle
     */
    int paddleSpeed();

    /**
     * this method returns the paddle width.
     * @return a number - that represent the width of the paddle
     */
    int paddleWidth();

    /**
     * this method return a string of the name of the game level.
     * @return the name(string) of the game level
     */
    String levelName();

    /**
     * this method returns a sprite with the backround of the level.
     * @return sprite - with the backround of the level
     */
    Sprite getBackground();

    /**
     * this method returns a list of blocks.
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * this method return the number of blocks required to move the level.
     * @return a number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * this method return a list of Enemy.
     * @return a list of enemy
     */
    List<Enemy> enemies();


}