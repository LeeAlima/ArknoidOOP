import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Log extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Log dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Log(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Log dealing with an expression and a
     * double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Log(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Log dealing with an expression and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Log(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Log dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Log(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Log dealing with double and a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Log(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Log dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Log(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Log dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Log(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Log dealing with an expression and a
     * double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Log(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the ninth constructor for a Plus dealing with an expression an a
     * string.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - an expression
     */
    public Log(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double d1 = this.getRightArgument().evaluate(assignment);
            if (d1 <= 0) {
                System.out.println("Error! None positive arguments for Log");
            }
        } catch (Exception e) {
            throw e;
        }
        try {
            double d2 = this.getLeftArgument().evaluate(assignment);
            if (d2 <= 0) {
                System.out.println("Error! None positive arguments for Log");
            }
        } catch (Exception e) {
            throw e;
        }
        try {
            // trying to calculate the Log expression
            return Math.log(getRightArgument().evaluate(assignment)) / Math.log(getLeftArgument().evaluate(assignment));
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Log(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    public String toString() {
        return String.format("Log(%s, %s)", this.getLeftArgument().toString(), this.getRightArgument().toString());
    }

    @Override
    String getExpressionString() {
        return null;
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Div(this.getRightArgument().differentiate(var),
                        new Mult(this.getRightArgument(), new Log(Math.E, this.getLeftArgument())));
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        // if the expression has no variables
        if (this.getVariables().isEmpty()) {
            // call calculateForNoVar
            return calculateForNoVar();
        }
        simplifyGeneral();
        // if the argument are equal
        if (getLeftArgument().toString().equals(getRightArgument().toString())) {
            return new Num(1);
            // if the right argument is 1
        } else if (getRightArgument().toString().equals("1")) {
            return new Num(0);
        } else if ((this.getLeftArgument()).isComitative()) {
            if ((this.getLeftArgument()).getFixedString().equals(this.getRightArgument().getFixedString())) {
                return new Num(1);
            }
            } else if (this.getVariables().isEmpty()) {
            try {
                return new Num(Math.log(getRightArgument().evaluate() / Math.log(getLeftArgument().evaluate())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public boolean isComitative() {
        return false;
    }

    @Override
    public String getFixedString() {
        return this.toString();
    }
}
