package sprite;

import java.awt.Color;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class NameOfLevel implements Sprite {
    private Rectangle rect;
    private java.awt.Color color;
    private String name;

    /**
     * this is the constructor of NameOfLevel.
     * @param rect - as a recatangle
     * @param color - as color
     * @param name - as a string
     */
    public NameOfLevel(Rectangle rect, Color color, String name) {
        this.rect = rect;
        this.color = color;
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        String stringScore = "Level Name: " + this.name;
        surface.drawText((int) (x1 + width / 1.5), (int) (y1 + height / 1.5), stringScore, 15);
        surface.setColor(this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * this method add a gameLevel to the game.
     * @param g
     *            - as a GameLevel object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}