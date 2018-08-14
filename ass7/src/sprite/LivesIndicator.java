package sprite;

import java.awt.Color;

import biuoop.DrawSurface;
import counter.Counter;
import game.GameLevel;
import geometry.Rectangle;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class LivesIndicator implements Sprite {

    private Rectangle rect;
    private java.awt.Color color;
    private Counter lives;

    /**
     * this is the constructor for LivesIndicator.
     * @param rect - as a rectangle
     * @param color - as a Color
     * @param lives - as the lives' counter
     */
    public LivesIndicator(Rectangle rect, Color color, Counter lives) {
        this.rect = rect;
        this.color = color;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        int intScore = this.lives.getValue();
        String stringScore = "Lives: " + Integer.toString(intScore);
        surface.drawText((int) (x1 + width / 6), (int) (y1 + height / 1.5), stringScore, 15);
        surface.setColor(this.color);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * this method add a gameLevel to the game.
     * @param g - as a GameLevel object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}