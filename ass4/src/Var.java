import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Var implements Expression {
    private String variable;

    /**
     * constructor for var.
     * @param variable
     *            - as a string
     */
    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * @return variable as a string
     */
    public String getVariable() {
        return variable;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // checking if there are no variables in the assignment
        if (assignment.isEmpty()) {
            if (variable == "e") {
                return Math.E;
            }
            if (variable == "p") {
                return Math.PI;
            }
            throw new Exception("No value");
        }
        // if the expression contains the variable
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }

    @Override
    public double evaluate() throws Exception {
        // throw an exception
        throw new Exception("variable wasn't assigned");
    }

    @Override
    public List<String> getVariables() {
        java.util.List<String> singleVariable = new ArrayList<String>();
        singleVariable.add(this.variable);
        return singleVariable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // if the var equals to the var
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression differentiate(String var) {
        // checking if the var apperas in the expression
        for (String string : this.getVariables()) {
            if (string == var) {
                // differentiate
                return new Num(1);
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
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