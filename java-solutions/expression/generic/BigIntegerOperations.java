package expression.generic;

import java.math.BigInteger;

public class BigIntegerOperations implements Operation<BigInteger> {
    @Override
    public BigInteger add(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.add(secondPart);
    }

    @Override 
    public BigInteger min(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.min(secondPart);
    }

    @Override
    public BigInteger max(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.max(secondPart);
    }

    @Override
    public BigInteger count(BigInteger value) {
        return BigInteger.valueOf(value.bitCount());
    }

    @Override
    public BigInteger substract(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.subtract(secondPart);
    }

    @Override
    public BigInteger multiply(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.multiply(secondPart);
    }

    @Override
    public BigInteger divide(BigInteger firstPart, BigInteger secondPart) {
        return firstPart.divide(secondPart);
    }

    @Override
    public BigInteger negate(BigInteger firstPart) {
        return firstPart.negate();
    }

    @Override
    public BigInteger parseConst(String value) {
        return new BigInteger(value);
    }

    @Override
    public BigInteger fromInt(int value) {
        return BigInteger.valueOf(value);
    }
}