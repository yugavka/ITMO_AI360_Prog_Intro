package expression.generic;

public class Add<T> extends AbstractOperation<T> {
    public Add(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.add(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "+";
    }
}