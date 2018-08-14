/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor for CollisionInfo.
     * @param collisionPoint - as a point
     * @param collisionObject - as an object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * this method returns the point at which the collision occurs.
     * @return a point - the collisonPoint
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * this method returns the collidable object involved in the collision.
     * @return a collidable - collisionObject
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}