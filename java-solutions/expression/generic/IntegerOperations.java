package expression.generic;

public class IntegerOperations implements Operation<Integer> {
    private final boolean checkOverflow;

    public IntegerOperations(boolean checkOverflow) {
        this.checkOverflow = checkOverflow;
    }

    @Override
    public Integer add(Integer firstPart, Integer secondPart) {
        if (checkOverflow) {
            if (secondPart > 0 ? firstPart > Integer.MAX_VALUE - secondPart : firstPart < Integer.MIN_VALUE - secondPart) {
                throw new ArithmeticException("overflow, operation: add");
            }
        }
        return firstPart + secondPart;
    }

    @Override
    public Integer min(Integer firstPart, Integer secondPart) {
        if (firstPart < secondPart) {
            return firstPart;
        }
        return secondPart;
    }

    @Override
    public Integer max(Integer firstPart, Integer secondPart) {
        if (firstPart > secondPart) {
            return firstPart;
        }
        return secondPart;
    }

    @Override
    public Integer count(Integer value) {
        return Integer.bitCount(value);
    }

    @Override
    public Integer substract(Integer firstPart, Integer secondPart) {
        if (checkOverflow) {
            if (secondPart > 0 ? firstPart < Integer.MIN_VALUE + secondPart : firstPart > Integer.MAX_VALUE + secondPart) {
                throw new ArithmeticException("overflow, operation: subtract");
            }
        }
        return firstPart - secondPart;
    }

    @Override
    public Integer multiply(Integer firstPart, Integer secondPart) {
        if (checkOverflow) {
            if (firstPart > 0) {
                if (secondPart > 0 ? firstPart > Integer.MAX_VALUE / secondPart : secondPart < Integer.MIN_VALUE / firstPart) {
                    throw new ArithmeticException("overflow, operation: multiply");
                }
            }
            if (firstPart < 0) {
                if (secondPart > 0 ? firstPart < Integer.MIN_VALUE / secondPart : secondPart < Integer.MAX_VALUE / firstPart) {
                    throw new ArithmeticException("overflow, operation: multiply");
                }
            }
        }
        return firstPart * secondPart;
    }

    @Override
    public Integer divide(Integer firstPart, Integer secondPart) {
        if (secondPart == 0) {
            throw new ArithmeticException("Division by zero");
        }
        if (checkOverflow) {
            if (firstPart == Integer.MIN_VALUE && secondPart == -1) {
                throw new ArithmeticException("Overflow");
            }
        }
        return firstPart / secondPart;
    }

    @Override
    public Integer negate(Integer firstPart) {
        if (checkOverflow) {
            if (firstPart == Integer.MIN_VALUE) {
                throw new ArithmeticException("Overflow");
            }
        }
        return -firstPart;
    }

    @Override
    public Integer parseConst(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer fromInt(int value) {
        return value;
    }
}
