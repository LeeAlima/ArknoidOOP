import game.RunGame;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ass6Game {

    /**
     * this is the main method - which runs the program by checking for
     * arguments in the run cognifiture and runs it or runs a default oprion.
     * @param args - as the arguments from the main
     */
    public static void main(String[] args) {
        String option;
        // if no arguments were added , than runs a default game.
        if (args.length == 0) {
            option = "level_sets.txt";
            // runs the args
        } else {
            option = args[0];
        }

        // creating a RunGame object with the option string
        RunGame play = new RunGame(option);
        // start the game
        play.startGame();
    }
}