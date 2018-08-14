import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression argument;

    /**
     * this is the first constructor dealing with an expression.
     * @param e
     *            - as an expression
     */
    public UnaryExpression(Expression e) {
        this.argument = e;
    }

    /**
     * this is the second constructor dealing with a number.
     * @param d
     *            - as a double
     */
    public UnaryExpression(double d) {
        this.argument = new Num(d);
    }

    /**
     * this is the third constructor dealing with an expression.
     * @param str
     *            - as a string
     */
    public UnaryExpression(String str) {
        this.argument = new Var(str);
    }

    /**
     * this method returns a set of all of the variables in the expression.
     * @return List - of strings (the variables)
     */
    public List<String> getVariables() {
        // creating a new list
        List<String> list = new ArrayList<>();
        // creating a set
        Set<String> hs = new HashSet<String>();
        // adding all of the variables to the list
        list.addAll(argument.getVariables());
        // adding it to the set
        hs.addAll(list);
        // clearing the set
        list.clear();
        // adding the variables to the list
        list.addAll(hs);
        return list;
    }

    /**
     * this method return a nice string of the expression.
     * @return string
     */
    public String toString() {
        return String.format("%s(%s)", this.getExpressionString(), this.argument.toString());
    }

    /**
     * this method returns the expression memenber.
     * @return an expression
     */
    public Expression getArgument() {
        return argument;
    }
}
