package expression.generic;

public interface Operation<T> {
    T add(T a, T b);

    T min(T a, T b);

    T max(T a, T b);

    T count(T value);

    T substract(T a, T b);

    T multiply(T a, T b);

    T divide(T a, T b);

    T negate(T a);

    T parseConst(String value);

    T fromInt(int value);
}