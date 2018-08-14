package counter;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Counter {
    private int number;

    /**
     * this is the constructor of Counter.
     * @param num
     *            - as the counter's number
     */
    public Counter(int num) {
        this.number = num;
    }

    /**
     * this method adds a number to the current count.
     * @param num
     *            - as the number to add
     */
    public void increase(int num) {
        this.number += num;
    }

    /**
     * this method subtract number from the current count.
     * @param num
     *            - as the number to substract
     */
    public void decrease(int num) {
        this.number -= num;
    }

    /**
     * this method returns the current count.
     * @return the value of the counter(int)
     */
    public int getValue() {
        return this.number;
    }
}