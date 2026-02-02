package expression.generic;

public abstract class AbstractUnaryOperation<T> implements InterfaceExpression<T> {
    protected final InterfaceExpression<T> exp;
    protected final Operation<T> operation;
    protected abstract T performOperation(T value);
    protected abstract String OperationDelimiter();

    protected AbstractUnaryOperation(InterfaceExpression<T> exp, Operation<T> operation) {
        this.exp = exp;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return performOperation(exp.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return OperationDelimiter() + "(" + exp.toString() + ")";
    }
}

