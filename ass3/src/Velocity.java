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
     * @param dx
     *            - the x coordinate
     * @param dy
     *            - the y coordinate
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
     * this method sets the x coordinate of velocity.
     * @param dXMove - as the x movement of the velocity
     */
    public void setDx(double dXMove) {
        this.dx = dXMove;
    }

    /**
     * this method sets the y coordinate of velocity.
     * @param dYMove - as the y movement of the velocity
     */
    public void setDy(double dYMove) {
        this.dy = dYMove;
    }

    /**
     * this method return a new velocity given vector's angle and speed.
     * @param angle
     *            - as the angle of the vector
     * @param speed
     *            - as the speed of the vector
     * @return new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(angle * Math.PI / 180) * speed;
        double dy = Math.cos(angle * Math.PI / 180) * -1 * speed;
        return new Velocity(dx, dy);
    }

    /**
     * this function add dx and dy to a point.
     * @param p
     *            - as a point
     * @return a new point
     */
    public Point applyToPoint(Point p) {
        // create a new point called pointToPoint
        Point pointToPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return pointToPoint;
    }
}
