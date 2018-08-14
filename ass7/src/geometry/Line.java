package geometry;

import java.util.List;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Line {

    private Point start;
    private Point end;

    /**
     * the first constructor for Line.
     * @param start as the start point.
     * @param end as the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }


    /**
     * the second constructor for Line.
     * @param x1 as the x value of the start point.
     * @param y1 as the y value of the start point.
     * @param x2 as the x value of the end point.
     * @param y2 as the x value of the end point.
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
     * @param p - as the checked point.
     * @return true if the point is in the line, false otherwise.
     */
    public boolean onLine(Point p) {
        double length = this.length();
        double distanceFromStart = this.start.distance(p);
        double distanceFromEnd = this.end.distance(p);
        // error is the differens between the point and its distance from the
        // start and end of the line and the length
        double error = (distanceFromStart + distanceFromEnd) - length;
        return (error <= 0.000001 && error >= -0.000001);
    }

    /**
     * this method checks if two lines intersect.
     * @param other - as another line.
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
     * this function checks if 2 lines intersect and if they do, it returns the
     * intersection points.
     * @param other as another line.
     * @return the intersection point between the both lines, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return null;
        }
        // calculating the x coordinate of the intersection point
        double xIntersection = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        // calculating the y coordinate of the intersection point
        double yIntersection = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        // creating the intersection point
        Point intersectionP = new Point(xIntersection, yIntersection);
        // checking that the point is on both lines
        if (this.onLine(intersectionP) && other.onLine(intersectionP)) {
            return intersectionP;
        } else {
            return null;
        }
    }

    /**
     * this method checks if two lines are equals.
     * @param other - as another line.
     * @return true if the two lines are equals and false otherwise.
     */
    public boolean equals(Line other) {
        // calculating the slope of the current line.
        double currentSlope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        // calculate the y coordinate given a slope and a point.
        double solY = currentSlope * other.start.getX() - currentSlope * this.start.getX() + this.start.getY();
        // checks if the values of the 'y' coordinates are the same.
        if (solY == other.start.getY()) {
            return true;
        }
        return false;
    }

    /**
     * this method calculate the difference between 2 lines' slopes.
     * @param other - as another line.
     * @return d as the difference between 2 lines slope.
     */
    public double differnceBetweenSlope(Line other) {
        double d = (this.start.getX() - this.end.getX()) * (other.start.getY() - other.end.getY())
                - (this.start.getY() - this.end.getY()) * (other.start.getX() - other.end.getX());
        return d;
    }

    /**
     * this method calculate the closest intersection point from a line.
     * @param rect - as a rectangle
     * @return point - the closest Point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // define minD with the max double
        double minD = Double.MAX_VALUE;
        // create a list of points
        List<Point> listOfPoints = rect.intersectionPoints(this);
        // create a point
        Point minPoint = null;
        // if the intersection point is null - return null
        if (rect.intersectionPoints(this) == null) {
            return null;
        } else { // finding the min distance
            for (int i = 0; i < listOfPoints.size(); i++) {
                if (this.start.distance(listOfPoints.get(i)) < minD) {
                    minPoint = listOfPoints.get(i);
                    minD = this.start.distance(listOfPoints.get(i));
                }
            }
        }
        return minPoint;
    }
}