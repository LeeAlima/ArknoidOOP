import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Minus extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Minus dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Minus(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Minus dealing with an expression and
     * a double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Minus(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Minus dealing with an expression and
     * a string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Minus(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Minus dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Minus(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Minus dealing with double and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Minus(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Minus dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Minus(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Minus dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Minus(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Minus dealing with an expression and
     * a double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Minus(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the ninth constructor for a Minus dealing with an expression an a
     * string.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - an expression
     */
    public Minus(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the plus expression
            return this.getLeftArgument().evaluate(assignment) - this.getRightArgument().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Minus(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    String getExpressionString() {
        return " - ";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Minus(this.getLeftArgument().differentiate(var), this.getRightArgument().differentiate(var));
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftArgument().simplify();
        Expression e2 = this.getRightArgument().simplify();
        // if the expression has no variables
        if (this.getVariables().isEmpty()) {
            // call calculateForNoVar
            return calculateForNoVar();
        }
        simplifyGeneral();
        // if the left argument is 0
        if (getLeftArgument().toString().equals(new Num(0).toString())) {
            return new Neg(getRightArgument()); // if the right argument is 0
        } else if (getRightArgument().toString().equals(new Num(0).toString())) {
            return this.getLeftArgument(); // if the arguments are the same
        } else if (getRightArgument().toString().equals(getLeftArgument().toString())) {
            return new Num(0); // check if the argument is comitative
        } else if ((this.getLeftArgument()).isComitative()) {
            if ((this.getLeftArgument()).getFixedString().equals(
                    this.getRightArgument().getFixedString().toString())) {
                return new Num(0);
            }
        }
        if (e1 instanceof Num && e2 instanceof Plus) {
            // 2 - (x + 3)
            if (((Plus) e2).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Minus(
                            new Num(e1.simplify().evaluate() - ((Plus) e2).getRightArgument().simplify().evaluate()),
                            ((Plus) e2).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                } // 2 - (3 + x)
            } else if (((Plus) e2).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Plus(
                            new Num(e1.simplify().evaluate() - ((Plus) e2).getLeftArgument().simplify().evaluate()),
                            ((Plus) e2).getRightArgument().simplify()).simplify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (e2 instanceof Num && e1 instanceof Plus) {
            // (2 + x) - 5
            if (((Plus) e1).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Plus(
                            new Num(((Plus) e1).getLeftArgument().simplify().evaluate() - e2.simplify().evaluate()),
                            ((Plus) e1).getRightArgument().simplify()).simplify();
                } catch (Exception e) {
                    e.printStackTrace();
                } // (x + 2) - 5
            } else if (((Plus) e1).getRightArgument().simplify() instanceof Num) {

                try {
                    return new Plus(((Plus) e1).getRightArgument().simplify().evaluate() - e2.simplify().evaluate(),
                            ((Plus) e1).getLeftArgument().simplify()).simplify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // (1 + x) - x
        }  else if (e1 instanceof Plus && e2 instanceof Var) {
            if (((Plus) e1).getLeftArgument().simplify() instanceof Num) {
                if ((((Plus) e1).getRightArgument().toString().equals(e2.simplify().toString()))) {
                    try {
                        return new Num(((Plus) e1).getLeftArgument().simplify().evaluate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } // (x + 1) - x
            }  else if (((Plus) e1).getRightArgument().simplify() instanceof Num) {
                if ((((Plus) e1).getLeftArgument().toString().equals(e2.simplify().toString()))) {
                    try {
                        return new Num(((Plus) e1).getRightArgument().simplify().evaluate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (e1 instanceof Mult && e2 instanceof Mult) {
            return simplfyContinuation();
            // checks if the expression has no variables
        } else if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.getRightArgument().evaluate() - this.getLeftArgument().evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    /**
     *  this method is the continuation for simplify.
     * @return Expression
     */
    public Expression simplfyContinuation() {
        Expression e1 = this.getLeftArgument().simplify();
        Expression e2 = this.getRightArgument().simplify();
        if (e1 instanceof Num || e2 instanceof Num) {
            return this;
        }
        if (((Mult) e1).getLeftArgument().simplify() instanceof Num) {
            // 2x - 2x
            if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                if (((Mult) e1).getRightArgument().simplify().toString()
                        .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                    try {
                        return new Mult(
                                new Num(((Mult) e1).getLeftArgument().simplify().evaluate()
                                        - ((Mult) e2).getLeftArgument().simplify().evaluate()),
                                ((Mult) e1).getRightArgument().simplify());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } // 2*x - x*2
            } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                if (((Mult) e1).getRightArgument().simplify().toString()
                        .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                    try {
                        return new Mult(
                                new Num(((Mult) e1).getLeftArgument().simplify().evaluate()
                                        - ((Mult) e2).getRightArgument().simplify().evaluate()),
                                ((Mult) e1).getRightArgument().simplify());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } // x*2 - 2*x
        } else if (((Mult) e1).getRightArgument().simplify() instanceof Num) {
            if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                if (((Mult) e1).getLeftArgument().simplify().toString()
                        .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                    try {
                        return new Mult(
                                new Num(((Mult) e1).getRightArgument().simplify().evaluate()
                                        - ((Mult) e2).getLeftArgument().simplify().evaluate()),
                                ((Mult) e1).getLeftArgument().simplify());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } // x*2 - x*2
            } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                if (((Mult) e1).getLeftArgument().simplify().toString()
                        .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                    try {
                        return new Mult(
                                new Num(((Mult) e1).getRightArgument().simplify().evaluate()
                                        - ((Mult) e2).getRightArgument().simplify().evaluate()),
                                ((Mult) e1).getLeftArgument().simplify());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
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