package counter;

import java.awt.Color;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long countPerNumber;

    /**
     * this is the constructor for CountdownAnimation.
     * @param numOfSeconds
     *            - as a double
     * @param countFrom
     *            - as a int that represent the counting
     * @param gameScreen
     *            - as a sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countPerNumber = (long) ((this.numOfSeconds / this.countFrom) * 1000);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // drawing all of the sprites
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        // drawing a text to the user
        d.drawText((int) (d.getWidth() / 3), d.getHeight() / 2,
                "The game will start in " + Integer.toString(this.countFrom), 30);
        long startTime = System.currentTimeMillis();
        Sleeper sleeper = new Sleeper();
        long usedTime = System.currentTimeMillis() - startTime;
        long leftTime = this.countPerNumber - usedTime;
        if (leftTime > 0) {
            sleeper.sleepFor(leftTime);
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom >= 0) {
            return false;
        }
        return true;
    }
}