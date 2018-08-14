/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class SimplificationDemo {

    /**
     * this method prints the expression befor simplification and after.
     * @param e - as the expression to handle with
     */
    public static void printBeforeAndAfter(Expression e) {
        System.out.println("The Expression before simplify: " + e);
        System.out.println("The Expression after simplification: " + e.simplify());
        System.out.println("");
    }

    /**
     * this method runs some tests.
     * @param args
     *            - as the args from the main(none).
     * @throws Exception
     *             - when something is wrong
     */
    public static void main(String[] args) throws Exception {
        // showing div
        Expression div;
        div = new Div(new Plus("x", "y"), new Plus("x", "y"));
        printBeforeAndAfter(div);

        div = new Div(new Plus("x", "y"), 1);
        printBeforeAndAfter(div);

        div = new Div(new Div(5, "x"), new Div(1, "y"));
        printBeforeAndAfter(div);

        div = new Div(5, new Mult(2, new Sin("y")));
        printBeforeAndAfter(div);

        div = new Plus(new Div(5, 0), 1);
        printBeforeAndAfter(div);

        // examples for log
        Expression log;
        log = new Log(new Plus("x", "y"), new Plus("y", "x"));
        printBeforeAndAfter(log);

        log = new Log(new Plus("x", new Sin(new Mult(2, "x"))), new Plus(new Sin(new Mult(2, "x")), "x"));
        printBeforeAndAfter(log);

        log = new Log(new Plus(-10, 5), 1);
        printBeforeAndAfter(log);

        // examples for minus
        Expression minus;
        minus = new Minus(new Mult(new Sin(50), "x"), 0);
        printBeforeAndAfter(minus);

        minus = new Minus(new Neg(new Mult(new Sin(50), "x")), new Neg(new Mult(new Sin(50), "x")));
        printBeforeAndAfter(minus);

        minus = new Minus(2, new Plus("x", 5));
        printBeforeAndAfter(minus);

        minus = new Minus(new Plus("x", 5), "x");
        printBeforeAndAfter(minus);

        minus = new Minus(new Mult(5, "x"), new Mult(2, "x"));
        printBeforeAndAfter(minus);

        // example for pow
        Expression pow;
        pow = new Pow(new Pow(new Sin("x"), "y"), new Mult(2, "z"));
        printBeforeAndAfter(pow);

        // examples for mult
        Expression mult;
        mult = new Mult(5, new Mult(new Plus(5, 3), "x"));
        printBeforeAndAfter(mult);

        mult = new Mult(new Sin(new Plus(new Plus(2, 2), "x")), new Sin(new Plus(new Plus(2, 2), "x")));
        printBeforeAndAfter(mult);

        mult = new Mult(new Mult(3, "x"), new Mult("x", new Plus(1, 3)));
        printBeforeAndAfter(mult);

        mult = new Mult(new Mult(3, "x"), new Mult("y", new Plus(1, 3)));
        printBeforeAndAfter(mult);

        // examples for plus
        Expression plus;
        plus = new Plus(2, new Plus(7, "x"));
        printBeforeAndAfter(plus);

        plus = new Plus(2, new Plus(7, "x"));
        printBeforeAndAfter(plus);

        plus = new Plus(new Plus(7, "x"), 2);
        printBeforeAndAfter(plus);

        plus = new Plus(new Mult("x", 3), new Mult(7, "x"));
        printBeforeAndAfter(plus);

        plus = new Plus(new Plus(2, "x"), new Plus(7, "x"));
        printBeforeAndAfter(plus);

        plus = new Plus(new Plus(new Plus(1, 0), new Mult("y", 1)), new Plus(7, "x"));
        printBeforeAndAfter(plus);

        // general examples
        Expression general;
        general = new Pow(new Plus(new Div(1, 1), 5), new Pow(new Plus("z", 5), new Div(new Mult(0, "t"), 5)));
        printBeforeAndAfter(general);

        general = new Plus(1, new Neg(new Sin(new Neg(new Cos(new Mult(new Minus("x", "x"), new Mult("x", 0)))))));
        printBeforeAndAfter(general);

        general = new Pow(new Neg("x"), new Pow(new Plus("z", 5), new Div(new Mult(0, "t"), 5)));
        printBeforeAndAfter(general);

        general = new Plus(new Div(new Pow(new Mult(2, 3), 8), 2), new Mult(5, new Pow("x", new Log(3, 3))));
        printBeforeAndAfter(general);

        general = new Mult("x", new Plus(new Cos(new Minus(new Plus(new Plus("y", "x"), new Plus("b", "a")),
                new Plus(new Plus("a", "b"), new Plus("x", "y")))), 3));
        printBeforeAndAfter(general);

        general = new Plus(new Plus(new Plus("y", "x"), new Plus("b", "a")),
                new Plus(new Plus("b", "a"), new Plus("x", "y")));
        printBeforeAndAfter(general);

        general = new Div(new Plus(new Plus(1, "x"), new Plus("b", "a")),
                new Plus(new Plus("a", "b"), new Plus("x", 1)));
        printBeforeAndAfter(general);

        general = new Neg(new Minus(new Minus(new Mult(new Mult(5, "x"), new Mult("t", "p")),
                new Mult(new Mult("x", 5), new Mult("t", "p"))), 6));
        printBeforeAndAfter(general);

        general = new Plus(new Mult(2, "x"), new Plus(2, new Plus(new Mult(4, "x"), 1)));
        printBeforeAndAfter(general);
    }
}