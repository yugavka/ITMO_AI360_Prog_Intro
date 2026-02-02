package expression;

public class Add extends AbstractOperation {
    public Add(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return firstPart + secondPart;
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return firstPart + secondPart;
    }

    @Override
    protected String OperationDelimiter() {
        return "+";
    }
}

