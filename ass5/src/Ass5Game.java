import java.util.ArrayList;
import java.util.List;
import game.GameFlow;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ass5Game {

    /**
     * this method adds levels to the list of LevelInformation.
     * @param list - as an empty list
     * @return list - with the 4 game's levels
     */
    public static List<LevelInformation> addToList(List<LevelInformation> list) {
        list.add(new DirectHit(800, 600));
        list.add(new WideEasy(800, 600));
        list.add(new Green3(800, 600));
        list.add(new FinalFour(800, 600));
        return list;
    }

    /**
     * this is the main method which runs the program.
     * @param args
     *            - as the args for main
     */
    public static void main(String[] args) {
        // creating a list for the levels to be played
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // creating a game object with the right size
        GameFlow game = new GameFlow(800, 600);
        // if no arguments were ran - i added one time each level
        if (args.length == 0) {
            levels = addToList(levels);
            // if 1 argument or more were ran with
        } else {
            int number = args.length;
            // going over all of the Strings in the arguments
            for (String s : args) {
                // if the argument is 1 - i added the "Direct Hit" level to the
                // game
                if (s.valueOf(1).equals(s)) {
                    levels.add(new DirectHit(800, 600));
                    number--;
                    // if the argument is 2 - i added the "Wide Easy" level to
                    // the game
                } else if (s.valueOf(2).equals(s)) {
                    levels.add(new WideEasy(800, 600));
                    number--;
                    // if the argument is 3 - i added the "Green 3" level to the
                    // game
                } else if (s.valueOf(3).equals(s)) {
                    levels.add(new Green3(800, 600));
                    number--;
                    // if the argument is 4 - i added the "Final Four" level to
                    // the game
                } else if (s.valueOf(4).equals(s)) {
                    levels.add(new FinalFour(800, 600));
                    number--;
                }
            } // the args list is not empty but inclues only unvalid values
            if (number == args.length) {
                levels = addToList(levels);
            }
        }
        // runing the game with the requested levels
        game.runLevels(levels);
    }
}