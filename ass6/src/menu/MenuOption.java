package menu;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 * @param <T> - as the task parameter
 */
public class MenuOption<T> {

    private String key;
    private String message;
    private T returnValue;

    /**
     * this is the constructor for MenuOption.
     * @param key - as a key
     * @param message - as a message
     * @param returnValue - as a task to return
     */
    public MenuOption(String key, String message, T returnValue) {
        this.key = key;
        this.message = message;
        this.returnValue = returnValue;
    }

    /**
     * this method returns the key.
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * this method returns the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * this method returns the the menu value.
     * @return the value
     */
    public T getReturnValue() {
        return returnValue;
    }

}
