package expression.generic;

public class Multiply<T> extends AbstractOperation<T> {
    public Multiply(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.multiply(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "*";
    }
}