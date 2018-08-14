import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Cos extends UnaryExpression implements Expression {

    /**
     * this is the first constructor for cos dealing with an expression.
     * @param exp - as an expression
     */
    public Cos(Expression exp) {
        super(exp);
    }

    /**
     * this is the second constructor for cos dealing with a string.
     * @param var - as a string
     */
    public Cos(String var) {
        super(var);
    }

    /**
     * this is the third constructor for cos dealing with a string.
     * @param num - as a double
     */
    public Cos(double num) {
        super(num);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the cosinos expression
            double d = Math.cos(Math.toRadians(this.getArgument().evaluate(assignment)));
            if (d >= 0 && d <= 0.0000001) {
                return 0;
            }
            return d;
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Cos(this.getArgument().assign(var, expression));
    }

    @Override
    protected String getExpressionString() {
        return "cos";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Neg(new Mult(this.getArgument().differentiate(var), new Sin(this.getArgument())));
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
        Cos s = new Cos(this.getArgument().simplify());
        // checks if the expression has no variables again
        if (s.getVariables().isEmpty()) {
            // trying to calculate
            try {
                return new Num(s.evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Cos(this.getArgument().simplify());
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