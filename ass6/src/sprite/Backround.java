package sprite;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Backround implements Sprite {

    private Image image;
    private Color color;

    /**
     * the is the fitst constructor for Backround - handling with colors.
     * @param color - as a Color object
     */
    public Backround(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * this is the second constructor for Backround - handling with images.
     * @param image - as an image object
     */
    public Backround(Image image) {
        this.image = image;
        this.color = null;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.image == null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        } else {
            d.drawImage(0, 0, this.image);
        }
    }

    @Override
    public void timePassed(double dt) {
        return;
    }
}