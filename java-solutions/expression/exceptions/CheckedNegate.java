package expression.exceptions;

import expression.InterfaceExpression;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(InterfaceExpression exp) {
        super(exp);
    }

    @Override
    public int performOperation(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("overflow, operation: negate");
        }
        return -value;
    }
}
