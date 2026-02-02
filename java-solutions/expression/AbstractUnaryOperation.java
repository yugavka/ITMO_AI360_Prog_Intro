package expression;

import java.util.Objects;

public abstract class AbstractUnaryOperation implements InterfaceExpression {
    private final InterfaceExpression exp;

    protected abstract int performOperation(int value);
    protected abstract double performOperation(double value);
    protected abstract String OperationDelimiter();

    protected AbstractUnaryOperation(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public int evaluate(int x) {
        return performOperation(exp.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return performOperation(exp.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return performOperation(exp.evaluateD(x, y, z));
    }

    @Override
    public String toString() {
        return OperationDelimiter() + "(" + exp.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractUnaryOperation that = (AbstractUnaryOperation) obj;
        return exp.equals(that.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp, getClass());
    }
}
