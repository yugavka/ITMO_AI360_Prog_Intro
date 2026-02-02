package expression.generic;

public interface InterfaceExpression<T> {
    T evaluate(T x, T y, T z);
}