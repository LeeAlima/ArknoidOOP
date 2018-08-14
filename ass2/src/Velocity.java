/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * the first constructor of velocity.
     * @param dx - the x coordinate
     * @param dy - the y coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * this method returns the x coordinate of velocity.
     * @return the x coordinate of velocity
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * this method returns the y coordinate of velocity.
     * @return the y coordinate of velocity
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * this method return a new velocity given vector's angle and speed.
     * @param angle - as the angle of the vector
     * @param speed - as the speed of the vector
     * @return new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        // finding dx by using the cos function
        double dx = speed * Math.cos(radians);
        // finding dy by using the sin function
        double dy = speed * Math.sin(radians);
        // making the the coordinates different from zero
        if (dx < 1 && dx >= 0) {
            dx = 1;
        }
        if (dy < 1 && dy >= 0) {
            dy = 1;
        }
        if (dx > -1 && dx < 0) {
            dx = -1;
        }
        if (dy > -1 && dy < 0) {
            dy = -1;
        }
        // return a new velocity defined by dx and dy
        return new Velocity(dx, dy);
     }
    /**
     * this function add dx and dy to a point.
     * @param p - as a point
     * @return a new point
     */
    public Point applyToPoint(Point p) {
        // create a new point called pointToPoint
        Point pointToPoint = new Point(p.getX()
                + this.dx , p.getY() + this.dy);
        return pointToPoint;
    }
}
