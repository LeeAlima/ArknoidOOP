package blocks;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
    private java.awt.Color frame;
    private TreeMap<Integer, Image> imageFillMap;
    private TreeMap<Integer, Color> colorsFillMap;

    /**
     * first constructor of block.
     * @param rect - as a rectangle
     * @param color =- as a color
     * @param counterHits - number of hits
     */
    public Block(Rectangle rect, java.awt.Color color, int counterHits) {
        this.rect = rect;
        this.color = color;
        this.counterHits = counterHits;
        this.frame = Color.black;
    }

    /**
     * this is the second constructor for block.
     * @param rect - as a rectangle
     * @param imageFill - as a map of images
     * @param colorFill - as a map of colors
     * @param numOfHits - as the number of hits
     * @param frame - as the color of the frame
     */
    public Block(Rectangle rect, TreeMap<Integer, Image> imageFill, TreeMap<Integer, Color> colorFill,
            Integer numOfHits, java.awt.Color frame) {
        this.rect = rect;
        this.imageFillMap = imageFill;
        this.colorsFillMap = colorFill;
        this.counterHits = numOfHits;
        hitListeners = new ArrayList<HitListener>();
        this.frame = frame;
    }

    /**
     * this method return a copy of the object.
     * @return Block - a copy of the similar one
     */
    public Block copyBlock() {
        return new Block(this.rect, this.imageFillMap, this.colorsFillMap, this.counterHits, this.frame);
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
     * this method draws a block according to the maps of image and colors.
     * @param surface - as the surface object we can draw with
     */
    public void drawOn(DrawSurface surface) {
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        if (this.imageFillMap == null && this.colorsFillMap == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) x1, (int) y1, (int) width, (int) height);
        } else if (this.imageFillMap != null && this.imageFillMap.containsKey(this.counterHits)) {
            surface.drawImage((int) x1, (int) y1, this.imageFillMap.get(this.counterHits));
        } else if (this.colorsFillMap != null && this.colorsFillMap.containsKey(this.counterHits)) {
            surface.setColor(this.colorsFillMap.get(this.counterHits));
            surface.fillRectangle((int) x1, (int) y1, (int) width, (int) height);
        } else {
            if (this.colorsFillMap != null && this.colorsFillMap.containsKey(0)) {
                surface.setColor(this.colorsFillMap.get(0));
                surface.fillRectangle((int) x1, (int) y1, (int) width, (int) height);
            } else {
                surface.drawImage((int) x1, (int) y1, this.imageFillMap.get(0));
            }
        }
        // drawing the frame
        if (this.frame != null) {
            surface.setColor(this.frame);
            surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
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
            // if the collision point is on the left or the right line
        } else if (Math.round(x) == Math.round(startX + this.rect.getWidth()) || Math.round(x) == Math.round(startX)) {
            newVelocity.setDx(currentVelocity.getDx() * (-1));
        } else if (Math.round(y) == Math.round(startY + this.rect.getHeight()) || Math.round(y) == Math.round(startY)) {
            newVelocity.setDy((currentVelocity.getDy()) * (-1));
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