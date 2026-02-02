package expression;

public class Multiply extends AbstractOperation {
    public Multiply(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return firstPart * secondPart;
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return firstPart * secondPart;
    }

    @Override
    protected String OperationDelimiter() {
        return "*";
    }
}

