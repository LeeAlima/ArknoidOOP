package blocks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
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

public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Rectangle rect;
    private java.awt.Color color;
    private int counterHits;

    /**
     * constructor of block.
     * @param rect1 - as a rectangle
     * @param color - as a color
     * @param counterHits - number of hits
     */
    public Block(Rectangle rect1, java.awt.Color color, int counterHits) {
        this.rect = rect1;
        this.color = color;
        this.counterHits = counterHits;
    }

    /**
     * this method return a rectangle.
     * @return Rectangle
     */
    public Rectangle returnBlockRectangle() {
        return this.rect;
    }

    /**
     * this method sets the block rectangle.
     * @param r - as a rectangle
     */
    public void setBlockRecatngle(Rectangle r) {
        this.rect = r;
    }

    /**
     * his method created a default block a the size of one enemy.
     * @param x - as the x coordinate
     * @param y - as the y coordinate
     */
    public Block(int x, int y) {
        this.rect = new Rectangle(new Point(x, y), 40, 30);
        counterHits = 1;

    }

    /**
     * this method return a copy of the object.
     * @return Block - a copy of the similar one
     */
    public Block copyBlock() {
        return new Block(this.rect, this.color, this.counterHits);
    }

    /**
     * @return the block's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method return the number of hit points.
     * @return the number of hit points
     */
    public int getHitPoints() {
        return this.counterHits;
    }

    /**
     * this method draws a block and draws the number of hits.
     * @param surface - as the surface object we can draw with
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        // filling the rectangle
        surface.fillRectangle((int) x1, (int) y1, (int) width, (int) height);
        surface.setColor(Color.BLACK);
        // drawing the rectangle
        surface.drawRectangle((int) x1, (int) y1, (int) width, (int) height);
        surface.setColor(Color.black);
        surface.setColor(this.color);
    }

    /**
     * this method checks where the balls hit the paddle and calculate a new
     * velocity and returns it.
     * @param hitter - as a ball
     * @param collisionPoint - as a point
     * @param currentVelocity - as a velocity
     * @return - a new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // define newVelocity as the current velocity
        Velocity newVelocity = currentVelocity;
        // finding the start x and y coordinates
        double startX = this.rect.getUpperLeft().getX();
        double startY = this.rect.getUpperLeft().getY();
        // finding the x and y coordinates of the collision point
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        // if the collision point is in one of the rectangle's vertexes
        if (collisionPoint.equals(this.rect.getUpperLeft()) || collisionPoint.equals(this.rect.getUpperRight())
                || collisionPoint.equals(this.rect.getLowerLeft())
                || collisionPoint.equals(this.rect.getLowerRight())) {
            // change dx and dy
            newVelocity.setDx(currentVelocity.getDx() * (-1));
            newVelocity.setDy(currentVelocity.getDy() * (-1));
            // if the collision point is on the upper or the bottom line
        } else if ((x > startX) && (x < startX + this.rect.getWidth())) {
            newVelocity.setDy((currentVelocity.getDy()) * (-1));
            // if the collision point is on the left or rigth lines
        } else if ((y > startY) && (y < startY + this.rect.getHeight())) {
            newVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        // if counter hits is bigger than zero
        if (this.counterHits > 0) {
            // decrease the counter
            this.counterHits--;
        }
        this.notifyHit(hitter);
        // return the new velocity(after the change)
        return newVelocity;
    }

    /**
     * this method do nothing as the time passed.
     * @param dt - as a double
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * this method add a block to the game.
     * @param g - as the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this method removes the sprite from the game.
     * @param game - as a GameLevel
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * this method notify the hit.
     * @param hitter - as a ball object
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}