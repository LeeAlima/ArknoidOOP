import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Num implements Expression {

    private double value;

    /**
     * constructor for Num.
     * @param value - as the number value
     */
    public Num(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) {
        // returning the number
        return this.value;
    }

    @Override
    public double evaluate() {
        // try to return the number
        try {
            return this.value;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> getVariables() {
        // return an empty list
        java.util.List<String> emptyList = new ArrayList<String>();
        return emptyList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // returning the expression after the assigning
        if (this.toString().equals(this.value)) {
            return expression;
        }
        return this;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * this method return the value.
     * @return double
     */
    public double getValue() {
        return this.value;
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