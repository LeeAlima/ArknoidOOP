package sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import blocks.Enemy;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class EnemyMovement implements Sprite {

    private int startSheild;
    private double dx;
    private List<Enemy> listOfChickens;
    private ArrayList<LinkedList<Enemy>> arrayOfLinkedList;
    private GameLevel game;

    /**
     * this is the constructor of EnemyMovement.
     * @param startSheild - as an int of the starting of the sheild.
     * @param dx - as a double
     * @param listOfChickens - as the list of enemies
     * @param game - as the GameLevel
     */
    public EnemyMovement(int startSheild, double dx, List<Enemy> listOfChickens, GameLevel game) {
        this.startSheild = startSheild;
        this.dx = dx;
        this.listOfChickens = listOfChickens;
        this.arrayOfLinkedList = new ArrayList<LinkedList<Enemy>>();
        createLinkedLists();
        this.game = game;
    }

    /**
     * @return - a list of enemies that appears a the bottom of colums
     */
    private List<Enemy> findLastInList() {
        List<Enemy> lastInColumns = new ArrayList<>();
        for (int i = 0; i < this.arrayOfLinkedList.size(); i++) {
            if (this.arrayOfLinkedList.get(i).size() > 0) {
                // add the last in colum to the list
                lastInColumns.add((Enemy) this.arrayOfLinkedList.get(i).getLast());
            }
        }
        return lastInColumns;
    }

    /**
     * this method creates a sort of matrix to represent the chickens. in this
     * way it's easier to move and make one shot.
     */
    public void createLinkedLists() {
        LinkedList<Enemy> oneList = new LinkedList<>();
        // create 10 linked list
        for (int j = 0; j < 10; j++) {
            // at the size of 5 for each one
            for (int i = j; i < 50; i += 10) {
                oneList.add(this.listOfChickens.get(i));
            } // add the linked list to the list
            this.arrayOfLinkedList.add(oneList);
            oneList = new LinkedList<>();
        }
    }

    /**
     * update the list of linkes list in case of enemy's delition.
     * @param enemy - as the enemy to remove.
     */
    public void updateLinked(Enemy enemy) {
        for (int i = 0; i < this.arrayOfLinkedList.size(); i++) {
            this.arrayOfLinkedList.get(i).remove(enemy);
        }
        this.arrayOfLinkedList.remove(enemy);
        this.listOfChickens.remove(enemy);
    }

    /**
     * make one shoot from the chickens.
     * @param gEnvironment - as a GameEnviroment object
     */
    public void shoot(GameEnvironment gEnvironment) {
        List<Enemy> lastInColoms = this.findLastInList();
        if (lastInColoms.size() != 0) {
            // choose a random number
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(lastInColoms.size());
            Point p = new Point(lastInColoms.get(index).getCollisionRectangle().getLowerLeft().getX() + 20,
                    lastInColoms.get(index).getCollisionRectangle().getLowerLeft().getY() + 4);
            // make one shoot
            Ball oneShot = new Ball(p, 3, Color.red, gEnvironment, true);
            oneShot.setVelocity(0, 250);
            oneShot.addToGame(game);
            // add the ball to the game
            game.addBall(oneShot);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        return;
    }

    /**
     * initial the enemy at the start location.
     * @param speed - as the initial speed
     */
    public void startAgain(double speed) {
        this.dx = speed;
        for (Enemy enemy : this.listOfChickens) {
            enemy.setRectangle(enemy.returnRect());
        }
        boolean check = true;
        while (check) {
            if (findMinX(50, 1 / 60) > 0) {
                for (Enemy enemy : this.listOfChickens) {
                    double pX = enemy.getCollisionRectangle().getUpperLeft().getX() - 50;
                    double pY = enemy.getCollisionRectangle().getUpperLeft().getY();
                    Point p = new Point(pX, pY);
                    Rectangle r = new Rectangle(p, enemy.getCollisionRectangle().getWidth(),
                            enemy.getCollisionRectangle().getHeight());
                    enemy.setRectangle(r);
                }
            } else {
                check = false;
            }
        }
    }

    @Override
    public void timePassed(double dt) {
        // check if the chickens should move as a unit.
        if (findMinX(dx, dt) > 0 && findMaxX(dx, dt) < 800) {
            for (int i = 0; i < listOfChickens.size(); i++) {
                listOfChickens.get(i).moveBy(dx, dt);
            }
        } else { // the chickens should move one line down
            this.dx = (int) dx * -1.1;
            for (int j = 0; j < this.listOfChickens.size(); j++) {
                double width = this.listOfChickens.get(j).getCollisionRectangle().getWidth();
                double height = this.listOfChickens.get(j).getCollisionRectangle().getHeight();
                double x = this.listOfChickens.get(j).getCollisionRectangle().getUpperLeft().getX();
                double y = this.listOfChickens.get(j).getCollisionRectangle().getUpperLeft().getY();
                // set the rectangle and lower the lines
                this.listOfChickens.get(j).setRectangle(new Rectangle(new Point(x, y + 10), width, height));
            }
        }
    }

    /**
     * this method finds the max value of the x coordinates possible to the
     * chickens' list.
     * @param dx1 - as the dx parameter
     * @param dt - as a double
     * @return - an int
     */
    public double findMaxX(double dx1, double dt) {
        double x = 0;
        for (Enemy e : this.listOfChickens) {
            if (e.getCollisionRectangle().getUpperLeft().getX() + 40 > x) {
                x = e.getCollisionRectangle().getUpperLeft().getX() + 40;
            }
        }
        x += dx1 * dt;
        return x;
    }

    /**
     * this method finds the min value of the x coordinates possible to the
     * chickens' list.
     * @param dx1 - as the dx parameter
     * @param dt - as a double
     * @return - an int
     */
    public int findMinX(double dx1, double dt) {
        int x = 800;
        for (Enemy e : this.listOfChickens) {
            if (e.getCollisionRectangle().getUpperLeft().getX() < x) {
                x = (int) e.getCollisionRectangle().getUpperLeft().getX();
            }
        }
        x += dx1 * dt;
        return x;
    }

    /**
     * this method checks if the chickens touch the sheild.
     * @return true - if the chicken touches the sehild and false otherwise.
     */
    public boolean touchTheSheild() {
        int y = 0;
        List<Enemy> lastInColoms = this.findLastInList();
        for (Enemy e : lastInColoms) {
            if (e.getCollisionRectangle().getLowerLeft().getY() + 80 > y) {
                y = (int) e.getCollisionRectangle().getLowerLeft().getY() + 80;
            }
        }
        if (y >= this.startSheild) {
            return true;
        }
        return false;
    }

    /**
     * this method add this to the game.
     * @param g - as a GameLevel object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
