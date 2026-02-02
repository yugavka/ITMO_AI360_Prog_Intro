package expression.exceptions;

import expression.InterfaceExpression;
import expression.AbstractOperation;

public class CheckedLcm extends AbstractOperation {
    public CheckedLcm(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        if (firstPart == 0 || secondPart == 0) {
            return 0;
        }
        int gcd = CheckedGcd.gcd(firstPart, secondPart);
        int divide = CheckedDivide.divide(firstPart, gcd);
        return CheckedMultiply.multiply(divide, secondPart);
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return 0;
    }

    @Override
    protected String OperationDelimiter() {
        return "lcm";
    }
}

