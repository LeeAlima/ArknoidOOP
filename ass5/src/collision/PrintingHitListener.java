package collision;

import sprite.Ball;
import sprite.Block;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class PrintingHitListener implements HitListener {

    /**
     * blocks that are hit - a messege with their hit-points is being printed to the console.
     * @param beingHit
     *            - as the Block object
     * @param hitter
     *            - as the ball object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}