package expression.exceptions;

import expression.InterfaceExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    public static int multiply(int firstPart, int secondPart) {
        if (firstPart > 0) {
            if (secondPart > 0 ? firstPart > Integer.MAX_VALUE / secondPart : secondPart < Integer.MIN_VALUE / firstPart) {
                throw new OverflowException("overflow, operation: multiply");
            }
        }
        if (firstPart < 0) {
            if (secondPart > 0 ? firstPart < Integer.MIN_VALUE / secondPart : secondPart < Integer.MAX_VALUE / firstPart) {
                throw new OverflowException("overflow, operation: multiply");
            }
        }
        return firstPart * secondPart;
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return multiply(firstPart, secondPart);
    }
}

