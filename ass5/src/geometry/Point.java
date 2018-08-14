package geometry;
/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Point {
    private double x;
    private double y;

    /**
     * Construct a point by given x and y elements.
     * @param x
     *            - the x element
     * @param y
     *            - the y element
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x element.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y element.
     */
    public double getY() {
        return this.y;
    }

    /**
     * this method sets the value of x.
     * @param newX - as the x to set
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * this method sets the value of y.
     * @param newY - as the y to set
     */
    public void setY(double newY) {
        this.y = newY;
    }


    /**
     * this method calculate the distance between 2 points.
     * @param other
     *            - a point to measure the distance between 2 points.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        // calculating the dx coordinate
        double dx = this.x - other.getX();
        // calculating the dy coordinate
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * this method checks if 2 points are the equals.
     * @param other
     *            - as another point
     * @return True if the two point are equals and False otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        // if the points are not the same point.
        return false;
    }
}
