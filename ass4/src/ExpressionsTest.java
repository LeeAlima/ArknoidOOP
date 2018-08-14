import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class ExpressionsTest {
    /**
     * this method runs a test.
     * @param args
     *            - as the args from the main(none).
     * @throws Exception - when something is wrong
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Mult(2, "x"), new Plus(new Sin(new Mult(4, "y")), new Pow("e", "x")));
        System.out.println(e);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", (double) 2);
        assignment.put("y", (double) 0.25);
        assignment.put("e", (double) 2.71);
        double value = e.evaluate(assignment);
        System.out.println(value);
        assignment.clear();
        e = e.assign("e", new Num(Math.E));
        System.out.println(e.differentiate("x"));
        assignment.put("x", (double) 2);
        assignment.put("y", (double) 0.25);
        assignment.put("e", (double) Math.E);
        e = e.assign("e", new Num(Math.E));
        System.out.println(e.differentiate("x").evaluate(assignment));
        System.out.print(e.differentiate("x").simplify());
    }
}
