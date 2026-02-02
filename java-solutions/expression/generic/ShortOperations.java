package expression.generic;

public class ShortOperations implements Operation<Short> {
    @Override
    public Short add(Short firstPart, Short secondPart) {
        return (short) (firstPart + secondPart);
    }

    @Override 
    public Short min(Short firstPart, Short secondPart) {
        if (firstPart < secondPart) {
            return firstPart;
        }
        return secondPart;
    }

    @Override
    public Short max(Short firstPart, Short secondPart) {
        if (firstPart > secondPart) {
            return firstPart;
        }
        return secondPart;
    }

    @Override
    public Short count(Short value) {
        return (short) Integer.bitCount(Short.toUnsignedInt(value));
    }

    @Override
    public Short substract(Short firstPart, Short secondPart) {
        return (short) (firstPart - secondPart);
    }

    @Override
    public Short multiply(Short firstPart, Short secondPart) {
        return (short) (firstPart * secondPart);
    }

    @Override
    public Short divide(Short firstPart, Short secondPart) {
        return (short) (firstPart / secondPart);
    }

    @Override
    public Short negate(Short firstPart) {
        return (short) -firstPart;
    }

    @Override
    public Short parseConst(String value) {
        return Short.parseShort(value);
    }

    @Override
    public Short fromInt(int value) {
        return (short) value;
    }
}