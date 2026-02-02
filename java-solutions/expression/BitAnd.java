package expression;

public class BitAnd extends AbstractOperation {
    public BitAnd(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return firstPart & secondPart;
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return 0;
    }

    @Override
    protected String OperationDelimiter() {
        return "&";
    }
}

