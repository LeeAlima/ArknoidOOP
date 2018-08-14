/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Ass3Game {
    /**
     * this is the main method which runs the program.
     * @param args - as the args for main
     */
    public static void main(String[] args) {
        // creating a Game object called game
        Game game = new Game(600, 600);
        // initializing the game
        game.initialize();
        // running the game
        game.run();
    }
}
