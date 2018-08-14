package animation;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public interface Animation {

    /**
     * this method uses the DrawSurface object in order to draw the requested
     * drawing to the animation object type.
     * @param d
     *            - as a DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method returns true if the animation should stop and false if not.
     * @return boolean result - true or false
     */
    boolean shouldStop();
}