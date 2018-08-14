package blocks;

import java.awt.Image;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Enemy extends Block {
    private Image image;
    private double xS;
    private double yS;
    private Rectangle start;

    /**
     * this is the constructor for Enemy.
     * @param x - as the x coordinate
     * @param y - as the y coordinate
     * @param image - as the image of the enemy
     */
    public Enemy(int x, int y, Image image) {
        super(x, y);
        this.xS = x;
        this.yS = y;
        this.image = image;
        this.start = this.returnBlockRectangle();
    }

    /**
     * this is a copy method.
     * @return a copy of the enemy
     */
    public Enemy copyEnemy() {
        Enemy e = new Enemy((int) xS, (int) yS, this.image);
        return e;
    }

    /**
     * this method moves the enemy.
     * @param dx - as the dx movmenent
     * @param dt - as a double
     */
    public void moveBy(double dx, double dt) {
        // set the new x coordinate
        double d = (dx * dt);
        double di = returnBlockRectangle().getUpperLeft().getX() + d;
        this.xS = di;
        this.yS = returnBlockRectangle().getUpperLeft().getY();
        double width = returnBlockRectangle().getWidth();
        double height = returnBlockRectangle().getHeight();
        // change the rectangle location
        setBlockRecatngle(new Rectangle(new Point(xS, yS), width, height));
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawImage((int) xS, (int) yS, image);
    }

    /**
     * @return the current rectangel
     */
    public Rectangle getRectangel() {
        return returnBlockRectangle();
    }

    /**
     * this method returns the start rectangle.
     * @return the start rectangle
     */
    public Rectangle returnRect() {
        return this.start;
    }

    /**
     * this method set the location of the rectangle.
     * @param rect - as a recatngle object
     */
    public void setRectangle(Rectangle rect) {
        this.xS = (int) rect.getUpperLeft().getX();
        this.yS = (int) rect.getUpperLeft().getY();
        this.setBlockRecatngle(rect);
    }
}