package collision;

import blocks.Block;
import blocks.Enemy;
import counter.Counter;
import game.GameLevel;
import sprite.Ball;
import sprite.EnemyMovement;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private EnemyMovement enemyMovement;

    /**
     * this is the constuctor for BallRemover.
     * @param game - as the GameLevel object
     * @param removedBlocks - as a counter that counts the balls that remained
     * in the game
     * @param enemyMovement - EnemyMovement
     */
    public BlockRemover(GameLevel game, Counter removedBlocks, EnemyMovement enemyMovement) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.enemyMovement = enemyMovement;
    }

    /**
     * blocks that are hit and reach 0 hit - points should be removed from the
     * game. the number of blocks shoud be decrease in 1.
     * @param beingHit - as the Block object
     * @param hitter - as the ball object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit instanceof Enemy) {
            if (hitter.ballFromChicken()) {
                return;
            }
            remainingBlocks.decrease(1);
            enemyMovement.updateLinked((Enemy) beingHit);
        }
        // if the number of hit - points is 0
        if (beingHit.getHitPoints() == 0) {
            this.game.removeBall(hitter);
            // removing the listener
            beingHit.removeHitListener(this);
            // removing the block from the game
            beingHit.removeFromGame(this.game);
        }
    }
}