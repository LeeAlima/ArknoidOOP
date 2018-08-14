package collision;

import sprite.Ball;
import sprite.Block;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public interface HitListener {

    /**
     * this method is called whenever the beingHit object is hit.
     * @param beingHit
     *            - as the Block parameter
     * @param hitter
     *            - as the Ball parameter
     */
    void hitEvent(Block beingHit, Ball hitter);
}