package expression.exceptions;

import expression.InterfaceExpression;
import expression.Add;  

public class CheckedAdd extends Add {
    public CheckedAdd(InterfaceExpression left, InterfaceExpression right) {
        super(left, right);
    }

    @Override
    public int performOperation(int firstPart, int secondPart) {
        if (secondPart > 0 ? firstPart > Integer.MAX_VALUE - secondPart : firstPart < Integer.MIN_VALUE - secondPart) {
            throw new OverflowException("overflow, operation: add");
        }
        return firstPart + secondPart;
    }
}

