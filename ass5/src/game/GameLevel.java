package game;

import biuoop.GUI;
import collision.Collidable;
import collision.HitListener;
import collision.Paddle;
import collision.Velocity;
import counter.CountdownAnimation;
import counter.Counter;
import counter.ScoreTrackingListener;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.Ball;
import collision.BallRemover;
import sprite.Block;
import collision.BlockRemover;
import sprite.LivesIndicator;
import sprite.NameOfLevel;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import animation.Animation;
import animation.AnimationRunner;
import animation.PauseScreen;
import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment gameEnviroment;
    private GUI gui;
    private int width;
    private int hight;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * this method is the constructor of GameLevel.
     * @param levelInformation
     *            - as an object of LevelInformation type
     * @param keyboard
     *            - as the KeyboardSensor
     * @param runner
     *            - as an AnumationRunner object
     * @param score
     *            - as a counter that counts the score
     * @param lives
     *            - as a countet that counts the lives
     * @param gui
     *            - as the GUI obeject
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor keyboard, AnimationRunner runner,
            Counter score, Counter lives, GUI gui) {
        this.sprites = new SpriteCollection();
        this.gameEnviroment = new GameEnvironment();
        this.width = 800;
        this.hight = 600;
        this.levelInformation = levelInformation;
        this.counterBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());
        this.counterBalls = new Counter(0);
        this.score = score;
        this.lives = lives;
        this.running = true;
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
    }

    /**
     * this method return the number of blocks.
     * @return the number of blocks
     */
    public int getBlockNumber() {
        return this.counterBlocks.getValue();
    }

    /**
     * this method add a collidable to gameEnviroment.
     * @param c
     *            - as a collidable
     */
    public void addCollidable(Collidable c) {
        this.gameEnviroment.addCollidable(c);
    }

    /**
     * this method add sprite to Sprites.
     * @param s
     *            - as a sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method removes a collidable frome the gameEnviroment.
     * @param c
     *            - as a collidable
     */
    public void removeCollidable(Collidable c) {
        this.gameEnviroment.removeCollidable(c);
    }

    /**
     * this method removes a sprite from sprites.
     * @param s
     *            - as a sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method initializes the game, in this method there is the creating of
     * the objects and adding it to the game.
     */
    public void initialize() {
        // drawing the backround
        addSprite(this.levelInformation.getBackground());
        // calling createBlocks() method to create Blocks
        createBlocks();
        // calling createFrameBlocks() to create the frame Blocks
        createFrameBlocks();
        // creating the score indicator
        createScoreIndicator();
        // creating the death zone
        createDeathZone();
        // creating the lives indicators
        createLivesIndicator();
        // drawing the name of the level
        drawNameOfLevel();
    }

    /**
     * this method draws the name of the level at the top of the screen.
     */
    public void drawNameOfLevel() {
        // creating a new NameOfLevel object with the name of the level
        NameOfLevel name = new NameOfLevel(new Rectangle(new Point(0, 0), this.width, this.hight / 32),
                new Color(150, 153, 153), this.levelInformation.levelName());
        // adding name to the game and drawing it
        name.addToGame(this);
        name.drawOn(gui.getDrawSurface());
    }

    /**
     * this method creates the lives indicator and draws the lives at the top of
     * the screen.
     */
    public void createLivesIndicator() {
        // creating a new LivesIndicator object with the current number of lives
        LivesIndicator livesIndicator = new LivesIndicator(new Rectangle(new Point(0, 0), this.width, this.hight / 32),
                new Color(150, 153, 153), lives);
        // adding livesIndicator to the fame and drawing it
        livesIndicator.addToGame(this);
        livesIndicator.drawOn(gui.getDrawSurface());
    }

    /**
     * this method creates the score indicator and draws the score at the top of
     * the screen.
     */
    public void createScoreIndicator() {
        // creating a new ScoreIndicator object with the current number of score
        ScoreIndicator scoreS = new ScoreIndicator(new Rectangle(new Point(0, 0), this.width, this.hight / 32),
                new Color(150, 153, 153), score);
        // adding scoreS to the game and drawing it
        scoreS.addToGame(this);
        scoreS.drawOn(gui.getDrawSurface());

    }

    /**
     * this method creates the game's blocks.
     */
    public void createBlocks() {
        for (Block b : this.levelInformation.blocks()) {
            // adding the block remover listener
            HitListener listener = new BlockRemover(this, counterBlocks);
            // adding the block to the listener
            b.addHitListener(listener);
            // adding the scoreTrackingListener listener
            HitListener scoreListener = new ScoreTrackingListener(score);
            // adding the block to the listener
            b.addHitListener(scoreListener);
            // than adding it to the game
            b.addToGame(this);
        }
    }

    /**
     * this method creates the paddle.
     * @param keyB
     *            - as the keyboard sensor
     */
    public void createPaddle(biuoop.KeyboardSensor keyB) {
        // creating a paddle's object
        Paddle paddleP = new Paddle(keyB,
                new Rectangle(new Point(this.width / 2 - this.levelInformation.paddleWidth() / 2, 0.95 * this.hight),
                        this.levelInformation.paddleWidth(), this.hight / 27),
                Color.BLACK, new Point(this.width / 30, 0), new Point(this.width * 29 / 30, 0),
                this.levelInformation.paddleSpeed());
        // than adding it to the game
        paddleP.addToGame(this);
        this.paddle = paddleP;
    }

    /**
     * this method creates new frame blocks and add it to the game.
     */
    public void createFrameBlocks() {
        // creating the left block
        Block b1 = new Block(new Rectangle(new Point(0, this.hight / 30), this.width / 30, this.hight * 29 / 30),
                Color.BLACK, 1);
        b1.addToGame(this);
        // creating the right block
        Block b2 = new Block(
                new Rectangle(new Point(this.width * 29 / 30, this.hight / 30), this.width / 30, this.hight * 29 / 30),
                Color.BLACK, 1);
        b2.addToGame(this);
        // creating the upper block
        Block b3 = new Block(new Rectangle(new Point(0, this.hight / 32), this.width, this.hight / 30), Color.BLACK, 1);
        b3.addToGame(this);
    }

    /**
     * this method creates the death - zone - block.
     */
    public void createDeathZone() {
        java.awt.Color colorC = new Color(192, 192, 192);
        Block deathZoneBlock = new Block(
                new Rectangle(new Point(this.width / 30, this.hight), this.width * 14 / 15, this.hight / 30), colorC,
                1);
        // adding the block to the game
        deathZoneBlock.addToGame(this);
        // adding a listener of BallRemover
        HitListener listener = new BallRemover(this, counterBalls);
        // adding the deathZoneBlock to the listener
        deathZoneBlock.addHitListener(listener);
    }

    /**
     * this method creates new balls and add them to the game.
     */
    public void createBalls() {
        // getting the number of balls
        int numberOfBalls = this.levelInformation.numberOfBalls();
        // creating a list of balls
        List<Ball> listOfBalls = new ArrayList<Ball>();
        // creating a list of velocities with the velocities in
        // initialBallVelocities
        List<Velocity> listOfVelocity = this.levelInformation.initialBallVelocities();

        // in each iteration i add a new ball to the list of balls with the
        // velocity
        // that was given in the list of velocities.
        // than i added the ball to the game
        for (int i = 0; i < numberOfBalls; i++) {
            listOfBalls.add(new Ball(this.width / 2, (int) (this.hight * 0.95 - 5), 6, Color.BLACK));
            listOfBalls.get(i).setVelocity(listOfVelocity.get(i));
            listOfBalls.get(i).setEnviroment(this.gameEnviroment);
            listOfBalls.get(i).addToGame(this);
        }
        // i incresed the number of balls
        this.counterBalls.increase(numberOfBalls);
    }

    /**
     * this method plays one turn.
     */
    public void playOneTurn() {
        this.running = true;
        // creating the balls
        this.createBalls();
        // creating the paddle
        createPaddle(keyboard);
        // running the program and activating the counting from 3 to 1
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.runner.run(this);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // if the number of blocks is 0
        if (this.counterBlocks.getValue() == 0) {
            // adding 100 points to the score
            score.increase(100);
            // removing the paddle object
            paddle.removeFromGame(this);
            this.running = false;
        }
        // if the number of balls is 0
        if (this.counterBalls.getValue() == 0) {
            // remiving the paddle object
            paddle.removeFromGame(this);
            this.running = false;
        }
        /// drawing all of the sprites
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // if the user pressed "p" - activating the pauseScreen
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !(this.running);
    }
}