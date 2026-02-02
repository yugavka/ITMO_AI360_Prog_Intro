package expression;

public class UnaryMinus extends AbstractUnaryOperation {
    public UnaryMinus(InterfaceExpression exp) {
        super(exp);
    }

    @Override
    public int performOperation(int value) {
        return -value;
    }

    @Override
    public double performOperation(double value) {
        return -value;
    }

    @Override
    protected String OperationDelimiter() {
        return "-";
    }
}

