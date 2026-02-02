package expression;

import java.util.*;

public class Const implements InterfaceExpression {
    private Number valueOfConst;

    public Const(int valueOfConst) {
        this.valueOfConst = valueOfConst;
    }

    public Const(double valueOfConst) {
        this.valueOfConst = valueOfConst;
    }

    @Override
    public int evaluate(int x) {
        return valueOfConst.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return valueOfConst.intValue();
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return valueOfConst.doubleValue();
    }

    @Override
    public String toString() {
        return String.valueOf(valueOfConst);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Const that = (Const) obj;
        return valueOfConst.equals(that.valueOfConst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueOfConst);
    }
}

