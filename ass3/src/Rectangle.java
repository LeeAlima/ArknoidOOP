import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * a constructor for Rectangle.
     * @param upperLeft - as the upperLeft point of the rectangle
     * @param width - as the rectangle's width
     * @param height - as the rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * this function return the upper and right point of the rectangle.
     * @return a new Point
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * this function return the lower and left point of the rectangle.
     * @return a new Point
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * this function return the lower and right point of the rectangle.
     * @return a new Point
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getX() + this.height);
    }

    /**
     * this method finds the intersection points of a line and the rectangle and
     * returns a list of those points.
     * @param line - as the line
     * @return a list of points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // creating a list for the points
        java.util.List<Point> listOfPoints = new ArrayList<Point>();
        Point p1UpperLeft = this.upperLeft;
        Point p2UpperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point p3LowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point p4LowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        // creating the left line
        Line l1 = new Line(p1UpperLeft, p3LowerLeft);
        // creating the upper line
        Line l2 = new Line(p1UpperLeft, p2UpperRight);
        // creating the right line
        Line l3 = new Line(p2UpperRight, p4LowerRight);
        // creating the lower line
        Line l4 = new Line(p4LowerRight, p3LowerLeft);
        Point intersectionPoint1 = line.intersectionWith(l1);
        if (intersectionPoint1 != null) {
            listOfPoints.add(intersectionPoint1);
        }
        Point intersectionPoint2 = line.intersectionWith(l2);
        if (intersectionPoint2 != null) {
            listOfPoints.add(intersectionPoint2);
        }
        Point intersectionPoint3 = line.intersectionWith(l3);
        if (intersectionPoint3 != null) {
            listOfPoints.add(intersectionPoint3);
        }
        Point intersectionPoint4 = line.intersectionWith(l4);
        if (intersectionPoint4 != null) {
            listOfPoints.add(intersectionPoint4);
        }
        // return the list of points
        return listOfPoints;
    }

    /**
     * this method return the rectangle's width.
     * @return the rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this method return the rectangle's height.
     * @return the rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * this method return the upper and left point of the rectangle.
     * @return the upperLeft point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this method sets the upperLeft point.
     * @param p - as a point
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * this method calculates the upper line of the rectangle.
     * @return Line - the upper line
     */
    public Line upperLine() {
        return new Line(this.upperLeft, new Point(this.width + this.upperLeft.getX(), this.upperLeft.getY()));
    }

    /**
     * this method calculates the lower line of the rectangle.
     * @return Line - the lower line
     */
    public Line lowerLine() {
        return new Line(new Point(this.upperLeft.getX(), this.height + this.upperLeft.getY()),
                new Point(this.width + this.upperLeft.getX(), this.height + this.upperLeft.getY()));
    }

    /**
     * this method calculates the left line of the rectangle.
     * @return Line - the left line
     */
    public Line leftSideLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.height + this.upperLeft.getY()));
    }

    /**
     * this method calculates the right line.
     * @return Line- the right line
     */
    public Line rightSideLine() {
        return new Line(new Point(this.width + this.upperLeft.getX(), this.upperLeft.getY()),
                new Point(this.width + this.upperLeft.getX(), this.height + this.upperLeft.getY()));
    }
}