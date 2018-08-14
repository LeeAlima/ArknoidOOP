package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class KeyPressStoppableAnimation implements Animation {
    private Boolean stop;
    private Boolean alreadyPressed;
    private boolean newMenu;
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;

    /**
     * this is the constructor for KeyPressStoppableAnimation.
     * @param sensor - as the keyboardsensor
     * @param key - as a key
     * @param animation - as the Animation object
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.stop = false;
        this.newMenu = false;
        this.alreadyPressed = true;
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // do one frame
        this.animation.doOneFrame(d, dt);
        // if the key was pressed and it's the first time it pressed
        if (this.keyboardSensor.isPressed(this.key) && !alreadyPressed) {
            this.stop = true;
        } else if (!this.keyboardSensor.isPressed(this.key)) {
            this.alreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        // if it's new Manu and stop is true
        if (this.newMenu && this.stop) {
            this.newMenu = false;
            this.stop = false;
            return this.stop;
        }
        // if it's not a newMenu and stop is true
        if (!(this.newMenu) && this.stop) {
            this.newMenu = true;
            return this.stop;
        }
        return this.stop;
    }
}