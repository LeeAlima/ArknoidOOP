package game;
import java.util.ArrayList;
import java.util.List;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class GameEnvironment {

    private List<Collidable> listCollision;

    /**
     * the constructor of GameEnviroment.
     */
    public GameEnvironment() {
        this.listCollision = new ArrayList<Collidable>();
    }

    /**
     * this method add the given collidable to the environment.
     * @param c - as a collidable
     */
    public void addCollidable(Collidable c) {
        listCollision.add(c);
    }

    /**
     * this method removes a given collidable.
     * @param c - as a collidable
     */
    public void removeCollidable(Collidable c) {
        listCollision.remove(c);
    }

    /**
     * this function calculates and finds the closest collision recording the
     * line and returns collisionInfo of it.
     * @param trajectory - as the line
     * @return ColiisionInfo based on the list of points and list of collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int j = -1;
        // declaring lowest as the max number
        double lowest = Double.MAX_VALUE;
        // creating a list of points
        List<Point> listCollisionPoints = new ArrayList<Point>();

        List<Collidable> doubleCol = new ArrayList<Collidable>(this.listCollision);

        // adding all of the collidable to the list
        for (Collidable c : doubleCol) {
            listCollisionPoints.add(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
        }
         /*calculating the lowest distance between a point in the
         list to the start point of the line.*/
        for (int i = 0; i < listCollisionPoints.size(); i++) {
            if (listCollisionPoints.get(i) != null) {
                double distance = listCollisionPoints.get(i).distance(trajectory.start());
                if (distance < lowest) {
                    j = i;
                    lowest = distance;
                }
            }
        }
        // if j = -1 it means the list is empty
        if (j == -1) {
            return null;
        }
        // the function return a new object of collisionInfo
        return new CollisionInfo(listCollisionPoints.get(j), listCollision.get(j));
    }
}