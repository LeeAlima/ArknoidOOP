package counter;

import blocks.Block;
import collision.HitListener;
import sprite.Ball;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this is the counstructor of ScoreTrackingListener.
     * @param scoreCounter - as a counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // if the block's hit points is non - negative
        if (beingHit.getHitPoints() >= 0) {
            currentScore.increase(5);
        }
        // if the block's hit points is 0
        if (beingHit.getHitPoints() == 0) {
            currentScore.increase(10);
        }
    }
}