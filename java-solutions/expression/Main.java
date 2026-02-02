package expression;

public class Main {
    public static void main(String[] args) {
        TripleExpression expression = new Add(
                new Subtract(new Variable("x"), new Const(1.1)),
                new Multiply(new Variable("y"), new Const(10.1))
        );
        System.out.println(expression);
        System.out.println(expression.evaluate(1,1,1));
    }
}

