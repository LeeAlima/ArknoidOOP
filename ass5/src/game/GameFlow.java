package game;

import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counter.Counter;
import levels.LevelInformation;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class GameFlow {
    private int width;
    private int hight;
    private GUI gui;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;
    private Counter lives;
    private Boolean win;

    /**
     * this is the constructor of GameFlow.
     * @param width
     *            - as the game width
     * @param hight
     *            - as the game hight
     */
    public GameFlow(int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.gui = new GUI("arknoid", this.width, this.hight);
        this.ks = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.ar = new AnimationRunner(gui);
        this.win = false;
    }

    /**
     * this method runs the game's levels.
     * @param levels
     *            - as a list of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {
        // running over a list of levels
        for (LevelInformation levelInfo : levels) {
            // creating an GameLevel object
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.score, this.lives,
                    this.gui);
            // initialize the level
            level.initialize();
            // a loop - while there are blocks and there are lives
            while (level.getBlockNumber() > 0 && this.lives.getValue() > 0) {
                // playing a turn
                level.playOneTurn();
                // if the turn ended with more than 0 blocks
                if (level.getBlockNumber() > 0) {
                    this.lives.decrease(1);
                }
            }
            // if the user used all of the lives
            if (lives.getValue() == 0) {
                // running an end screen
                this.ar.run(new EndScreen(this.ks, 1, score.getValue(), this.gui, this.win));
            }
        }
        // if the method got into here - it means the user won
        this.win = true;
        // running an end screen
        this.ar.run(new EndScreen(this.ks, 2, score.getValue(), this.gui, this.win));
    }
}