package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import animation.Animation;
import animation.AnimationRunner;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import blocks.Block;
import blocks.Enemy;
import collision.BallRemover;
import collision.BlockRemover;
import collision.Collidable;
import collision.HitListener;
import collision.Paddle;
import counter.CountdownAnimation;
import counter.Counter;
import counter.ScoreTrackingListener;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.Ball;
import sprite.EnemyMovement;
import sprite.LivesIndicator;
import sprite.NameOfLevel;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;

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
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Counter numOfLevel;
    private List<Enemy> enemiesList;
    private List<Block> blockList;
    private int speed;
    private EnemyMovement enemyMovement;
    private List<Ball> listOfBalls;
    private long current;
    private long last;

    /**
     * this method is the constructor of GameLevel.
     * @param levelInformation - as an object of LevelInformation type
     * @param keyboard - as the KeyboardSensor
     * @param runner - as the AnimationRunner object
     * @param oneMap - as a map containing counters
     * @param numOfLevel - as a counter that counts the number of level
     * @param gui - as the GUI obeject
     * @param speed - as the speed of the game;
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor keyboard, AnimationRunner runner,
            Map<String, Counter> oneMap, Counter numOfLevel, GUI gui, int speed) {
        this.sprites = new SpriteCollection();
        this.gameEnviroment = new GameEnvironment();
        this.width = 800;
        this.hight = 600;
        this.levelInformation = levelInformation;
        this.counterBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());
        this.score = oneMap.get("score");
        this.lives = oneMap.get("lives");
        this.numOfLevel = numOfLevel;
        this.running = true;
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.enemiesList = levelInformation.enemies();
        this.blockList = levelInformation.blocks();
        this.speed = speed;
        this.listOfBalls = new ArrayList<>();
        this.last = 0;
        this.current = 0;
    }

    /**
     * this method remove a ball from this.listOfBalls.
     * @param b - as the ball to remove
     */
    public void removeBall(Ball b) {
        this.listOfBalls.remove(b);
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
     * this method removes a collidable frome the gameEnviroment.
     * @param c - as a collidable
     */
    public void removeCollidable(Collidable c) {
        this.gameEnviroment.removeCollidable(c);
    }

    /**
     * this method removes a sprite from sprites.
     * @param s - as a sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method add a ball to this.listOfBalls.
     * @param b - as the ball to add
     */
    public void addBall(Ball b) {
        this.listOfBalls.add(b);
    }

    /**
     * this method initializes the game, in this method there is the creating of
     * the objects and adding it to the game.
     */
    public void initialize() {
        // drawing the backround
        addSprite(this.levelInformation.getBackground());
        // calling createBlocks() method to create Blocks
        createEnemyMovement();
        createBlocks();
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
     * this method creates an EnemyMovement object.
     */
    public void createEnemyMovement() {
        this.enemyMovement = new EnemyMovement(520, this.speed, enemiesList, this);
        this.enemyMovement.addToGame(this);
    }

    /**
     * this method draws the name of the level at the top of the screen.
     */
    public void drawNameOfLevel() {
        // creating a new NameOfLevel object with the name of the level
        NameOfLevel name = new NameOfLevel(new Rectangle(new Point(0, 0), this.width, this.hight / 32),
                new Color(150, 153, 153), "Battle no. " + this.numOfLevel.getValue());
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
        BlockRemover blockRemove;
        ScoreTrackingListener stl;
        stl = new ScoreTrackingListener(this.score);
        HitListener list = new BallRemover(this);
        blockRemove = new BlockRemover(this, this.counterBlocks, enemyMovement);
        for (int i = 0; i < this.enemiesList.size(); i++) {
            this.enemiesList.get(i).addToGame(this);
            this.enemiesList.get(i).addHitListener(blockRemove);
            this.enemiesList.get(i).addHitListener(stl);
            this.enemiesList.get(i).addHitListener(list);
        }
        Collections.reverse(this.enemiesList);
        for (int i = 0; i < this.blockList.size(); i++) {
            this.blockList.get(i).addToGame(this);
            this.blockList.get(i).addHitListener(list);
            this.blockList.get(i).addHitListener(blockRemove);
        }
    }

    /**
     * this method creates the paddle.
     * @param keyB - as the keyboard sensor
     */
    public void createPaddle(biuoop.KeyboardSensor keyB) {
        // creating a paddle's object
        Paddle paddleP = new Paddle(keyB,
                new Rectangle(new Point(this.width / 2 - this.levelInformation.paddleWidth() / 2, 0.95 * this.hight),
                        this.levelInformation.paddleWidth(), this.hight / 27),
                new Point(this.width / 30, 0), new Point(this.width * 29 / 30, 0), this.levelInformation.paddleSpeed(),
                this.gameEnviroment, this);
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

    }

    /**
     * this method creates the death - zone - block.
     */
    public void createDeathZone() {
        java.awt.Color colorC = new Color(192, 192, 192);
        Block deathZoneBlockUp = new Block(
                new Rectangle(new Point(this.width / 30, this.hight), this.width * 14 / 15, this.hight / 30), colorC,
                1);
        Block deathZoneBlockLow = new Block(new Rectangle(new Point(0, this.hight / 32), this.width, this.hight / 30),
                Color.BLACK, 1);
        deathZoneBlockLow.addToGame(this);
        // adding the block to the game
        deathZoneBlockUp.addToGame(this);
        // adding a listener of BallRemover
        HitListener listener = new BallRemover(this);
        // adding the deathZoneBlock to the listener
        deathZoneBlockUp.addHitListener(listener);
        deathZoneBlockLow.addHitListener(listener);
    }

    /**
     * this method reset the game.
     */
    public void createAnewGame() {
        // removing the ball
        if (this.paddle != null) {
            for (Ball b : listOfBalls) {
                this.removeSprite(b);
            }
            this.paddle.clearBalls();
            this.removeCollidable(this.paddle);
            this.removeSprite(this.paddle);
            // decrease the lives counter
            this.lives.decrease(1);
            // clearing the ball from the screen
            this.listOfBalls.clear();
            this.running = false;
        }
        enemyMovement.startAgain(this.speed);
    }

    /**
     * this method plays one turn.
     */
    public void playOneTurn() {
        // enemyMovement.startAgain(this.speed);
        this.running = true;
        createPaddle(keyboard);
        // running the program and activating the counting from 3 to 1
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.runner.run(this);

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // save the current time
        this.current = System.currentTimeMillis();
        // check for shooting
        if (current - last >= 500 && this.counterBlocks.getValue() > 0) {
            this.enemyMovement.shoot(this.gameEnviroment);
            // save the shooting time
            this.last = System.currentTimeMillis();
        }
        // if the number of blocks is 0
        if (this.counterBlocks.getValue() == 0) {
            paddle.removeFromGame(this);
            this.running = false;
        }
        // if the chickens touches the sheild - reset the game
        if (enemyMovement.touchTheSheild()) {
            createAnewGame();
        }
        /// drawing all of the sprites
        this.sprites.notifyAllTimePassed(dt);
        this.sprites.drawAllOn(d);
        // if the user pressed "p" - activating the pauseScreen
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, (new PauseScreen())));
        }

    }

    @Override
    public boolean shouldStop() {
        return !(this.running);
    }
}