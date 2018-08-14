package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * this is the constructor for AnimationRunner.
     * @param g - as a GUI object
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();

    }

    /**
     * this method return the gui object.
     * @return a gui object
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * The method takes an Animation object and runs it.
     * @param animation - as an Animation object
     */
    public void run(Animation animation) {
        // defining the seconds per frame
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            double dt = 1 / (double) framesPerSecond;
            // calling the doOneFram method throught animation
            animation.doOneFrame(d, dt);
            // showing the gui
            gui.show(d);

            // calculating the used time
            long usedTime = System.currentTimeMillis() - startTime;
            // calculating the time to sleep
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}