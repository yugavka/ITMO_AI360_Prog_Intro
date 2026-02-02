package expression.generic;

public class Max<T> extends AbstractOperation<T> {
    public Max(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.max(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "max";
    }
}