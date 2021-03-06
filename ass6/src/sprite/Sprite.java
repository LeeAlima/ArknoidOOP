package sprite;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - as the surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt - as a double number to track the time not with seconds but
     * with speed by pixels.
     */
    void timePassed(double dt);
}