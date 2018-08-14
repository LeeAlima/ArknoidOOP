package menu;

import animation.Animation;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 * @param <T> - as the task parameter
 */
public interface Menu<T> extends Animation {

    /**
     * this method adds a selection.
     * @param key - as a key
     * @param message - as a message
     * @param returnVal - as the return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * this method returns the object status.
     * @return T - as the status
     */
    T getStatus();


    /**
     * this method set the stop member by the boolean paramenter it gets.
     * @param b - as a boolean parameter
     */
    void setStop(boolean b);
}
