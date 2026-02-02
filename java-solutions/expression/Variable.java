package expression;

import java.util.Objects;

public class Variable implements InterfaceExpression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }
    
    @Override
    public int evaluate(int x, int y, int z) {
        if ("x".equals(this.variable)) {
            return x;
        }
        if ("y".equals(this.variable)) {
            return y;
        }
        if ("z".equals(this.variable)) {
            return z;
        }
        return 0;
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        if ("x".equals(this.variable)) {
            return x;
        }
        if ("y".equals(this.variable)) {
            return y;
        }
        if ("z".equals(this.variable)) {
            return z;
        }
        return 0;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variable that = (Variable) obj;
        return variable.equals(that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}

