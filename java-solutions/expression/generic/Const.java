package expression.generic;

public class Const<T> implements InterfaceExpression<T> {
    private final T valueOfConst;

    public Const(T valueOfConst) {
        this.valueOfConst = valueOfConst;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return valueOfConst;
    }

    @Override
    public String toString() {
        return String.valueOf(valueOfConst);
    }
}

