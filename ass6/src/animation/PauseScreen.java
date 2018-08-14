package animation;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * this is the constuctor of PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // filling the backround
        d.setColor(new Color(255, 255, 153));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 51, 51));
        d.fillRectangle(140, 330, 530, 50);
        d.drawText(120, d.getHeight() / 2, "paused -- press space to continue", 40);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}