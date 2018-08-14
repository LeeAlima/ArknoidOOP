package game;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 * @param <T> - as the task parameter
 */
public interface Task<T> {
    /**
     * this method run the task.
     * @return T - as a task
     */
    T run();
}
