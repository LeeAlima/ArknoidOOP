import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnviroment;

    /**
     * the first constructor for Ball.
     * @param center
     *            - as the center point of a circle
     * @param r
     *            - as the radius of the a circle
     * @param color
     *            - as the color of the circle
     * @param gameEnviroment
     *            - as the game enviroment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnviroment) {
        this.center = center;
        this.color = color;
        this.r = r;
        this.gameEnviroment = gameEnviroment;
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
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.color = color;
        this.r = r;
        this.center = new Point(x, y);
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
     * @param dx
     *            - as the x coordinate
     * @param dy
     *            - as the y coordinate
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
     * this method makes one step (the ball's movement) and change the ball's
     * velocity.
     */
    public void moveOneStep() {
        // creating a new line that presents the line between the current
        // location of the ball with the next location
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        // creating collisionInfo object with all of the game's object and the
        // line
        CollisionInfo collision = this.gameEnviroment.getClosestCollision(trajectory);
        // if there is an intersection point
        if (collision == null) {
            // move the ball's center to the end of line
            this.center = trajectory.end();
        } else {
            // creating a point that represents the collision point
            Point collidableP = collision.collisionPoint();
            double xNew = collidableP.getX();
            double yNew = collidableP.getY();
            // moving the collision point a little bit
            if (this.velocity.getDx() > 0) {
                xNew -= 0.000001;
            }
            if (this.velocity.getDx() < 0) {
                xNew += 0.000001;
            }
            if (this.velocity.getDy() > 0) {
                yNew -= 0.000001;
            }
            if (this.velocity.getDy() < 0) {
                yNew += 0.000001;
            }
            // if the ball is inside the paddle
            if (collision.collisionObject() instanceof Paddle) {
                Paddle p = (Paddle) collision.collisionObject();
                if (this.insideRectangle(p.getCollisionRectangle())) {
                    yNew -= 1;
                }
            }
            // if the ball is inside a block
            if (collision.collisionObject() instanceof Block) {
                Block block = (Block) collision.collisionObject();
                if (this.insideRectangle(block.getCollisionRectangle())) {
                    if (this.velocity.getDx() > 0) {
                        xNew -= 1;
                    }
                    if (this.velocity.getDx() < 0) {
                        xNew += 1;
                    }
                    if (this.velocity.getDy() > 0) {
                        yNew -= 1;
                    }
                    if (this.velocity.getDy() < 0) {
                        yNew += 1;
                    }
                }
            }
            // change the center
            this.center = new Point(xNew, yNew);
            Velocity newV = collision.collisionObject().hit(collidableP, this.velocity);
            this.velocity = newV;
        }
    }

    /**
     * this method changes the vertical movement of a ball.
     */
    public void changeVertical() {
        this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
    }

    /**
     * this method changes the horizontal movement of a ball.
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

    /**
     * this method sets the game environment.
     * @param enviroment
     *            - as the environment of the game
     */
    public void setEnviroment(GameEnvironment enviroment) {
        this.gameEnviroment = enviroment;
    }

    /**
     * this method actives moveOneStep.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this method add a ball to the game.
     * @param g
     *            - as the game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * this method checks if the ball is inside of the rectangle.
     * @param rect - as the rectangle
     * @return true if the ball is inside of the rectangle , false otherwise
     */
    public boolean insideRectangle(Rectangle rect) {
        double x = this.center.getX();
        double y = this.center.getY();
        double x1 = rect.getUpperLeft().getX();
        double y1 = rect.getUpperLeft().getY();
        double w = rect.getWidth();
        double h = rect.getHeight();
        if ((x1 <= x && x <= x1 + w) && (y1 <= y && y <= y1 + h)) {
                return true;
            }
    return false;
    }
}