package expression.generic;

public abstract class AbstractOperation<T> implements InterfaceExpression<T> {
    protected final InterfaceExpression<T> left;
    protected final InterfaceExpression<T> right;
    protected final Operation<T> operation;
    protected abstract T performOperation(T firstPart, T secondPart);
    protected abstract String OperationDelimiter();

    protected AbstractOperation(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return performOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + OperationDelimiter() + " " + right.toString() + ")";
    }
}

