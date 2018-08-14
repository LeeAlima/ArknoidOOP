/**
* @author Lee alima <leealima28@gmail.com>
* @version 1.6
* @since 2010-03-31
*  */
public class DescribeNumbers {
    /**
    *
    * @param args input from the user
    */
    public static void main(String[] args) {
        int[] array = stringsToInts(args);
        System.out.println("min: " + min(array));
        System.out.println("max: " + max(array));
        System.out.println("avg: " + avg(array));
    }
    /**
     * the function changes an array of strings
     * into an array of int.
     * @param numbers - an array of strings.
     * @return a new array of integers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }
    /**
     * the function receives an array and returns
     * the minimun number.
     * @param numbers - an array of integers.
     * @return the min number in the array .
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
            continue;
        }
        return min;
    }
    /**
     * the function receives an array and returns
     * the maximum number.
     * @param numbers - an array of integers.
     * @return the max number in the array .
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
            continue;
        }
        return max;
    }
    /**
     * the function receives an array and returns
     * the average of the numbers.
     * @param numbers - an array of integers.
     * @return the average of the array .
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum / numbers.length;
    }
}
