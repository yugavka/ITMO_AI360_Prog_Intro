package expression.exceptions;

import expression.InterfaceExpression;
import expression.AbstractOperation;

public class CheckedGcd extends AbstractOperation {
    public CheckedGcd(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    public static int gcd(int firstPart, int secondPart) {
        if (secondPart == 0) {
            if (firstPart == Integer.MIN_VALUE) {
                    throw new OverflowException("overflow, operation: gcd");
            }
            return firstPart > 0 ? firstPart : -firstPart;
        }
        return gcd(secondPart, firstPart % secondPart);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return gcd(firstPart, secondPart);
    }

    @Override
    public double performOperation(double firstPart, double secondPart) {
        return 0;
    }

    @Override
    protected String OperationDelimiter() {
        return "gcd";
    }
}

