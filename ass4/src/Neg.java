import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * this is the first constructor for neg dealing with an expression.
     * @param exp -  as the unary expression
     */
    public Neg(Expression exp) {
        super(exp);
    }

    /**
     * this is the second constructor for neg dealing with a string.
     * @param var - as the unary expression
     */
    public Neg(String var) {
        super(var);
    }

    /**
     * this is the third constructor for neg dealing with a string.
     * @param num - as the unary expression
     */
    public Neg(double num) {
        super(num);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            // trying to calculate the neg expression
            return -1 * this.getArgument().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("Exception in evaluation process");
            throw e;
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        return new Neg(this.getArgument().assign(var, expression));
    }

    @Override
    protected String getExpressionString() {

        return "-";
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String var1 : this.getVariables()) {
            if (var == var1) {
                // differentiate
                return new Neg(this.getArgument().differentiate(var));
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
        Neg s = new Neg(this.getArgument().simplify());
        if (s.getVariables().isEmpty()) {
            // trying to calculate
            try {
                return new Num(s.evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Neg(this.getArgument().simplify());
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