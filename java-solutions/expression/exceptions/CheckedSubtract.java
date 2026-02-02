package expression.exceptions;

import expression.InterfaceExpression;
import expression.Subtract; 

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        if (secondPart > 0 ? firstPart < Integer.MIN_VALUE + secondPart : firstPart > Integer.MAX_VALUE + secondPart) {
            throw new OverflowException("overflow, operation: subtract");
        }
        return firstPart - secondPart;
    }
}

