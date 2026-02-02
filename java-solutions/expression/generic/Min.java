package expression.generic;

public class Min<T> extends AbstractOperation<T> {
    public Min(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.min(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "min";
    }
}