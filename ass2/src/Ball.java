import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ball {
    private Point center;
    private Point firstBound;
    private Point secondBound;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;

    /**
     * the first constructor for Ball.
     * @param center
     *            - as the center point of a circle
     * @param r
     *            - as the radius of the a circle
     * @param color
     *            - as the color of the circle
     * @param firstBound - as the first bound
     * @param secondBound - as the second bound
     */
    public Ball(Point center, int r, java.awt.Color color,
            Point firstBound, Point secondBound) {
        this.center = center;
        this.color = color;
        this.r = r;
        this.firstBound = firstBound;
        this.secondBound = secondBound;
    }

    /**
     * the second constructor for Ball.
     * @param center
     *            - as the center point of a circle
     * @param r
     *            - as the radius of the a circle
     * @param color
     *            - as the color of the circle
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.color = color;
        this.r = r;
    }
    /**
     * the third constructor for Ball.
     * @param x
     *            - as the x value of the center of the point
     * @param y
     *            - as the y value of the center of the point
     * @param r
     *            - as the radius of the a circle
     * @param color
     *            - as the color of the circle
     * @param firstBound - as the first bound
     * @param secondBound - as the second bound
     */
    public Ball(int x, int y, int r, java.awt.Color color,
            Point firstBound, Point secondBound) {
        this.color = color;
        this.r = r;
        this.center = new Point(x , y);
        this.firstBound = firstBound;
        this.secondBound = secondBound;
    }
    /**
     * the fourth constructor for Ball.
     * @param x
     *            - as the x value of the center of the point
     * @param y
     *            - as the y value of the center of the point
     * @param r
     *            - as the radius of the a circle
     * @param color
     *            - as the color of the circle
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.color = color;
        this.r = r;
        this.center = new Point(x , y);
    }

    /**
     * this method defines the object velocity.
     * @param v
     *            - as a velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this method defines the object velocity.
     * @param dx - as the x coordinate
     * @param dy - as the y coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method returns the x value of center.
     * @return the x value of the center point of the circle
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * this method returns the y value of center.
     * @return the y value of the center point of the circle
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * this method returns the velocity of object.
     * @return the velocity of a ball object
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * this method changes the movement of a ball if it reaches the limits.
     */
    public void moveOneStep() {
        // creating a new point
        Point pMove = this.velocity.applyToPoint(this.center);
        // define the x value
        int xMovement = (int) pMove.getX();
        // define the y value
        int yMovement = (int) pMove.getY();
       // checks is the x value is over the limit
        if (xMovement + this.r >= this.secondBound.getX()) {
            xMovement = (int) this.secondBound.getX() - this.r;
            this.changeHorizonal();
        }
        // checks if the x value is under the limit
        if (xMovement - this.r <= this.firstBound.getX()) {
            xMovement = (int) this.firstBound.getX() + this.r;
            this.changeHorizonal();
        }
        // checks if the y value is over the limit
        if (yMovement + this.r >= this.secondBound.getY()) {
            yMovement = (int) this.secondBound.getY() - this.r;
            this.changeVertical();
        }
        // checks if the y value is under the limit
        if (yMovement - this.r <= this.firstBound.getY()) {
            yMovement = (int) this.firstBound.getY() + this.r;
            this.changeVertical();
        }
        // define the point's center
        this.center = new Point(xMovement, yMovement);
    }

    /**
     * this method changes the vertical movement of a ball.
     */
    public void changeVertical() {
        this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
    }

    /**
     * this method changes the horizonal movement of a ball.
     */
    public void changeHorizonal() {
        this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * this method returns the radius of a circle.
     * @return the radius of a circle
     */
    public int getSize() {
        return this.r;
    }

    /**
     * this method returns the circle's color.
     * @return the circle's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method draws a ball.
     * @param surface
     *            - as DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        // set the ball color with the current object color
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

}