package expression.generic;

public class Variable<T> implements InterfaceExpression<T> {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if ("x".equals(variable)) {
            return x;
        } else if ("y".equals(variable)) {
            return y;
        } else if ("z".equals(variable)) {
            return z;
        } else {
            throw new IllegalArgumentException("Unknown variable: " + variable);
        }
    }

    @Override
    public String toString() {
        return variable;
    }
}

