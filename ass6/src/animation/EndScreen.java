package animation;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class EndScreen implements Animation {
    private Boolean stop;
    private int typeOfMessage;
    private int score;
    private Boolean win;

    /**
     * this is the first constructor for EndScreen.
     * @param typOfMessage - as an int to symbolize which message to show
     * @param score - as a int (the user's score)
     * @param win - as a boolean parameter that represents if the user won or
     * not
     */
    public EndScreen(int typOfMessage, int score, Boolean win) {
        this.typeOfMessage = typOfMessage;
        this.score = score;
        this.win = win;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(255, 255, 153));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 51, 51));
        // if the user lost
        if (typeOfMessage == 1) {
            d.drawText(130, 160, "Game Over. Your score is " + this.score, 45);
            for (int i = 0; i < 10; i++) {
                d.drawLine(200 + i, 250 + i, 600 + i, 450 + i);
                d.drawLine(200 + i, 450, 600 + i, 250);
            }
        }
        // if the user won
        if (typeOfMessage == 2 && win) {

            // drawing the crown
            d.fillRectangle(100, 200, 600, 200);
            d.fillRectangle(100, 150, 200, 150);
            d.fillRectangle(300, 150, 200, 150);
            d.fillRectangle(500, 150, 200, 150);
            d.setColor(new Color(255, 255, 153));
            int y = 200;
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 100; i++) {
                    d.drawLine(y + i, i, y + i, 150 + i);
                    d.drawLine(y - 100 + i, 150, y - 100 + i, 250 - i);
                }
                y += 200;
            }
            // printing a message to the user
            d.drawText(200, 320, "You Win! Your score is " + this.score, 35);
            d.setColor(new Color(255, 51, 51));
            d.fillCircle(600, 150, 7);
            d.fillCircle(400, 150, 7);
            d.fillCircle(200, 150, 7);
            d.fillRectangle(100, 420, 600, 20);
            d.setColor(new Color(255, 255, 153));
            d.fillCircle(600, 150, 3);
            d.fillCircle(400, 150, 3);
            d.fillCircle(200, 150, 3);
            d.setColor(new Color(255, 255, 153));
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
