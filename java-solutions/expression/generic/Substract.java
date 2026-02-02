package expression.generic;

public class Substract<T> extends AbstractOperation<T> {
    public Substract(InterfaceExpression<T> left, InterfaceExpression<T> right, Operation<T> operation) {
        super(left, right, operation);
    }

    @Override
    protected T performOperation(T firstPart, T secondPart) {
        return operation.substract(firstPart, secondPart);
    }

    @Override
    protected String OperationDelimiter() {
        return "-";
    }
}