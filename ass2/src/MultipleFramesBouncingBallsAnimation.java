import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class MultipleFramesBouncingBallsAnimation {

    /**
    *this method creates 2 rectangles.
    * @param g - as DrawSurface
    */
    public static void paints(DrawSurface g) {
        g.setColor(Color.GRAY);
        g.fillRectangle(50, 50, 450, 450);
        g.setColor(Color.YELLOW);
        g.fillRectangle(450, 450, 150, 150);
    }
    /**
     * the method changes an array of strings into an array of int.
     * @param numbers
     *            - an array of strings.
     * @param length1 - the length of the first limit
     * @param width1 - the width of the first limit
     * @param length2 - the length of the second limit
     * @param width2 - the width of the second limit
     * @return a new array of integers.
     * @throws Exception - if the radius' values don't fit
     */
    public static int[] stringsToInts(String[] numbers, int length1,
            int width1, int length2, int width2)
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
                throw new Exception(" Zero negative number is not allowed ");
            }
            if (j < numbers.length / 2) {
                if ((arr[j] * 2 >= length1) || (arr[j] * 2 >= width1)) {
                    throw new Exception(" The circle's diameter is"
                            + " bigger than the limit");
                }
            } else {
                if ((arr[j] * 2 >= length2) || (arr[j] * 2 >= width2)) {
                    throw new Exception(" The circle's diameter is"
                            + " bigger than the limit");
            }
        }
        }
        return arr;
    }
    /**
     * this method runs the program.
     * @param args
     *            input from the user
     */
    public static void runProgram(String[] args) {
        // creating a gui
        GUI gui = new GUI("title", 600, 600);
        DrawSurface d = gui.getDrawSurface();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
       try {
           int[] array = stringsToInts(args, 450, 450, 150, 150);
              // creating an array at the size of array (numbers of given radius)
              Ball[] ballArr = new Ball[array.length];
              // a for loop to go over all over the balls
              for (int i = 0; i < array.length; i++) {
                  Random rand = new Random();
                  // if i is less than array.length/2, the ball should
                  // be in the first frame
                  if (i < array.length / 2) {
                  // ballots a number to be the x coordinate
                  int x = rand.nextInt(450 - 2 * array[i]) + array[i] + 50;
                  // ballots a number to be the y coordinate
                  int y = rand.nextInt(450 - 2 * array[i]) + array[i] + 50;
                  Point firstBound1 = new Point(50, 50);
                  Point secondBound1 = new Point(500, 500);
                  // creating a ball
                  ballArr[i] = new Ball(x, y, array[i], Color.BLACK,
                          firstBound1, secondBound1);
                  } else { // the ball should be in the second frame
                      int x = rand.nextInt(150 - 2 * array[i]) + array[i] + 450;
                      // ballots a number to be the y coordinate
                      int y = rand.nextInt(150 - 2 * array[i]) + array[i] + 450;
                      Point firstBound2 = new Point(450, 450);
                      Point secondBound2 = new Point(600, 600);
                      // creating a ball
                      ballArr[i] = new Ball(x, y, array[i],
                              Color.BLACK, firstBound2, secondBound2);
                  }
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
                  d = gui.getDrawSurface();
                  paints(d);
                  // for every ball in the arrays
                  for (int j = 0; j < array.length; j++) {
                      ballArr[j].moveOneStep();
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