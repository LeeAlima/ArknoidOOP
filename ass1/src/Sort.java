/**
* @author Lee alima <leealima28@gmail.com>
* @version 1.6
* @since 2010-03-31
*  */
public class Sort {
    /**
    *
    * @param args input from the user
    */
    public static void main(String[] args) {
        int[] array = stringsToInts(args);
        array = bubbleSort(array);
        int i;
        if (args[0].equals("desc")) {
            for (i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        } else if (args[0].equals("asc")) {
            for (i = array.length - 1; i >= 0; i--) {
                System.out.print(array[i] + " ");
            }
        }
         System.out.println("");
    }
    /**
     * the function changes an array of strings
     * into an array of int.
     * @param numbers - an array of strings.
     * @return a new array of integers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length - 1];
        for (int i = 0; i < numbers.length - 1; i++) {
            arr[i] = Integer.parseInt(numbers[i + 1]);
        }
        return arr;
    }
    /**
     * the function receives an array and return
     * its the array sorted.
     * @param numbers - an array of integers.
     * @return a sorted array.
     */
    public static int[] bubbleSort(int[] numbers) {
        int j;
        boolean flag = true;
        int temp;
        while (flag) {
            flag = false;
            for (j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    flag = true;
                }
            }
        }
        return numbers;
    }
}
