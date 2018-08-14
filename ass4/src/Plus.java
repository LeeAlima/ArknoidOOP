import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Plus extends BinaryExpression implements Expression {

    /**
     * this is the first constructor for a Plus dealing with 2 expression.
     * @param leftArgument
     *            - an Expression
     * @param rightArgument
     *            - an Expression
     */
    public Plus(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the second constructor for a Plus dealing with an expression and
     * a double.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Plus(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the third constructor for a Plus dealing with an expression and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - an expression
     */
    public Plus(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fourth constructor for a Plus dealing with a string and a
     * double.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a double
     */
    public Plus(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the fifth constructor for a Plus dealing with double and a
     * string.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a string
     */
    public Plus(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the sixt constructor for a Plus dealing with 2 doubles.
     * @param leftArgument
     *            - a double
     * @param rightArgument
     *            - a double
     */
    public Plus(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the seventh constructor for a Plus dealing with 2 strings.
     * @param leftArgument
     *            - a string
     * @param rightArgument
     *            - a string
     */
    public Plus(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);

    }

    /**
     * this is the eighth constructor for a Plus dealing with an expression and
     * a double.
     * @param leftArgument
     *            - an expression
     * @param rightArgument
     *            - a double
     */
    public Plus(Expression leftArgument, double rightArgument) {
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
    public Plus(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);

    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the plus expression
            return this.getLeftArgument().evaluate(assignment) + this.getRightArgument().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Plus(getLeftArgument().assign(var, expression), getRightArgument().assign(var, expression));
    }

    @Override
    protected String getExpressionString() {
        return " + ";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Plus(this.getLeftArgument().differentiate(var), this.getRightArgument().differentiate(var));
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftArgument();
        Expression e2 = this.getRightArgument(); // if the expression has no variables
        if (this.getVariables().isEmpty()) { // call calculateForNoVar
            return calculateForNoVar();
        }
        simplifyGeneral(); // if the left argument is 0
        if (getLeftArgument().toString().equals(new Num(0).toString())) {
            return getRightArgument(); // if the right argument is 0
        } else if (getRightArgument().toString().equals(new Num(0).toString())) {
            return getLeftArgument(); // if the list if empty
        } else if (getLeftArgument().toString().equals(getRightArgument().toString())
                || (this.getLeftArgument()).getFixedString().equals(getRightArgument().getFixedString())) {
            return new Mult(2, getRightArgument());
        } else if (e1 instanceof Num && e2 instanceof Plus) {
            // 2 + (x +5)
            if (((Plus) e2).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Plus(
                            new Num(e1.simplify().evaluate() + ((Plus) e2).getLeftArgument().simplify().evaluate()),
                            ((Plus) e2).getRightArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                } // 2 + (5 + x)
            } else if (((Plus) e2).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Plus(
                            new Num(e1.simplify().evaluate() + ((Plus) e2).getRightArgument().simplify().evaluate()),
                            ((Plus) e2).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                } // 2 + (5 + 2)
            } else if (((Plus) e2).getLeftArgument().simplify() instanceof Num
                    && ((Plus) e2).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Num(e1.simplify().evaluate() + ((Plus) e2).getRightArgument().simplify().evaluate()
                            + ((Plus) e2).getLeftArgument().simplify().evaluate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (e2 instanceof Num && e1 instanceof Plus) {
            // (2 + x) +5
            if (((Plus) e1).getLeftArgument().simplify() instanceof Num) {
                try {
                    return new Plus(
                            new Num(((Plus) e1).getLeftArgument().simplify().evaluate() + e2.simplify().evaluate()),
                            ((Plus) e1).getRightArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                } // (x + 2) +5
            } else if (((Plus) e1).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Plus(((Plus) e1).getRightArgument().simplify().evaluate() + e2.simplify().evaluate(),
                            ((Plus) e1).getLeftArgument().simplify());
                } catch (Exception e) {
                    e.printStackTrace();
                } // (2 + 5) + 2
            } else if (((Plus) e1).getLeftArgument().simplify() instanceof Num
                    && ((Plus) e1).getRightArgument().simplify() instanceof Num) {
                try {
                    return new Num(((Plus) e1).getLeftArgument().simplify().evaluate()
                            + ((Plus) e1).getRightArgument().simplify().evaluate() + e2.simplify().evaluate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (e1 instanceof Mult && e2 instanceof Mult) {
            if (((Mult) e1).getLeftArgument().simplify() instanceof Num) {
                // 5x + 2x
                if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                    if (((Mult) e1).getRightArgument().simplify().toString()
                            .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                        try {
                            return new Mult(
                                    new Num(((Mult) e1).getLeftArgument().simplify().evaluate()
                                            + ((Mult) e2).getLeftArgument().simplify().evaluate()),
                                    ((Mult) e1).getRightArgument().simplify());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } // 5x + x2
                } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                    if (((Mult) e1).getRightArgument().simplify().toString()
                            .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                        try {
                            return new Mult(
                                    new Num(((Mult) e1).getLeftArgument().simplify().evaluate()
                                            + ((Mult) e2).getRightArgument().simplify().evaluate()),
                                    ((Mult) e1).getRightArgument().simplify());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (((Mult) e1).getRightArgument().simplify() instanceof Num) {
                // x2 + x5
                if (((Mult) e2).getLeftArgument().simplify() instanceof Num) {
                    if (((Mult) e1).getLeftArgument().simplify().toString()
                            .equals(((Mult) e2).getRightArgument().simplify().toString())) {
                        try {
                            return new Mult(
                                    new Num(((Mult) e1).getRightArgument().simplify().evaluate()
                                            + ((Mult) e2).getLeftArgument().simplify().evaluate()),
                                    ((Mult) e1).getLeftArgument().simplify());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } // x2 + x2
                } else if (((Mult) e2).getRightArgument().simplify() instanceof Num) {
                    if (((Mult) e1).getLeftArgument().simplify().toString()
                            .equals(((Mult) e2).getLeftArgument().simplify().toString())) {
                        try {
                            return new Mult(
                                    new Num(((Mult) e1).getRightArgument().simplify().evaluate()
                                            + ((Mult) e2).getRightArgument().simplify().evaluate()),
                                    ((Mult) e1).getLeftArgument().simplify());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } // nested expression
        } else if (e1 instanceof Mult && e2 instanceof Plus) {
            Expression second = ((Plus) e2.simplify()).getRightArgument().simplify();
            if (second instanceof Mult) {
                Expression first = ((Mult) e1).getRightArgument().simplify();
                if (first instanceof Var && ((Mult) second).getRightArgument() instanceof Var) {
                    if (first.getFixedString().equals(((Mult) second).getRightArgument().getFixedString())) {
                        return new Plus(
                                new Mult(new Plus(((Mult) e1).getLeftArgument().simplify(),
                                        ((Mult) second).getLeftArgument()).simplify(), first),
                                ((Plus) e2.simplify()).getLeftArgument().simplify());

                    }
                }
            }
        } else if (e1 instanceof Plus && e2 instanceof Plus) {
            return simplfyContinuation();
        } else if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.getRightArgument().evaluate() + this.getLeftArgument().evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     *  the continuation for simplfy.
     * @return expession
     */
    public Expression simplfyContinuation() {
        Expression e1 = this.getLeftArgument();
        Expression e2 = this.getRightArgument();
        if (e1 instanceof Num || e2 instanceof Num) {
            return this;
        }
        if (((Plus) e1).getLeftArgument().simplify() instanceof Num) {
            // (2+x) + (2+x)
            if (((Plus) e2).getLeftArgument().simplify() instanceof Num) {
                if (((Plus) e1).getRightArgument().simplify().toString()
                        .equals(((Plus) e2).getRightArgument().simplify().toString())) {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getLeftArgument().simplify().evaluate()
                                        + ((Plus) e2).getLeftArgument().simplify().evaluate()),
                                new Mult(2, ((Plus) e2).getRightArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } // (2+x) + (2+y)
                } else {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getLeftArgument().simplify().evaluate()
                                        + ((Plus) e2).getLeftArgument().simplify().evaluate()),
                                new Plus(((Plus) e1).getRightArgument().simplify(),
                                        ((Plus) e2).getRightArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } // (2+x) + (x+2)
            } else if (((Plus) e2).getRightArgument().simplify() instanceof Num) {
                if (((Plus) e1).getRightArgument().simplify().toString()
                        .equals(((Plus) e2).getLeftArgument().simplify().toString())) {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getLeftArgument().simplify().evaluate()
                                        + ((Plus) e2).getRightArgument().simplify().evaluate()),
                                new Mult(2, ((Plus) e1).getRightArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } // (2+x) + (y+2)
                } else {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getLeftArgument().simplify().evaluate()
                                        + ((Plus) e2).getRightArgument().simplify().evaluate()),
                                new Plus(((Plus) e1).getRightArgument().simplify(),
                                        ((Plus) e2).getLeftArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        } else if (((Plus) e1).getRightArgument().simplify() instanceof Num) {
            // (x+2) + (2+x)
            if (((Plus) e2).getLeftArgument().simplify() instanceof Num) {
                if (((Plus) e1).getLeftArgument().simplify().toString()
                        .equals(((Plus) e2).getRightArgument().simplify().toString())) {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getRightArgument().simplify().evaluate()
                                        + ((Plus) e2).getLeftArgument().simplify().evaluate()),
                                new Mult(2, ((Plus) e2).getRightArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } // (x+2) + (2+x)
                } else {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getRightArgument().simplify().evaluate()
                                        + ((Plus) e2).getLeftArgument().simplify().evaluate()),
                                new Plus(((Plus) e1).getLeftArgument().simplify(),
                                        ((Plus) e2).getRightArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (((Plus) e2).getRightArgument().simplify() instanceof Num) {
                // (x+2) + (x+2)
                if (((Plus) e1).getLeftArgument().simplify().toString()
                        .equals(((Plus) e2).getLeftArgument().simplify().toString())) {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getRightArgument().simplify().evaluate()
                                        + ((Plus) e2).getRightArgument().simplify().evaluate()),
                                new Mult(2, ((Plus) e2).getLeftArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } // (x+2) + (y+2)
                } else {
                    try {
                        return new Plus(
                                new Num(((Plus) e1).getRightArgument().simplify().evaluate()
                                        + ((Plus) e2).getRightArgument().simplify().evaluate()),
                                new Plus(((Plus) e1).getLeftArgument().simplify(),
                                        ((Plus) e2).getLeftArgument().simplify()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        // check if the number of vars in each side is 1
        if (this.getLeftArgument().getVariables().size() == 1 && this.getRightArgument().getVariables().size() == 1) {
            if (this.getLeftArgument().getVariables().get(0).equals(this.getRightArgument().getVariables().get(0))) {
                if (this.getLeftArgument().getFixedString().compareTo(this.getRightArgument().getFixedString()) <= 0) {
                    return "(" + this.getLeftArgument().getFixedString() + " + "
                            + this.getRightArgument().getFixedString() + ")";
                } else {
                    return "(" + (this.getRightArgument()).getFixedString() + " + "
                            + (this.getLeftArgument()).getFixedString() + ")";
                }
            }
            if (this.getLeftArgument().getVariables().get(0)
                    .compareTo(this.getRightArgument().getVariables().get(0)) < 0) {
                return "(" + this.getLeftArgument().getFixedString() + " + " + this.getRightArgument().getFixedString()
                        + ")";
            } else {
                return "(" + (this.getRightArgument()).getFixedString() + " + "
                        + (this.getLeftArgument()).getFixedString() + ")";
            }
        }
        // if there are 2 variables
        if (this.getLeftArgument().getVariables().size() > this.getRightArgument().getVariables().size()) {
            return "(" + this.getLeftArgument().getFixedString() + " + " + this.getRightArgument().getFixedString()
                    + ")";
        } else if (this.getLeftArgument().getVariables().size() < this.getRightArgument().getVariables().size()) {
            return "(" + this.getRightArgument().getFixedString() + " + " + this.getLeftArgument().getFixedString()
                    + ")";
        } else {
            if (this.getLeftArgument().getVariables().get(0)
                    .compareTo(this.getRightArgument().getVariables().get(0)) < 0) {
                return "(" + (this.getLeftArgument()).getFixedString() + " + "
                        + (this.getRightArgument()).getFixedString() + ")";
            } else {
                return "(" + (this.getRightArgument()).getFixedString() + " + "
                        + (this.getLeftArgument()).getFixedString() + ")";
            }
        }
    }
}