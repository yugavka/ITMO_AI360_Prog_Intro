package expression.generic;

public class Count<T> extends AbstractUnaryOperation<T> {
    public Count(InterfaceExpression<T> exp, Operation<T> operation) {
        super(exp, operation);
    }

    @Override
    protected T performOperation(T value) {
        return operation.count(value);
    }

    @Override
    protected String OperationDelimiter() {
        return "count";
    }
}