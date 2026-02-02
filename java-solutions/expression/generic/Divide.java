package expression.generic;

public class Divide<T> extends AbstractOperation<T> {
    public Divide(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.divide(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "/";
    }
}