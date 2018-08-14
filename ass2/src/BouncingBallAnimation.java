import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class BouncingBallAnimation {

    /**
    *
    * @param args
    *            input from the user
    */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 300, 400);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Point firstBound = new Point(0, 0);
        Point secondBound = new Point(300, 400);
        // creating a ball with these values
        Ball ball = new Ball(0 , 0 , 30 , java.awt.Color.BLACK
                , firstBound, secondBound);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }

    }
}
