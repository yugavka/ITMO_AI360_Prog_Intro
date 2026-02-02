package expression.exceptions;

import expression.InterfaceExpression;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    public static int divide(int firstPart, int secondPart) {
        if (secondPart == 0) {
            throw new DivisionByZeroException("division by zero, operation: divide");
        }
        if (firstPart == Integer.MIN_VALUE && secondPart == -1) {
            throw new OverflowException("overflow, operation: divide");
        }
        return firstPart / secondPart;
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        return divide(firstPart, secondPart);
    }
}

