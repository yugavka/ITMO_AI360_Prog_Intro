package expression;

import java.util.*;

public abstract class AbstractOperation implements InterfaceExpression {
    protected final InterfaceExpression left;
    protected final InterfaceExpression right;
    protected abstract int performOperation(int firstPart, int secondPart);
    protected abstract double performOperation(double firstPart, double secondPart);
    protected abstract String OperationDelimiter();

    protected AbstractOperation(InterfaceExpression left, InterfaceExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x) {
        return performOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return performOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return performOperation(left.evaluateD(x, y, z), right.evaluateD(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + OperationDelimiter() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractOperation that = (AbstractOperation) obj;
        return left.equals(that.left) && right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getClass());
    }
}
