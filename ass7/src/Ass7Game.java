
import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresAnimation;
import game.HighScoresTable;
import game.KeyPressStoppableAnimation;
import game.SpaceInvadersLevel;
import game.Task;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ass7Game {

    /**
     * this is the main method which runs the program.
     * @param args - as the args for main (none)
     */
    public static void main(String[] args) {
        HighScoresTable highScoresTable = null;
        // trying to read the high score file
        try {
            highScoresTable = HighScoresTable.loadFromFile((new File("highScores.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        } // if the table is empty
        if (highScoresTable.getHighScores().isEmpty()) {
            highScoresTable = new HighScoresTable(5);
        }
        final HighScoresTable scoresTable = highScoresTable;
        GUI gui = new GUI("arknoid", 800, 600);
        // creating a runner
        final AnimationRunner runner = new AnimationRunner(gui);
        final KeyboardSensor keyborad = runner.getGui().getKeyboardSensor();
        // creating a menu
        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>("chicken invaders", keyborad);
        mainMenu.addSelection("s", "Start game", new Task<Void>() {
            // run the levels
            public Void run() {
                // creating a level information object to run over finally
                LevelInformation spaceInvader = new SpaceInvadersLevel();
                GameFlow flow = new GameFlow(800, 600, scoresTable, gui, keyborad);
                flow.runLevels(spaceInvader);
                return null;
            }
        }); // the option for watching the high score table
        mainMenu.addSelection("h", "High Scores", new Task<Void>() {
            // show the highScoreAnimation with the scores table
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyborad, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(scoresTable)));
                return null;
            }
        }); // the option of quiting
        mainMenu.addSelection("q", "Exit", new Task<Void>() {
            // q to quit game
            public Void run() {
                System.exit(0);
                return null;
            }
        });
        while (true) {
            // run the main menu
            runner.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            // as long the task is not null - run it.
            if (task != null) {
                task.run();
                mainMenu.setStop(false);
            }
        }

    }
}