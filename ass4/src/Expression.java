import java.util.List;
import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public interface Expression {

    /**
     * this method evaluate the expression using the variable values provided in
     * the assignment and return the result.
     * @param assignment - as a map
     * @return double - a number after the calculation
     * @throws Exception - when something is wrong
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * this method creates an empty list - and sends it to other evaluate
     * function.
     * @return double - a number after the calculation
     * @throws Exception - when something is wrong
     */
    double evaluate() throws Exception;

    /**
     * this method returns a list of the variables in the expression.
     * @return List - of string
     */
    List<String> getVariables();

    /**
     * this method returns a nice string representation of the expression.
     * @return String - new string that represents the expression
     */
    String toString();

    /**
     * this method returns a new expression in which all occurrences of the
     * variable var are replaced with the provided expression.
     * @param var
     *            - as a var to assign to
     * @param expression
     *            - as tn expression to assign on
     * @return new Exprssion - after the assignment
     */
    Expression assign(String var, Expression expression);

    /**
     * this method differentiates the expression.
     * @param var
     *            - the var to differentia
     * @return Expression - new expression after the differntiate
     */
    Expression differentiate(String var);

    /**
     * this method simplfy the expression.
     * @return Expression - after the simplification
     */
    Expression simplify();

    /**
     * this method is a boolean method which checks if the expression type is
     * comitative.
     * @return true if the expression is comitative
     */
    boolean isComitative();

    /**
     * this function return the expression from right to left.
     * @return String - from the right argument to the left argument
     */
    String getFixedString();
}