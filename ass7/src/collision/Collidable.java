package collision;

import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return a rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * return a new Velocity - after the hit.
     * @param hitter - as a Ball
     * @param collisionPoint - as a point
     * @param currentVelocity - as a velocity
     * @return a new velocity - aftet the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}