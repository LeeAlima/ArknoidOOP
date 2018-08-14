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
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * this is the constuctor for BallRemover.
     * @param game
     *            - as the GameLevel object
     * @param remainingBalls
     *            - as a counter that counts the balls that remained in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * balls that touches the "dead zone" should be removes from the game, than
     * the number of the balls shoud be decrease in 1.
     * @param beingHit
     *            - as the Block object
     * @param hitter
     *            - as the ball object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // removing the ball from the game
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}