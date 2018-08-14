import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class MultipleBouncingBallsAnimation {

    /**
     * the method changes an array of strings into an array of int.
     * @param numbers
     *            - an array of strings.
     * @param length - the limit's length
     * @param width - the limit's width
     * @return a new array of integers.
     * @throws Exception - if the radius' values don't fit
     */
    public static int[] stringsToInts(String[] numbers, int length, int width)
            throws Exception {
        if (numbers.length == 0) {
            throw new Exception(" There are no radius's parameters");
        }
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            double number = Double.parseDouble(numbers[i]);
            if (number > 0.5 && number < 1) {
                number = 1;
            }
            arr[i] = (int) number;
        }
        for (int j = 0; j < numbers.length; j++) {
            if (arr[j] < 0) {
                throw new Exception(" negative number is not allowed ");
            }
            if ((arr[j] * 2 >= length) || (arr[j] * 2 >= width)) {
                throw new Exception(" The circle's diameter is"
                        + " bigger than the limit");
            }
        }
        return arr;
    }

    /**
    * this method run the program.
    * @param args
    *            input from the user
    */
    public static void  runProgram(String[] args) {
     // creating a gui
        GUI gui = new GUI("title", 300, 400);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        try {
            int[] array = stringsToInts(args, 300, 400);
            // creating an array called ballArr with the length of array
            Ball[] ballArr = new Ball[array.length];
            // a for loop to go over all of the balls
            for (int i = 0; i < array.length; i += 1) {
                Random rand = new Random();
                // ballots a number to be the x coordinate
                int x = rand.nextInt(300 - 2 * array[i]) + array[i];
                // ballots a number to be the y coordinate
                int y = rand.nextInt(400 - 2 * array[i]) + array[i];
                // creating a ball
                Point firstBound = new Point(0, 0);
                Point secondBound = new Point(300, 400);
                ballArr[i] = new Ball(x, y, array[i], Color.BLACK,
                        firstBound, secondBound);
                // if the ball's size is bigger than 50
                if (ballArr[i].getSize() > 50) {
                    // set its speed as 1
                    Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360) + 1
                            , 5);
                    ballArr[i].setVelocity(v);
                } else {
                    // set the speed in a way to make it slower as the size grows
                    Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360) + 1,
                            ((54 - ballArr[i].getSize()) / 3) + 5);
                    ballArr[i].setVelocity(v);
                }
            }
            // a while loop with the value true to make it infinity
            while (true) {
                DrawSurface d = gui.getDrawSurface();
                // for every ball in the array
                for (int j = 0; j < array.length; j++) {
                    // make a step
                    ballArr[j].moveOneStep();
                    // draw it
                    ballArr[j].drawOn(d);
                }
                gui.show(d);
                sleeper.sleepFor(50); // wait for 50 milliseconds.
            }
        } catch (Exception e) {
            gui.getDialogManager().showErrorDialog("Error", "You have got an"
                    + " error, change your parameters!");
            System.exit(1);
        }
    }
    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        runProgram(args);
    }
}
