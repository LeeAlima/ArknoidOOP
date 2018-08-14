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
     * this method adds a sub menu.
     * @param key - as a key
     * @param message - as a message
     * @param subMenu - as a sub menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * this method set the stop member by the boolean paramenter it gets.
     * @param b - as a boolean parameter
     */
    void setStop(boolean b);

    /**
     * this method set the map of booleans to true. ( to check if a preesing was
     * already pressed).
     * @param s - as a string that represent the key in a map.
     */
    void clicked(String s);

}
