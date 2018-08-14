package backrounds;

import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Level4Backround implements Sprite {
    private int width;
    private int hight;

    /**
     * this is the constructor for Level4Backround.
     * @param width
     *            - as the width of the gui
     * @param hight
     *            - as the hight of the gui
     */
    public Level4Backround(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // filling the backround
        d.setColor(new Color(0, 128, 255));
        d.fillRectangle((int) 0, (int) 0, (int) this.width, (int) this.hight);

        // drawing part of the clouds
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(95, 395, 22);
        d.fillCircle(115, 410, 24);
        d.fillCircle(550, 470, 20);
        d.fillCircle(570, 485, 22);

        Random rand = new Random();
        for (int i = 0; i < 90; i++) {
            d.drawLine(550 + i, 480, 545 + i, 470 + rand.nextInt(270));
            d.drawLine(95 + i, 405, 90 + i, 395 + rand.nextInt(270));
        }

        // the continuation of the clouds drawing
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(125, 385, 25);
        d.fillCircle(580, 460, 25);

        d.setColor(new Color(160, 160, 160));
        d.fillCircle(145, 410, 27);
        d.fillCircle(168, 390, 28);
        d.fillCircle(600, 485, 27);
        d.fillCircle(622, 465, 28);

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}