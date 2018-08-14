import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public abstract class BaseExpression {

    /**
     * this method creates an empty list - and sends it to other evaluate method.
     * @return double - a number after the calculation
     * @throws Exception - for wrong calculation
     */
    public double evaluate() throws Exception {
        try {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            return this.evaluate(assignment);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * this method evaluate the expression using the variable values provided in
     * the assignment and return the result.
     * @param assignment - as a map
     * @return double - a number after the calculation
     * @throws Exception notify the user that a problem occurred
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * this method checks for the class type.
     * @return the string of the class type
     */
    abstract String getExpressionString();

    /**
     * this method checks if the the expression can evaluate into a number and
     * return it.
     * @return Expression
     * @throws Exception
     */
    public Expression calculateForNoVar() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}