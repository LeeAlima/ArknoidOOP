import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private Point startBound;
    private Point secondBound;

    /**
     * constructor for Paddle.
     * @param keyboard
     *            - as the keyboard sensor
     * @param rect
     *            - as the rectangle
     * @param color
     *            - as the color
     * @param startBound
     *            - as the first bound
     * @param secondBound
     *            - as the second bound
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, java.awt.Color color, Point startBound,
            Point secondBound) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.startBound = startBound;
        this.secondBound = secondBound;
    }

    /**
     * this method makes the paddle move left.
     */
    public void moveLeft() {
        // changing the upperLeft point by changing the x coordinate
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - 10, this.rect.getUpperLeft().getY()));
    }

    /**
     * this method makes the paddle move right.
     */
    public void moveRight() {
        // changing the upperLeft point by changing the x coordinate
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + 10, this.rect.getUpperLeft().getY()));
    }

    /**
     * this method checks what the user pressed and moves the paddle.
     */
    public void timePassed() {
        // if the user pressed the left key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // if the get upperleft point is still in gui
            if (rect.getUpperLeft().getX() > this.startBound.getX()) {
                // moveLeft
                this.moveLeft();
            } // if the user pressed the right key
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // if the get upperleft point is still in gui
            if (rect.getUpperRight().getX() < this.secondBound.getX()) {
                // moveRight
                this.moveRight();
            }
        } else {
            // do nothing
            return;
        }
    }

    /**
     * this method draws the paddle.
     * @param d
     *            - as the surface
     */
    public void drawOn(DrawSurface d) {
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double x2 = this.rect.getWidth();
        double y2 = this.rect.getHeight();
        d.setColor(this.color);
        d.fillRectangle((int) x1, (int) y1, (int) x2, (int) y2);
    }

    /**
     * @return this class's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method checks where the balls hit the paddle and calculate a new
     * velocity and returns it.
     * @param collisionPoint
     *            - as the collision point
     * @param currentVelocity
     *            - as the current velocity
     * @return velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        double speed = Math.sqrt(
                currentVelocity.getDx() * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy());
        // if the collision point is in one of the rectangles' vertexes
        if (collisionPoint.equals(this.rect.getUpperLeft()) || collisionPoint.equals(this.rect.getUpperRight())
                || collisionPoint.equals(this.rect.getLowerLeft())
                || collisionPoint.equals(this.rect.getLowerRight())) {
            // change both dx and dy coordinated of velocity
            return new Velocity(-1 * (currentVelocity.getDx()), -1 * (currentVelocity.getDy()));
            // if the collisionPoint is on the upper line
        } else if (this.rect.upperLine().onLine(collisionPoint)) {
            // if the ball touches the first ninth
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 1 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(300, speed);
             // if the ball touches the second nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 2 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(315, speed);
             // if the ball touches the third nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 3 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(330, speed);
             // if the ball touches the fourth nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 4 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(345, speed);
             // if the ball touches the fifth nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 5 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(360, speed);
             // if the ball touches the sixt ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 6 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(15, speed);
             // if the ball touches the seventh ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 7 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(30, speed);
             // if the ball touches the eighth ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 8 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(45, speed);
            } else {
                // if the ball touches the ninth ninth
                return Velocity.fromAngleAndSpeed(60, speed);
            } // if the ball touches the left or right lines
        } else if (this.rect.leftSideLine().onLine(collisionPoint)
                || this.rect.rightSideLine().onLine(collisionPoint)) {
            return new Velocity(-1 * (currentVelocity.getDx()), (currentVelocity.getDy()));
        } else if (this.rect.lowerLine().onLine(collisionPoint)) {
            return new Velocity(-1 * (currentVelocity.getDx()), -1 * (currentVelocity.getDy()));
        }
        return currentVelocity;
    }

    /**
     * this method add the paddle to the game.
     * @param g
     *            - as the game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}