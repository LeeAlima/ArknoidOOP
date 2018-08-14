package backrounds;

import java.awt.Color;
import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Level2Backround implements Sprite {
    private int width;
    private int hight;

    /**
     * this is the constructor for Level2Backround.
     * @param width
     *            - as the width of the gui
     * @param hight
     *            - as the hight of the gui
     */
    public Level2Backround(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // filling the backround
        d.setColor(Color.white);
        d.fillRectangle((int) 0, (int) 0, (int) this.width, (int) this.hight);

        // drawing the rainbow
        d.setColor(new Color(255, 51, 51));
        d.fillCircle(400, 580, 100);
        d.setColor(new Color(255, 153, 51));
        d.fillCircle(400, 580, 90);
        d.setColor(new Color(255, 255, 51));
        d.fillCircle(400, 580, 80);
        d.setColor(new Color(0, 204, 0));
        d.fillCircle(400, 580, 70);
        d.setColor(new Color(0, 204, 204));
        d.fillCircle(400, 580, 60);
        d.setColor(new Color(0, 0, 255));
        d.fillCircle(400, 580, 60);
        d.setColor(new Color(102, 0, 204));
        d.fillCircle(400, 580, 50);
        d.setColor(Color.white);
        d.fillCircle(400, 580, 40);
        d.fillRectangle(300, 580, 200, 100);

        // drawing the sun rays
        d.setColor(new Color(255, 255, 153));
        for (int i = 0; i < 90; i++) {
            d.drawLine((int) (width * 0.2), (int) (0.2 * hight), width * 6 / 7 - i * 10, (int) (0.4 * hight));
        }

        // drawing the sun by circles
        d.setColor(new Color(255, 178, 172));
        d.fillCircle((int) (0.2 * width), (int) (0.2 * hight), 70);
        d.setColor(new Color(255, 255, 102));
        d.fillCircle((int) (0.2 * width), (int) (0.2 * hight), 60);
        d.setColor(new Color(255, 255, 153));
        d.fillCircle((int) (0.2 * width), (int) (0.2 * hight), 50);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}