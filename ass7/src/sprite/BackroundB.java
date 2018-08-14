package sprite;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class BackroundB implements Sprite {
    private Image image;

    /**
     * this is the constructor for BackroundBlack.
     * @param image - as an image to draw at the backround
     */
    public BackroundB(Image image) {
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.image == null) {
            d.setColor(Color.black);
            d.drawRectangle(0, 0, 800, 600);
        }
        d.drawImage(0, 0, this.image);

    }

    @Override
    public void timePassed(double dt) {
        return;
    }

}
