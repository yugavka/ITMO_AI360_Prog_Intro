package expression.generic;

public class ExpressionParser<T> extends BaseParser {
    private final Operation<T> operation;

    private ExpressionParser(CharSource source, Operation<T> operation) {
        super(source);
        this.operation = operation;
    }

    public static <T> InterfaceExpression<T> parse(String expression, Operation<T> operation) {
        return new ExpressionParser<>(new StringSource(expression), operation).parse();
    }

    private InterfaceExpression<T> parse() {
        final InterfaceExpression<T> result = parseMinMax();
        if (!checkEOF()) {
            throw source.error("Expected EOF, but found: " + ch);
        }
        return result;
    }

    private InterfaceExpression<T> parseMinMax() {
        skipWhitespace();
        InterfaceExpression<T> result = parseAddSubtract();
        while (true) {
            skipWhitespace();
            if (take("m")) {
                if (take("in")) {
                    result = new Min<>(result, parseAddSubtract(), operation);
                } else if (take("ax")) {
                    result = new Max<>(result, parseAddSubtract(), operation);
                }
            } else {
                return result;
            }
        }
    }

    private InterfaceExpression<T> parseAddSubtract() {
        skipWhitespace();
        InterfaceExpression<T> result = parseMultiplyDivide();
        while (true) {
            skipWhitespace();
            if (take('+')) {
                InterfaceExpression<T> right = parseMultiplyDivide();
                result = new Add<>(result, right, operation);
            } else if (take('-')) {
                InterfaceExpression<T> right = parseMultiplyDivide();
                result = new Substract<>(result, right, operation);
            } else {
                return result;
            }
        }
    }

    private InterfaceExpression<T> parseMultiplyDivide() {
        skipWhitespace();
        InterfaceExpression<T> result = parseUnary();
        while (true) {
            skipWhitespace();
            if (take('*')) {
                InterfaceExpression<T> right = parseUnary();
                result = new Multiply<>(result, right, operation);
            } else if (take('/')) {
                final InterfaceExpression<T> right = parseUnary();
                result = new Divide<>(result, right, operation);
            } else {
                return result;
            }
        }
    }

    private InterfaceExpression<T> parseUnary() {
        skipWhitespace();
        if (take('-')) {
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate<>(parseUnary(), operation);
        } else if (take("count")) {
            return new Count<>(parseUnary(), operation);
        } else if (take('+')) {
            return parseUnary();
        } else if (take('(')) {
            final InterfaceExpression<T> result = parseMinMax();
            expect(')');
            return result;
        } else if (between('0', '9')) {
            return parseConst(false);
        }
        return parseVariable();
    }

    private InterfaceExpression<T> parseConst(boolean negative) {
        skipWhitespace();
        final StringBuilder builder = new StringBuilder();
        if (negative) {
            builder.append('-');
        }
        while (between('0', '9')) {
            builder.append(take());
        }
        return new Const<>(operation.parseConst(builder.toString()));
    }

    private InterfaceExpression<T> parseVariable() {
        char name = take();
        return new Variable<>(String.valueOf(name));
    }
}

