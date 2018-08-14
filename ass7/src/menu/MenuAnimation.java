package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 * @param <T> - as the task parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private String nameOfMenu;
    private List<String> keys;
    private List<String> messages;
    private List<T> returnValues;
    private T status;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * this is the constructor for MenuAnimation.
     * @param nameOfMenu - as the name of the task
     * @param keyboard - as the keyboard sensor
     */
    public MenuAnimation(String nameOfMenu, KeyboardSensor keyboard) {
        this.nameOfMenu = nameOfMenu;
        this.keyboard = keyboard;
        this.returnValues = new ArrayList<T>();
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.stop = false;
    }

    /**
     * this method draws nicely a string.
     * @param d - as the DrawSurface
     * @param x - as the x locataion
     * @param y - as the y location
     * @param s - as the string
     * @param size - as the font size
     */
    public void drawText(DrawSurface d, int x, int y, String s, int size) {
        d.setColor(Color.BLACK);
        d.drawText(x + 1, y, s, size);
        d.drawText(x - 1, y, s, size);
        d.drawText(x, y + 1, s, size);
        d.drawText(x, y - 1, s, size);

    }

    /**
     * this method do one frame.
     * @param d - as the DrawSurface parameter
     * @param dt - as the dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // drawing the backround
        int x = 0;
        for (int i = 0; i < 6; i++) {
            d.setColor(getColor(i));
            d.fillRectangle(0 + x, 0, 134, 600);
            x += 134;
        }
        // drawing the name of the nemu
        d.setColor(Color.BLACK);
        drawText(d, 100, 100, this.nameOfMenu, 50);
        d.setColor(Color.white);
        d.drawText(100, 100, this.nameOfMenu, 50);

        // drawing the keys and values
        int j = 200;
        for (int i = 0; i < this.returnValues.size(); i++) {
            d.setColor(Color.black);
            drawText(d, 150, j, ("(" + this.keys.get(i) + ")" + " " + this.messages.get(i)), 30);
            d.setColor(Color.white);
            d.drawText(150, j, ("(" + this.keys.get(i) + ")" + " " + this.messages.get(i)), 30);
            j += 50;
        }
        for (int i = 0; i < this.returnValues.size(); i++) {
            if (this.keyboard.isPressed(this.keys.get(i))) {
                this.status = this.returnValues.get(i);
                this.stop = true;
            }
        }

    }

    /**
     * this method returns the stop variable of the class.
     * @return the value of stop
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * this method add a selection.
     * @param key - as a key
     * @param message - as a message
     * @param returnVal -as a value to return
     */
    public void addSelection(String key, String message, T returnVal) {
        // add the key to the list of keys
        this.keys.add(key);
        // add the message to the list of message
        this.messages.add(message);
        // add the return value to the list of returnValues
        this.returnValues.add(returnVal);
    }

    /**
     * this method return the status.
     * @return T - as the status to return
     */
    public T getStatus() {
        return status;
    }

    /**
     * this method return a color based on an index.
     * @param i - as an the index.
     * @return color
     */
    public java.awt.Color getColor(int i) {
        if (i == 0 || i == 13) {
            return new Color(255, 153, 153);
        }
        if (i == 1 || i == 12) {
            return new Color(255, 204, 153);
        }
        if (i == 2 || i == 11) {
            return new Color(255, 255, 153);
        }
        if (i == 3 || i == 10) {
            return new Color(204, 255, 153);
        }
        if (i == 4 || i == 9) {
            return new Color(153, 255, 153);
        }
        if (i == 5 || i == 8) {
            return new Color(153, 255, 204);
        }
        if (i == 6 || i == 7) {
            return new Color(153, 204, 255);
        }
        return null;
    }

    @Override
    public void setStop(boolean b) {
        this.stop = b;
    }
}
