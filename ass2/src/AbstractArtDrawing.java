import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class AbstractArtDrawing {

    /**
     * this method ballots values for 2 points and create a new line from
     * them.
     * @return a line
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        // return a new line by the given coordinates.
        return new Line(x1, y1, x2, y2);
    }

    /**
     * this method draws a black line by given its points values.
     * @param l
     *            - as a line.
     * @param d
     *            - as DrawSurface.
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) (l.start().getX()), (int) (l.start().getY()),
                (int) (l.end().getX()), (int) (l.end().getY()));
    }

    /**
     * this method creates an array of lines and define each line with random
     * points.
     * @return an array of lines.
     */
    public static Line[] lineArr() {
        // creating an array of 10 lines.
        Line[] arr = new Line[10];
        // define each line with random values by calling generateRandomLine.
        for (int i = 0; i < 10; i++) {
            arr[i] = generateRandomLine();
        }
        return arr;
    }

    /**
     * this method draws by red the intersection points between two lines.
     * @param arr
     *            - an array of lines.
     * @param d
     *            - as DrawSurface.
     */
    public static void drawIntersecionPoint(Line[] arr, DrawSurface d) {
        // checks for every line if it has intersection points with every one
        // from the rest lines.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // checks if the lines are the same
                if (!(arr[i].equals(arr[j]))) {
                    // define intersectionPoint as the intersecion
                    //point between the lines.
                    Point intersectionPoint = arr[i].intersectionWith(arr[j]);
                    // if there is an intersection point - prints it by red
                    if (intersectionPoint != null) {
                        d.setColor(Color.RED);
                        d.drawCircle((int) (intersectionPoint.getX()),
                                (int) (intersectionPoint.getY()), 3);
                        d.fillCircle((int) (intersectionPoint.getX()),
                                (int) (intersectionPoint.getY()), 3);
                    }
                }

            }
        }
    }

    /**
     * this function draws the middle point of a segment by blue.
     * @param l
     *            - as a line
     * @param d
     *            - as DrawSurface
     */
    public void drawMiddlePoint(Line l, DrawSurface d) {
        // define middlePoint as the middle point of the segment
        Point middlePoint = l.middle();
        d.setColor(Color.BLUE);
        d.drawCircle((int) (middlePoint.getX()),
                (int) (middlePoint.getY()), 3);
        d.fillCircle((int) (middlePoint.getX()),
                (int) (middlePoint.getY()), 3);
    }

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        // open a gui window
        GUI gui = new GUI("Random Lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        // create a lines array called finalArr
        Line[] finalArr = lineArr();
        // draw every line
        for (int i = 0; i < 10; ++i) {
            example.drawLine(finalArr[i], d);
        }
        // draw the middle point of every segment
        for (int i = 0; i < 10; ++i) {
            example.drawMiddlePoint(finalArr[i], d);
        }
        // draw the intersection points between the all lines
        drawIntersecionPoint(finalArr, d);
        // show gui
        gui.show(d);
    }
}
