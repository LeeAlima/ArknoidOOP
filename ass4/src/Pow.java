import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Pow extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Pow dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Pow(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Pow dealing with an expression and a
     * double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Pow(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Pow dealing with an expression and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Pow(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Pow dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Pow(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Pow dealing with double and a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Pow(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Pow dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Pow(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Pow dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Pow(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Pow dealing with an expression and a
     * double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Pow(Expression leftArgument, double rightArgument) {
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
    public Pow(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the Pow expression
            return Math.pow(getLeftArgument().evaluate(assignment), getRightArgument().evaluate(assignment));
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Pow(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    protected String getExpressionString() {
        return " ^ ";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Mult(new Pow(this.getLeftArgument(), this.getRightArgument()), new Plus(
                        new Mult(this.getLeftArgument().differentiate(var),
                                new Div(this.getRightArgument(), this.getLeftArgument())),
                        new Mult(this.getRightArgument().differentiate(var), new Log(Math.E, this.getLeftArgument()))));
            }
        }
        return new Num(0);

    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftArgument().simplify();
        Expression e2 = this.getRightArgument().simplify();
        Expression result;
        // if the expression has no variables
        if (this.getVariables().isEmpty()) {
            // call calculateForNoVar
            return calculateForNoVar();
        }
        simplifyGeneral();
        // checks if the left argument is 1
        if (getLeftArgument().toString().equals(new Num(1).toString())) {
            return new Num(1); // checks if the right argument is 0
        } else if (getRightArgument().toString().equals(new Num(0).toString())) {
            return new Num(1); // checks if the rigtht argument is 1
        } else if (getRightArgument().toString().equals(new Num(1).toString())) {
            return this.getLeftArgument();
         // (x^y)^z - Bonus!
        } else if (this.getLeftArgument().simplify() instanceof Pow) {
            if (e1 instanceof Pow) {
                result = new Mult(((Pow) e1).getRightArgument().simplify(), e2.simplify());
                return new Pow(((Pow) e1).getLeftArgument().simplify(), result);
            }
        } else if (this.getVariables().isEmpty()) {
            try {
                return new Num(Math.pow(this.getRightArgument().evaluate(), this.getLeftArgument().evaluate()));
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