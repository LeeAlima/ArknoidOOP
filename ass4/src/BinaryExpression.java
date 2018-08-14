import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

abstract class BinaryExpression extends BaseExpression {

    private Expression leftArgument;
    private Expression rightArgument;

    /**
     * this is the first constructor for a BinaryExpression dealing with 2
     * expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public BinaryExpression(Expression leftArgument, Expression rightArgument) {
        this.setLeftArgument(leftArgument);
        this.setRightArgument(rightArgument);
    }

    /**
     * this is the second constructor for a BinaryExpression dealing with an
     * expression and a double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public BinaryExpression(double leftArgument, Expression rightArgument) {
        this.setLeftArgument(new Num(leftArgument));
        this.setRightArgument(rightArgument);
    }

    /**
     * this is the third constructor for a BinaryExpression dealing with an
     * expression and a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public BinaryExpression(Expression leftArgument, String rightArgument) {
        this.setLeftArgument(leftArgument);
        this.setRightArgument(new Var(rightArgument));
    }

    /**
     * this is the fourth constructor for a BinaryExpression dealing with a
     * string and a double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public BinaryExpression(String leftArgument, double rightArgument) {
        this.setLeftArgument(new Var(leftArgument));
        this.setRightArgument(new Num(rightArgument));
    }

    /**
     * this is the fifth constructor for a BinaryExpression dealing with double
     * and a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public BinaryExpression(double leftArgument, String rightArgument) {
        this.setLeftArgument(new Num(leftArgument));
        this.setRightArgument(new Var(rightArgument));
    }

    /**
     * this is the sixt constructor for a BinaryExpression dealing with 2
     * doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public BinaryExpression(double leftArgument, double rightArgument) {
        this.setLeftArgument(new Num(leftArgument));
        this.setRightArgument(new Num(rightArgument));
    }

    /**
     * this is the seventh constructor for a BinaryExpression dealing with 2
     * strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public BinaryExpression(String leftArgument, String rightArgument) {
        this.setLeftArgument(new Var(leftArgument));
        this.setRightArgument(new Var(rightArgument));
    }

    /**
     * this is the eightt constructor for a BinaryExpression dealing with an
     * expression and a double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public BinaryExpression(Expression leftArgument, double rightArgument) {
        this.setLeftArgument(leftArgument);
        this.setRightArgument(new Num(rightArgument));
    }

    /**
     * this is the ninth constructor for a BinaryExpression dealing with an
     * expression an a string.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - an expression
     */
    public BinaryExpression(String leftArgument, Expression rightArgument) {
        this.setLeftArgument(new Var(leftArgument));
        this.setRightArgument(rightArgument);
    }

    /**
     * this method returns a set of all of the variables in the expression.
     * @return List - of strings (the variables)
     */
    public List<String> getVariables() {
        // creating a list
        List<String> list = new ArrayList<>();
        // creating a set
        Set<String> hs = new HashSet<String>();
        // adding all of the arguments of the left expression
        list.addAll(getLeftArgument().getVariables());
        // adding all of the arguments of the right expression
        list.addAll(getRightArgument().getVariables());
        // adding all of the variables to the set
        hs.addAll(list);
        // clearing the list
        list.clear();
        // adding all of the sets' variables to list
        list.addAll(hs);
        return list;
    }

    /**
     * this method return a nice string of the expression.
     * @return string
     */
    public String toString() {
        return String.format("(%s%s%s)", this.getLeftArgument().toString(), this.getExpressionString(),
                this.getRightArgument().toString());
    }

    /**
     * this method simplfying both arguments of the expression.
     */
    public void simplifyGeneral() {
        this.setRightArgument(this.getRightArgument().simplify());
        this.setLeftArgument(this.getLeftArgument().simplify());
    }

    /**
     * this method return the right argument of the expression.
     * @return rightArgument
     */
    public Expression getRightArgument() {
        return rightArgument;
    }

    /**
     * this method sets the right argument of the expression.
     * @param eR - as an expression
     */
    public void setRightArgument(Expression eR) {
        this.rightArgument = eR;
    }

    /**
     * this method return the left argument of the expression.
     * @return leftArgument
     */
    public Expression getLeftArgument() {
        return leftArgument;
    }

    /**
     * this method sets the left argument of the expression.
     * @param eL - as an expression
     */
    public void setLeftArgument(Expression eL) {
        this.leftArgument = eL;
    }
}