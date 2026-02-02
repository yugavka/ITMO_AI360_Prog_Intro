package expression.generic;

public class Negate<T> extends AbstractUnaryOperation<T> {
    public Negate(InterfaceExpression<T> exp, Operation<T> operation) {
        super(exp, operation);
    }

    @Override
    protected T performOperation(T value) {
        return operation.negate(value);
    }

    @Override
    protected String OperationDelimiter() {
        return "-";
    }
}

