import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Game {

    private SpriteCollection sprites;
    private GameEnvironment gameEnviroment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private int width;
    private int hight;

    /**
     * this method is the constructor for Game.
     * @param width - as the game's width
     * @param hight - as the games' hight
     */
    public Game(int width, int hight) {
        this.sprites = new SpriteCollection();
        this.gameEnviroment = new GameEnvironment();
        this.width = width;
        this.hight = hight;

    }

    /**
     * this method add a collidable to gameEnviroment.
     * @param c - as a collidable
     */
    public void addCollidable(Collidable c) {
        this.gameEnviroment.addCollidable(c);
    }

    /**
     * this method add sprite to Sprites.
     * @param s - as a sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method initializes the game, in this method there is the creating of
     * the objects and adding it to the game.
     */
    public void initialize() {
        // creating a gui
        this.gui = new GUI("arknoid", this.width, this.hight);
        // involving the keyboard sensor
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.sleeper = new Sleeper();
        // calling createBlocks() method to create Blocks
        createBlocks();
        // calling createFrameBlocks() to create the frame Blocks
        createFrameBlocks();
        // calling createBalls() to create balls
        createBalls();
        // creating the paddle
        createPaddle(keyboard);
    }

    /**
     * this method creates the game's blocks.
     */
    public void createBlocks() {
        java.awt.Color randCo = null;
        int numberOfBlocks = 12;
        int x = 0;
        int y = 0;
        int number;
        // the external loop runs on the lines of blocks
        for (int i = 0; i < 6; i++) {
            number = 1;
            randCo = getColor(i);
            numberOfBlocks--;
            // the internal loop runs on the blocks of each line
            for (int j = 0; j < numberOfBlocks; j++) {
                // creating a new block
                if (i == 0) {
                    number = 2;
                }
                Block b = new Block(new Rectangle(new Point(0.9 * this.width + x, 0.1 * this.hight + y),
                        this.width / 15, this.hight / 24), randCo, number);
                // than adding it to the game
                b.addToGame(this);
                x -= this.width / 15;
            }
            x = 0;
            y += this.hight / 24;
        }
    }

    /**
     * this method creates the paddle.
     * @param keyboard - as the keyboard sensor
     */
    public void createPaddle(biuoop.KeyboardSensor keyboard) {
        // creating a paddle's object
        Paddle paddle = new Paddle(keyboard,
                new Rectangle(new Point(this.width * 4.5 / 12, 0.9 * this.hight), this.width / 4, this.hight / 24),
                Color.BLACK, new Point(this.width / 20, 0), new Point(this.width * 19 / 20, 0));
        // than adding it to the game
        paddle.addToGame(this);
    }

    /**
     * this method returns the right color to the match i.
     * @param i - as a number of color
     * @return color
     */
    public java.awt.Color getColor(int i) {
        if (i == 0) {
            return new Color(255, 153, 153);
        }
        if (i == 1) {
            return new Color(255, 204, 153);
        }
        if (i == 2) {
            return new Color(255, 255, 153);
        }
        if (i == 3) {
            return new Color(204, 255, 153);
        }
        if (i == 4) {
            return new Color(153, 255, 153);
        }
        if (i == 5) {
            return new Color(153, 255, 204);
        }
        return null;
    }

    /**
     * this method creates new frame blocks and add it to the game.
     */
    public void createFrameBlocks() {
        // setting the blocks' color
        java.awt.Color colorC = new Color(192, 192, 192);
        Block b1 = new Block(new Rectangle(new Point(0, 0), this.width, this.hight / 30), colorC, 1);
        b1.addToGame(this);
        Block b2 = new Block(new Rectangle(new Point(0, this.hight / 30), this.width / 30, this.hight * 29 / 30),
                colorC, 1);
        b2.addToGame(this);
        Block b3 = new Block(
                new Rectangle(new Point(this.width / 30, this.hight * 29 / 30), this.width * 14 / 15, this.hight / 30),
                colorC, 1);
        b3.addToGame(this);
        Block b4 = new Block(
                new Rectangle(new Point(this.width * 29 / 30, this.hight / 30), this.width / 30, this.hight * 29 / 30),
                colorC, 1);
        b4.addToGame(this);
    }

    /**
     * this method creates new 2 balls and add it to the game.
     */
    public void createBalls() {
        // creating the first ball
        Ball ball1 = new Ball(260, 480, 5, Color.BLACK);
        ball1.setVelocity(4, -5);
        ball1.setEnviroment(this.gameEnviroment);
        ball1.addToGame(this);
        // creating the second ball
        Ball ball2 = new Ball(this.width * 3 / 4, this.hight * 10 / 12, 6, Color.BLACK);
        ball2.setVelocity(6, 5);
        ball2.setEnviroment(this.gameEnviroment);
        ball2.addToGame(this);
    }

    /**
     * this method runs the program and draw and show all of the sprites.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            // creating a variable type DrawSurface
            DrawSurface d = gui.getDrawSurface();
            // d.setColor(Color.black);
            // d.drawRectangle(0, 0, this.width, this.hight);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}