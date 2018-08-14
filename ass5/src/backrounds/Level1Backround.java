package backrounds;

import java.awt.Color;
import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Level1Backround implements Sprite {
    private int width;
    private int hight;

    /**
     * this is the constructor for Level1Backround.
     * @param width
     *            - as the width of the gui
     * @param hight
     *            - as the hight of the gui
     */
    public Level1Backround(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // filling the backround
        d.fillRectangle((int) 0, (int) 0, (int) this.width, (int) this.hight);
        d.setColor(Color.RED);
        // drawing the circles
        d.drawCircle(this.width / 2, (int) (this.hight * 0.3 + this.hight / 30), 40);
        d.drawCircle(this.width / 2, (int) (this.hight * 0.3 + this.hight / 30), 60);
        d.drawCircle(this.width / 2, (int) (this.hight * 0.3 + this.hight / 30), 80);
        d.drawCircle(this.width / 2, (int) (this.hight * 0.3 + this.hight / 30), 100);
        // drawing the lines
        d.drawLine(this.width / 2 - 120, (int) (this.hight * 0.3 + this.hight / 30), this.width / 2 + 120,
                (int) (this.hight * 0.3 + this.hight / 30));
        d.drawLine(this.width / 2, (int) (this.hight * 0.3 + this.hight / 30) - 120, this.width / 2,
                (int) (this.hight * 0.3 + this.hight / 30) + 120);

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}
