package backrounds;

import java.awt.Color;
import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Level3Backround implements Sprite {
    private int width;
    private int hight;

    /**
     * this is the constructor for Level3Backround.
     * @param width
     *            - as the width of the gui
     * @param hight
     *            - as the hight of the gui
     */
    public Level3Backround(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // drawing the bulding
        d.setColor(new Color(224, 224, 224));
        d.fillRectangle((int) 0, (int) 0, (int) this.width, (int) this.hight);
        d.setColor(new Color(32, 32, 32));
        d.fillRectangle(100, 430, 98, 170);
        d.setColor(new Color(224, 224, 224));
        int x = 0;
        int y = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(105 + x, 437 + y, 12, 22);
                x += 19;
            }
            x = 0;
            y += 30;
        }
        d.setColor(new Color(50, 50, 50));
        d.fillRectangle(140, 390, 20, 40);
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(145, 240, 10, 150);
        d.setColor(new Color(255, 255, 0));

        // drawing the light
        d.fillCircle(150, 240, 10);
        d.setColor(Color.red);
        d.fillCircle(150, 240, 7);
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(150, 240, 3);

        // drawing the first pokador
        d.setColor(Color.red);
        d.fillCircle(550, 450, 30);
        d.setColor(new Color(224, 224, 224));
        d.fillRectangle(520, 450, 60, 60);
        d.setColor(Color.black);
        d.drawCircle(550, 450, 30);
        d.drawLine(520, 450, 580, 450);
        d.setColor(Color.BLACK);
        d.fillCircle(550, 450, 18);
        d.setColor(Color.WHITE);
        d.fillCircle(550, 450, 15);
        d.setColor(Color.BLACK);
        d.drawCircle(550, 450, 8);

        // drawing the second pokador
        d.setColor(Color.red);
        d.fillCircle(450, 350, 30);
        d.setColor(new Color(224, 224, 224));
        d.fillRectangle(420, 350, 60, 60);
        d.setColor(Color.black);
        d.drawCircle(450, 350, 30);
        d.drawLine(420, 350, 480, 350);
        d.setColor(Color.BLACK);
        d.fillCircle(450, 350, 18);
        d.setColor(Color.WHITE);
        d.fillCircle(450, 350, 15);
        d.setColor(Color.BLACK);
        d.drawCircle(450, 350, 8);

        // drawing the third pokador
        d.setColor(Color.red);
        d.fillCircle(650, 350, 30);
        d.setColor(new Color(224, 224, 224));
        d.fillRectangle(620, 350, 60, 60);
        d.setColor(Color.black);
        d.drawCircle(650, 350, 30);
        d.drawLine(620, 350, 650, 350);
        d.setColor(Color.BLACK);
        d.fillCircle(650, 350, 18);
        d.setColor(Color.WHITE);
        d.fillCircle(650, 350, 15);
        d.setColor(Color.BLACK);
        d.drawCircle(650, 350, 8);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}