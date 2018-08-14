import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Block implements Collidable, Sprite {

    private Rectangle rect;
    private java.awt.Color color;
    private int counterHits;

    /**
     * constructor of block.
     * @param rect - as a rectangle
     * @param color =- as a color
     * @param counterHits - number of hits
     */
    public Block(Rectangle rect, java.awt.Color color, int counterHits) {
        this.rect = rect;
        this.color = color;
        this.counterHits = counterHits;
    }

    /**
     * @return the block's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method draws a block and draws the number of hits.
     * @param surface
     *            - as the surface object we can draw with
     */
    public void drawOn(DrawSurface surface) {
        String stringNumber;
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
        // if the counter of the hits is more than 0
        if (counterHits > 0) {
            // change it to string
            stringNumber = Integer.toString(counterHits);
        } else {
            stringNumber = "X";
        }
        // draw the string on the surface
        surface.drawText((int) (x1 + width / 2), (int) (y1 + height / 1.5), stringNumber, 15);
        surface.setColor(this.color);
    }

    /**
     * this method checks where the balls hit the paddle and calculate a new
     * velocity and returns it.
     * @param collisionPoint - as a point
     * @param currentVelocity - as a velocity
     * @return - a new velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
        // return the new velocity(after the change)
        return newVelocity;
    }

    /**
     * this method do nothing as the time passed.
     */
    public void timePassed() {
        return;
    }

    /**
     * this method add a block to the game.
     * @param g - as the game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}