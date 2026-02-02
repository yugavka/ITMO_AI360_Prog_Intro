package expression.generic;

public class DoubleOperations implements Operation<Double> {
    @Override
    public Double add(Double firstPart, Double secondPart) {
        return firstPart + secondPart;
    }

    @Override
    public Double min(Double firstPart, Double secondPart) {
        return Double.min(firstPart, secondPart);
    }

    @Override
    public Double max(Double firstPart, Double secondPart) {
        return Double.max(firstPart, secondPart);
    }

    @Override
    public Double count(Double value) {
        return Double.valueOf(Long.bitCount(Double.doubleToLongBits(value)));
    }

    @Override
    public Double substract(Double firstPart, Double secondPart) {
        return firstPart - secondPart;
    }

    @Override
    public Double multiply(Double firstPart, Double secondPart) {
        return firstPart * secondPart;
    }

    @Override
    public Double divide(Double firstPart, Double secondPart) {
        return firstPart / secondPart;
    }

    @Override
    public Double negate(Double firstPart) {
        return -firstPart;
    }

    @Override
    public Double parseConst(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public Double fromInt(int value) {
        return Double.valueOf(value);
    }
}