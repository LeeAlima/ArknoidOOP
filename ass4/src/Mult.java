import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Mult dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Mult(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Mult dealing with an expression and
     * a double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Mult(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Mult dealing with an expression and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Mult(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Mult dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Mult(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Mult dealing with double and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Mult(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Mult dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Mult(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Mult dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Mult(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Mult dealing with an expression and
     * a double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Mult(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the ninth constructor for a Mult dealing with an expression an a
     * string.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - an expression
     */
    public Mult(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the plus expression
            return this.getLeftArgument().evaluate(assignment) * this.getRightArgument().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Mult(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    protected String getExpressionString() {
        return " * ";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Plus(new Mult(this.getLeftArgument().differentiate(var), this.getRightArgument()),
                        new Mult(this.getRightArgument().differentiate(var), this.getLeftArgument()));
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
        // if the left argument is 0
        if (this.getLeftArgument().toString().equals(new Num(0).toString())) {
            return new Num(0); // if the right argument is 0
        } else if (this.getRightArgument().toString().equals(new Num(0).toString())) {
            return new Num(0); // if the left argument is 1
        } else if (this.getLeftArgument().toString().equals(new Num(1).toString())) {
            return this.getRightArgument(); // if the right argument is 1
        } else if (this.getRightArgument().toString().equals(new Num(1).toString())) {
            return this.getLeftArgument();
        } else if (e2.toString().equals(e1.toString())) {
            return new Pow(e2, 2);
         // num * (num*var) || num * (var*num)
        } else if (e1.simplify() instanceof Num && e2.simplify() instanceof Mult) {
            if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Mult(
                            new Num(((Mult) e2).getLeftArgument().simplify().evaluate() * e1.simplify().evaluate()),
                            ((Mult) e2).getRightArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Mult(
                            new Num(e1.simplify().evaluate() * ((Mult) e2).getRightArgument().simplify().evaluate()),
                            ((Mult) e2).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // (num*var)*num || (var*num)*num
        } else if (e2.simplify() instanceof Num && e1.simplify() instanceof Mult) {
            if (((Mult) e1).getLeftArgument().simplify() instanceof Num) {
                try {

                    return new Mult(
                            new Num(((Mult) e1).getLeftArgument().simplify().evaluate() * e2.simplify().evaluate()),
                            ((Mult) e1).getRightArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (((Mult) e1).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Mult(
                            new Num(e2.simplify().evaluate() * ((Mult) e1).getRightArgument().simplify().evaluate()),
                            ((Mult) e1).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // x*x
        } else if (e1.simplify() instanceof Var && e2.simplify() instanceof Var) {
            if (e1.toString().equals(e2.toString())) {
                return new Pow(e1, 2);
            }
        } else if (e1.simplify() instanceof Mult && e2.simplify() instanceof Mult) {
            // (x*3) * (x*3)
            if (((Mult) e1).getLeftArgument().simplify() instanceof Var
                    && ((Mult) e2).getLeftArgument().simplify() instanceof Var
                    && !(((Mult) e1).getRightArgument().simplify() instanceof Var)
                    && !(((Mult) e2).getRightArgument().simplify() instanceof Var)) {
                result = new Mult(((Mult) e1).getRightArgument().simplify(), ((Mult) e2).getRightArgument().simplify());
                if (((Mult) e1).getLeftArgument().simplify().toString()
                        .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                    return new Mult(result.simplify(), new Pow(((Mult) e1).getLeftArgument().simplify(), 2));
                } else { // (x*3) *(y*3)
                    return new Mult(new Mult(result.simplify(), ((Mult) e1).getLeftArgument().simplify()),
                            ((Mult) e2).getLeftArgument().simplify());
                } // (2*x) * (2*x)
            } else if (((Mult) e1).getRightArgument().simplify() instanceof Var
                    && ((Mult) e2).getRightArgument().simplify() instanceof Var
                    && !(((Mult) e1).getLeftArgument().simplify() instanceof Var)
                    && !(((Mult) e2).getLeftArgument().simplify() instanceof Var)) {
                result = new Mult(((Mult) e1).getLeftArgument().simplify(), ((Mult) e2).getLeftArgument().simplify());
                if (((Mult) e1).getRightArgument().simplify().toString()
                        .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                    return new Mult(result.simplify(), new Pow(((Mult) e1).getRightArgument().simplify(), 2));
                } else { // (2*x) * (2*y)
                    return new Mult(new Mult(result.simplify(), ((Mult) e1).getRightArgument().simplify()),
                            ((Mult) e2).getRightArgument().simplify());
                }
            // (3*x) * (x*3)
            } else if (((Mult) e1).getRightArgument().simplify() instanceof Var
                    && ((Mult) e2).getLeftArgument().simplify() instanceof Var
                    && !(((Mult) e1).getLeftArgument().simplify() instanceof Var)
                    && !(((Mult) e2).getRightArgument().simplify() instanceof Var)) {
                result = new Mult(((Mult) e1).getLeftArgument().simplify(), ((Mult) e2).getRightArgument().simplify());
                if (((Mult) e1).getRightArgument().simplify().toString()
                        .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                    return new Mult(result.simplify(), new Pow(((Mult) e1).getRightArgument().simplify(), 2));
                } else { // (3*x) * (2*y)
                    return new Mult(new Mult(result.simplify(), ((Mult) e1).getRightArgument().simplify()),
                            ((Mult) e2).getLeftArgument().simplify());
                } // (x*3) * (2*x)
            } else if (((Mult) e1).getLeftArgument().simplify() instanceof Var
                    && ((Mult) e2).getRightArgument().simplify() instanceof Var
                    && !(((Mult) e1).getRightArgument().simplify() instanceof Var)
                    && !(((Mult) e2).getLeftArgument().simplify() instanceof Var)) {
                result = new Mult(((Mult) e1).getRightArgument().simplify(), ((Mult) e2).getLeftArgument().simplify());
                if (((Mult) e1).getLeftArgument().simplify().toString()
                        .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                    result = new Mult(((Mult) e1).getRightArgument().simplify(),
                            ((Mult) e2).getLeftArgument().simplify());
                    return new Mult(result.simplify(), new Pow(((Mult) e1).getLeftArgument().simplify(), 2));
                } else { // (x*3) * (2*y)
                    return new Mult(new Mult(result.simplify(), ((Mult) e1).getLeftArgument().simplify()),
                            ((Mult) e2).getRightArgument().simplify());
                }

            } else if (this.getVariables().isEmpty()) {
                try {
                    return new Num(this.getRightArgument().evaluate() * this.getLeftArgument().evaluate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    @Override
    public boolean isComitative() {
        return true;
    }

    @Override
    public String getFixedString() {
        if (this.getLeftArgument().getVariables().size() == 1 && this.getRightArgument().getVariables().size() == 1) {
            if (this.getLeftArgument().getVariables().get(0).equals(this.getRightArgument().getVariables().get(0))) {
                if (this.getLeftArgument().getFixedString().compareTo(this.getRightArgument().getFixedString()) <= 0) {
                    return "(" + this.getLeftArgument().getFixedString() + " * "
                            + this.getRightArgument().getFixedString() + ")";
                } else {
                    return "(" + (this.getRightArgument()).getFixedString() + " * "
                            + (this.getLeftArgument()).getFixedString() + ")";
                }
            }
            if (this.getLeftArgument().getVariables().get(0)
                    .compareTo(this.getRightArgument().getVariables().get(0)) < 0) {
                return "(" + this.getLeftArgument().getFixedString() + " * " + this.getRightArgument().getFixedString()
                        + ")";
            } else {
                return "(" + (this.getRightArgument()).getFixedString() + " * "
                        + (this.getLeftArgument()).getFixedString() + ")";
            }
        }
        // if there are 2 variables
        if (this.getLeftArgument().getVariables().size() > this.getRightArgument().getVariables().size()) {
            return "(" + this.getLeftArgument().getFixedString() + " * " + this.getRightArgument().getFixedString()
                    + ")";
        } else if (this.getLeftArgument().getVariables().size() < this.getRightArgument().getVariables().size()) {
            return "(" + this.getRightArgument().getFixedString() + " * " + this.getLeftArgument().getFixedString()
                    + ")";
        } else {
            if (this.getLeftArgument().getVariables().get(0)
                    .compareTo(this.getRightArgument().getVariables().get(0)) < 0) {
                return "(" + (this.getLeftArgument()).getFixedString() + " * "
                        + (this.getRightArgument()).getFixedString() + ")";
            } else {
                return "(" + (this.getRightArgument()).getFixedString() + " * "
                        + (this.getLeftArgument()).getFixedString() + ")";
            }
        }
    }
}