/**
* @author Lee alima <leealima28@gmail.com>
* @version 1.6
* @since 2010-03-31
*  */
public class Factorial {
    /**
    *
    * @param args input from the user
    */
    public static void main(String[] args) {
        long number = Long.parseLong(args[0]);
        System.out.println("recursive: " + factorialRecursive(number));
        System.out.println("iterative: " + factorialIter(number));
    }
    /**
     * the function prints the factorial of a number.
     * @param n - a number as input from the user.
     * @return the factorial of the number recursively .
     */
    public static long factorialRecursive(long n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    /**
     * the function prints the factorial of a number.
     * @param n - a number as input from the user
     * @return the factorial of the number iteratively .
     */
    public static long factorialIter(long n) {
        long mult = 1;
        for (long i = n; i > 0; i--) {
            mult *= i;
        }
        return mult;
    }
}
