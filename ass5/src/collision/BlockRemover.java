package collision;

import counter.Counter;
import game.GameLevel;
import sprite.Ball;
import sprite.Block;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * this is the constuctor for BallRemover.
     * @param game
     *            - as the GameLevel object
     * @param removedBlocks
     *            - as a counter that counts the balls that remained in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * blocks that are hit and reach 0 hit - points should be removed from the game.
     * the number of blocks shoud be decrease in 1.
     * @param beingHit
     *            - as the Block object
     * @param hitter
     *            - as the ball object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // if the number of hit - points is 0
        if (beingHit.getHitPoints() == 0) {
            // removing the listener
            beingHit.removeHitListener(this);
            // removing the block from the game
            beingHit.removeFromGame(this.game);
            remainingBlocks.decrease(1);
        }
    }
}