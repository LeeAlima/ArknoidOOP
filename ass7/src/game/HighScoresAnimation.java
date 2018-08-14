package game;

import java.awt.Color;

import animation.Animation;
import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class HighScoresAnimation implements Animation {

    private Boolean stop;
    private HighScoresTable scores;
    private boolean newMenu;

    /**
     * this is the constructor for HighScoresAnimation.
     * @param scores - as an HighScoresTable obkject
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
        this.newMenu = false;
    }

    /**
     * this method draws nicely a string.
     * @param d - as the DrawSurface
     * @param x - as the x locataion
     * @param y - as the y location
     * @param s - as the string
     * @param size - as the font size
     */
    public void drawText(DrawSurface d, int x, int y, String s, int size) {
        d.setColor(Color.BLACK);
        d.drawText(x + 1, y, s, size);
        d.drawText(x - 1, y, s, size);
        d.drawText(x, y + 1, s, size);
        d.drawText(x, y - 1, s, size);
        d.setColor(new Color(255, 51, 51));
        d.drawText(x, y, s, size);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // drawing the backround
        d.setColor(new Color(255, 255, 153));
        d.fillRectangle(0, 0, 800, 600);

        // drawing the information
        drawText(d, 50, 50, "High Scores", 32);
        drawText(d, 100, 120, "Player Name", 24);
        drawText(d, 450, 120, "Score", 24);
        String msg = "press space to continue";
        drawText(d, 200, 450, msg, 40);

        d.setColor(Color.BLACK);
        d.drawLine(100, 130, 700, 130);
        d.drawLine(100, 132, 700, 132);
        d.setColor(new Color(255, 51, 51));
        d.drawLine(100, 131, 700, 131);

        // drawing the user's name and score
        int i = 0;
        for (ScoreInfo score : scores.getHighScores()) {
            d.drawText(101, 180 + i, score.getName(), 24);
            d.drawText(450, 180 + i, Integer.toString(score.getScore()), 24);
            i += 50;
        }
        // drawing a rainbow
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
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(400, 580, 40);
        d.fillRectangle(300, 580, 200, 100);
        return;
}

    @Override
    public boolean shouldStop() {
        if (this.newMenu && this.stop) {
            this.newMenu = false;
            this.stop = false;
            return this.stop;
        }
        if (!(this.newMenu) && this.stop) {
            this.newMenu = true;
            return this.stop;
        }
        return this.stop;
    }
}