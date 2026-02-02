package expression.generic;

public class FloatOperations implements Operation<Float> {
    @Override
    public Float add(Float firstPart, Float secondPart) {
        return firstPart + secondPart;
    }

    @Override 
    public Float min(Float firstPart, Float secondPart) {
        return Float.min(firstPart, secondPart);
    }

    @Override
    public Float max(Float firstPart, Float secondPart) {
        return Float.max(firstPart, secondPart);
    }

    @Override
    public Float count(Float value) {
        return Float.valueOf(Integer.bitCount(Float.floatToIntBits(value)));
    }

    @Override
    public Float substract(Float firstPart, Float secondPart) {
        return firstPart - secondPart;
    }

    @Override
    public Float multiply(Float firstPart, Float secondPart) {
        return firstPart * secondPart;
    }

    @Override
    public Float divide(Float firstPart, Float secondPart) {
        return firstPart / secondPart;
    }

    @Override
    public Float negate(Float firstPart) {
        return -firstPart;
    }

    @Override
    public Float parseConst(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public Float fromInt(int value) {
        return Float.valueOf(value);
    }
}