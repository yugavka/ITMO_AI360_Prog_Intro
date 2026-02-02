package expression;

public class BitOr extends AbstractOperation {
    public BitOr(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return firstPart | secondPart;
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return 0;
    }

    @Override
    protected String OperationDelimiter() {
        return "|";
    }
}

