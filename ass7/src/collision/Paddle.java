package collision;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Point startBound;
    private Point secondBound;
    private int speed;
    private int height;
    private GameEnvironment gameEnviroment;
    private GameLevel game;
    private long prevTime;
    private long currentTime;
    private List<Ball> listOfBalls;

    /**
     * constructor for Paddle.
     * @param keyboard - as the keyboard sensor
     * @param rect - as the rectangle
     * @param startBound - as the first bound
     * @param secondBound - as the second bound
     * @param speed - as the paddle's speed
     * @param gameEnviroment - as GameEnviroment
     * @param game - as GameLevel
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, Point startBound, Point secondBound, int speed,
            GameEnvironment gameEnviroment, GameLevel game) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.startBound = startBound;
        this.secondBound = secondBound;
        this.speed = speed;
        this.height = 600;
        this.gameEnviroment = gameEnviroment;
        this.game = game;
        this.currentTime = 0;
        this.prevTime = 0;
        this.listOfBalls = new ArrayList<>();
    }

    /**
     * this method makes the paddle move left.
     * @param dt - as a double
     */
    public void moveLeft(double dt) {
        // changing the upperLeft point by changing the x coordinate
        this.rect.setUpperLeft(
                new Point(this.rect.getUpperLeft().getX() - this.speed * dt, this.rect.getUpperLeft().getY()));
    }

    /**
     * this method makes the paddle move right.
     * @param dt - as a double
     */
    public void moveRight(double dt) {
        // changing the upperLeft point by changing the x coordinate
        this.rect.setUpperLeft(
                new Point(this.rect.getUpperLeft().getX() + this.speed * dt, this.rect.getUpperLeft().getY()));
    }

    /**
     * this method checks what the user pressed and moves the paddle.
     * @param dt - as a double
     */
    public void timePassed(double dt) {
        // if the user pressed the left key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // if the get upperleft point is still in gui
            if (rect.getUpperLeft().getX() > this.startBound.getX() + this.speed * dt) {
                // moveLeft
                this.moveLeft(dt);
            } else {
                this.rect.setUpperLeft(
                        new Point(this.startBound.getX(), this.getCollisionRectangle().getUpperLeft().getY()));
            }
            // if the user pressed the right key
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // if the get upperleft point is still in gui
            if (rect.getUpperRight().getX() < this.secondBound.getX() - this.speed * dt) {
                // moveRight
                this.moveRight(dt);
            } else {
                this.rect.setUpperLeft(new Point(this.secondBound.getX() - this.rect.getWidth(),
                        this.getCollisionRectangle().getUpperLeft().getY()));
            }
        } // make shot available every 0.35 seconds
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            currentTime = System.currentTimeMillis();
            if (this.currentTime - this.prevTime >= 350) {
                shoot(dt);
            }
        }
        return;
    }

    /**
     * this method makes one shoot.
     * @param dt - as a double
     */
    public void shoot(double dt) {
        // create a new point to llocate the shot
        Point p = new Point((int) (this.rect.getUpperLeft().getX() + this.rect.getWidth() / 2),
                (int) (this.height * 0.95 - 5));
        Ball oneShot = new Ball(p, 3, Color.white, this.gameEnviroment, false);
        oneShot.setVelocity(0, -400);
        // add the shot to the game enviroment
        oneShot.setEnviroment(this.gameEnviroment);
        oneShot.addToGame(this.game);
        // add the shot to the list of balls
        listOfBalls.add(oneShot);
        // add the shot to the list of balls of the game
        this.game.addBall(oneShot);
        // time the shoot's time
        this.prevTime = System.currentTimeMillis();
    }

    /**
     * this method clear all of the balls of the game.
     */
    public void clearBalls() {
        if (this.listOfBalls != null) {
            for (Ball b : this.listOfBalls) {
                b.removeFromGame(this.game);
            }
        }
    }

    /**
     * this method draws the paddle.
     * @param d - as the surface
     */
    public void drawOn(DrawSurface d) {
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double x2 = this.rect.getWidth();
        double y2 = this.rect.getHeight();
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) x1, (int) y1, (int) x2, (int) y2);


    }

    /**
     * this method calls a methos to initialize the game.
     * @param hitter - as a Ball
     * @param collisionPoint - as the collision point
     * @param currentVelocity - as the current velocity
     * @return velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if the ball touches the paddle - reset the game
        this.game.createAnewGame();

        double speedD = Math.sqrt(
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
                return Velocity.fromAngleAndSpeed(300, speedD);
                // if the ball touches the second nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 2 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(315, speedD);
                // if the ball touches the third nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 3 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(330, speedD);
                // if the ball touches the fourth nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 4 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(345, speedD);
                // if the ball touches the fifth nint
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 5 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(360, speedD);
                // if the ball touches the sixt ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 6 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(15, speedD);
                // if the ball touches the seventh ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 7 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(30, speedD);
                // if the ball touches the eighth ninth
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 8 * (this.rect.getWidth() / 9.0)) {
                return Velocity.fromAngleAndSpeed(45, speedD);
            } else {
                // if the ball touches the ninth ninth
                return Velocity.fromAngleAndSpeed(60, speedD);
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
     * @param g - as the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this method removes the paddle from the game.
     * @param g - as the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
}