package game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import biuoop.DialogManager;
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
    private HighScoresTable table;
    private boolean firstTime;

    /**
     * this is the constructor of GameFlow.
     * @param width - as the game width
     * @param hight - as the game hight
     * @param ks - as the keyboard sensor
     * @param gui - as the gui object
     * @param highScoresTable - as the table
     */
    public GameFlow(int width, int hight, HighScoresTable highScoresTable, GUI gui, KeyboardSensor ks) {
        this.width = width;
        this.hight = hight;
        this.gui = gui;
        this.ks = ks;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.ar = new AnimationRunner(gui);
        this.win = false;
        this.table = highScoresTable;
        this.firstTime = true;
    }

    /**
     * this method returns a new game.
     * @return new game
     */
    public GameFlow copyGame() {
        return new GameFlow(this.width, this.hight, this.table, this.gui, this.ks);
    }

    /**
     * this method runs the game's levels.
     * @param levels - as a list of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {
        // running over a list of levels
        for (LevelInformation levelInfo : levels) {
            // creating an GameLevel object
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.score, this.lives, this.gui);
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
            } // if the user lost and it's the first time
            if (lives.getValue() == 0 && firstTime) {
                if (table.getRank(this.score.getValue()) <= this.table.size()
                        || this.table.getHighScores().size() < 5) {
                    // activating the dialog for losers
                    dialogForLosers();
                    this.firstTime = false;
                    return;
                }
                // showing the end screen for losers
                this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(1, score.getValue(), this.win)));
                this.firstTime = false;
            }
        } // if the user ended the levels and has more lives
        if (lives.getValue() != 0) {
            this.win = true;
        }
        // if tht user won and its the first time
        if (win && firstTime) {
            // activating the dialog for winners
            if (table.getRank(this.score.getValue()) <= this.table.size() || this.table.getHighScores().size() < 5) {
                dialogForWinners();
                this.firstTime = false;
                return;
            }
            // showing the end screen for winners
            this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                    new EndScreen(2, score.getValue(), this.win)));
            this.firstTime = false;
        }
        // showing the high scores screen
        this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                (new HighScoresAnimation(this.table))));
        return;
    }

    /**
     * this method activating a dialog for winners.
     */
    public void dialogForWinners() {
        // opening a dialog
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        ScoreInfo user = new ScoreInfo(name, this.score.getValue());
        this.win = true;
        this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                new EndScreen(2, score.getValue(), this.win)));
        // adding the user to the table
        this.table.add(user);
        this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                (new HighScoresAnimation(this.table))));
        // trying to save the score in the table
        try {
            table.save(new File("HighScores.txt"));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method activating a dialog for losers.
     */
    public void dialogForLosers() {
        // opening a dialog
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        ScoreInfo user = new ScoreInfo(name, this.score.getValue());
        this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                new EndScreen(1, score.getValue(), this.win)));
        this.table.add(user);
        this.ar.run(new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                (new HighScoresAnimation(this.table))));
        // trying to save the score in the table
        try {
            table.save(new File("HighScores.txt"));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}