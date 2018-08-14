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
public class ScoreIndicator implements Sprite {

    private Rectangle rect;
    private java.awt.Color color;
    private Counter score;

    /**
     * this is the constructor of ScoreIndicator.
     * @param rect - as a rectangle
     * @param color - as a Color
     * @param score - as a score counter
     */
    public ScoreIndicator(Rectangle rect, java.awt.Color color, Counter score) {
        this.rect = rect;
        this.color = color;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        // filling the rectangle
        surface.fillRectangle((int) x1, (int) y1, (int) width, (int) height);
        surface.setColor(Color.BLACK);
        // drawing the rectangle
        surface.drawRectangle((int) x1, (int) y1, (int) width, (int) height);
        surface.setColor(Color.black);
        // draw the string on the surface
        int intScore = this.score.getValue();
        String stringScore = "Score: " + Integer.toString(intScore);
        surface.drawText((int) (x1 + width / 2.2), (int) (y1 + height / 1.5), stringScore, 15);
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