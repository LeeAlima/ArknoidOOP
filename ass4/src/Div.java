import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Div extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Div dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Div(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Div dealing with an expression and a
     * double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Div(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Div dealing with an expression and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Div(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Div dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Div(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Div dealing with double and a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Div(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Div dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Div(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Div dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Div(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Div dealing with an expression and a
     * double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Div(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the ninth constructor for a Div dealing with an expression an a
     * string.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - an expression
     */
    public Div(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to evaluate the right argument
            double d = this.getRightArgument().evaluate(assignment);
            if (d == 0) {
                System.out.println("Error! Can't divide an expression in 0");
            }
        } catch (Exception e) {
            throw e;
        }
        try {
            // trying to calculate the di expression
            return this.getLeftArgument().evaluate(assignment) / this.getRightArgument().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Div(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    String getExpressionString() {
        return "/";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Div(
                        new Minus(new Mult(this.getLeftArgument().differentiate(var), getRightArgument()),
                                new Mult(this.getLeftArgument(), this.getRightArgument().differentiate(var))),
                        new Pow(this.getRightArgument(), new Num(2)));
            }
        }
        return new Num(0);

    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftArgument().simplify();
        Expression e2 = this.getRightArgument().simplify();
        Expression result1;
        Expression result2;
        // if the expression has no variables
        if (this.getVariables().isEmpty()) {
            // call calculateForNoVar
            return calculateForNoVar();
        }
        simplifyGeneral();
        // if the arguments are equals
        if (this.getLeftArgument().toString().equals(this.getRightArgument().toString())) {
            return new Num(1); // if the right argument is 1
        } else if (this.getRightArgument().toString().equals(new Num(1).toString())) {
            return this.getLeftArgument().simplify(); // if the left argument is
                                                      // 0
        } else if (this.getLeftArgument().toString().equals(new Num(0).toString())) {
            return new Num(0); // checking comitative
        } else if ((this.getLeftArgument()).isComitative()) {
            if ((this.getLeftArgument()).getFixedString().equals(this.getRightArgument().getFixedString())) {
                return new Num(1);
            }
        } else if (e1 instanceof Num && e2 instanceof Mult) {
            // 5 / (2*x)
            if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Div(
                            new Num(e1.simplify().evaluate() / ((Mult) e2).getLeftArgument().simplify().evaluate()),
                            ((Mult) e2).getRightArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                 // 5 / (x*2)
                }
            } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Div(new Num(e1.evaluate() / ((Mult) e2).getRightArgument().simplify().evaluate()),
                            ((Mult) e2).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // calculate to Div's expressions
        } else if (e1 instanceof Div && e2 instanceof Div) {
            result1 = new Mult(((Div) e1).getLeftArgument().simplify(), ((Div) e2).getRightArgument().simplify());
            result2 = new Mult(((Div) e1).getRightArgument().simplify(), ((Div) e2).getLeftArgument().simplify());
            return new Div(result1, result2).simplify();
            // checking if there are no list
        } else if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.getRightArgument().evaluate() / this.getLeftArgument().evaluate());
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