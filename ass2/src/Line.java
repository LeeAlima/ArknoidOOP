/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Line {

    private Point start;
    private Point end;
    private double slope;
    private double freeNumber;

    /**
     * the first constructor for Line.
     * @param start
     *            as the start point.
     * @param end
     *            as the end point.
     */
    public Line(Point start, Point end) {
        this.start = start; // define a start point
        this.end = end; // define an end point
        // define the slope of the line
        this.slope = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        // define the free number in the line equation.
        this.freeNumber = this.start.getY() - (this.slope * this.start.getX());
    }

    /**
     * the second constructor for Line.
     * @param x1
     *            as the x value of the start point.
     * @param y1
     *            as the y value of the start point.
     * @param x2
     *            as the x value of the end point.
     * @param y2
     *            as the x value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // creating the first new point.
        Point newP1 = new Point(x1, y1);
        // creating the second new point.
        Point newP2 = new Point(x2, y2);
        // define newP1 as the start point.
        this.start = newP1;
        // define newP2 as the end point.
        this.end = newP2;
        // define the slope of the line
        this.slope = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        // define the free number in the line equation.
        this.freeNumber = this.start.getY() - (this.slope * this.start.getX());
    }

    /**
     * @return the distance between 2 points .
     */
    public double length() {
        double distance = start.distance(this.end);
        return distance;
    }

    /**
     * the method finds the middle point between 2 points.
     * @return the middle point between 2 points.
     */
    public Point middle() {
        // define the x value of the point
        double midX = (this.start.getX() + this.end.getX()) / 2;
        // define the y value of the point
        double midY = (this.start.getY() + this.end.getY()) / 2;
        // create the middle point
        Point midPoint = new Point(midX, midY);
        return midPoint;
    }

    /**
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method checks if a point is in a segment.
     * @param p
     *            - as the checked point.
     * @return true if the point is in the line, false otherwise.
     */
    public boolean onLine(Point p) {
        // find the max value of x between the 2 points.
        double maxX = Math.max(this.start.getX(), this.end.getX());
        // find the max value of y between the 2 points.
        double maxY = Math.max(this.start.getY(), this.end.getY());
        // find the min value of x between the 2 points.
        double minX = Math.min(this.start.getX(), this.end.getX());
        // find the min value of y between the 2 points.
        double minY = Math.min(this.start.getY(), this.end.getY());
        // checking if the point is between those values.
        if (p.getX() <= maxX && p.getX() >= minX
                && p.getY() <= maxY && p.getY() >= minY) {
            return true;
        }
        // if the point is not in the line - return false.
        return false;
    }

    /**
     * this method checks if two lines intersect.
     * @param other
     *            - as another line.
     * @return true if the lines intersect, false otherwise .
     */
    public boolean isIntersecting(Line other) {
        // define d as the distance between the slopes of the lines.
        double d = this.differnceBetweenSlope(other);
        // if the slopes are the same
        if (d == 0) {
            // return false
            return false;
            // if there is an intersection point between the both lines.
        } else if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * this function checks if 2 lines intersect and if they do,
     * it returns the intersection points.
     * @param other
     *            as another line.
     * @return the intersection point between the both lines, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x, y;
        double d = differnceBetweenSlope(other);
        // if the lines have different slopes.
        if (d != 0) {
            // checking if the lines do not parallel to the axes.
            if (this.start.getX() != this.end.getX()
                    && other.start.getX() != this.end.getX()) {
                x = (other.freeNumber - this.freeNumber)
                        / (this.slope - other.slope);
                y = (this.slope * x) + this.freeNumber;
                // checking if the this line parallel to the y axe.
            } else if (this.start.getX() == this.end.getX()) {
                x = this.start.getX();
                y = (other.slope * x) + other.freeNumber;
            } else {  // checking if the the other line parallel to the y axe.
                x = other.start.getX();
                y = (this.slope * x) + this.freeNumber;
            }
            // create a new point , called - point as the intersection point.
            Point point = new Point(x, y);
            // checking if the point is in the both lines
            if (this.onLine(point) && other.onLine(point)) {
                return point;
            }
        }
        return null;
    }

    /**
     * this method checks if two lines are equals.
     * @param other
     *            - as another line.
     * @return true if the two lines are equals and false otherwise.
     */
    public boolean equals(Line other) {
        // calculating the slope of the current line.
        double currentSlope = (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
        // calculate the y coordinate given a slope and a point.
        double solY = currentSlope * other.start.getX()
                - currentSlope * this.start.getX() + this.start.getY();
        // checks if the values of the 'y' coordinates are the same.
        if (solY == other.start.getY()) {
            return true;
        }
        return false;
    }

    /**
     * this method calculate the difference between 2 lines' slopes.
     * @param other
     *            - as another line.
     * @return d as the difference between 2 lines slope.
     */
    public double differnceBetweenSlope(Line other) {
        double d = (this.start.getX()
                - this.end.getX()) * (other.start.getY() - other.end.getY())
                - (this.start.getY() - this.end.getY())
                * (other.start.getX() - other.end.getX());
        return d;
    }
}