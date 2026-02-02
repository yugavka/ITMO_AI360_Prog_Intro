package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        return new InnerExpressionParser(new StringSource(expression)).parse();
    }

    private static class InnerExpressionParser extends BaseParser {
        InnerExpressionParser(CharSource source) {
            super(source);
        }

        private TripleExpression parse() {
            InterfaceExpression result = parseBitOr();
            if (!checkEOF()) {
                throw new IllegalArgumentException("Expected EOF, but found:" + take());
            }
            return result;
        }

        private InterfaceExpression parseBitOr() {
            skipWhitespace();
            InterfaceExpression result = parseBitXor();
            while (true) {
                skipWhitespace();
                if (take('|')) {
                    InterfaceExpression right = parseBitXor();
                    result = new BitOr(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseBitXor() {
            skipWhitespace();
            InterfaceExpression result = parseBitAnd();
            while (true) {
                skipWhitespace();
                if (take('^')) {
                    InterfaceExpression right = parseBitAnd();
                    result = new BitXor(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseBitAnd() {
            skipWhitespace();
            InterfaceExpression result = parseAddSubtract();
            while (true) {
                skipWhitespace();
                if (take('&')) {
                    InterfaceExpression right = parseAddSubtract();
                    result = new BitAnd(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseAddSubtract() {
            skipWhitespace();
            InterfaceExpression result = parseMultiplyDivide();
            while (true) {
                skipWhitespace();
                if (take('+')) {
                    InterfaceExpression right = parseMultiplyDivide();
                    result = new Add(result, right);
                } else if (take('-')) {
                    InterfaceExpression right = parseMultiplyDivide();
                    result = new Subtract(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseMultiplyDivide() {
            skipWhitespace();
            InterfaceExpression result = parseUnaryMinus();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    InterfaceExpression right = parseUnaryMinus();
                    result = new Multiply(result, right);
                } else if (take('/')) {
                    InterfaceExpression right = parseUnaryMinus();
                    result = new Divide(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseUnaryMinus() {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst(true);
                }
                return new UnaryMinus(parseUnaryMinus());
            } else if (take('+')) {
                return parseUnaryMinus();
            } else if (take('(')) {
                InterfaceExpression result = parseBitOr();
                expect(')');
                return result;
            } else if (between('0', '9')) {
                return parseConst(false);
            }
            return parseVariable();
        }

        private InterfaceExpression parseConst(boolean negative) {
            skipWhitespace();
            StringBuilder constanta = new StringBuilder();
            if (negative) {
                constanta.append('-');
            }
            while (between('0', '9')) {
                constanta.append(take());
            }
            try {
                return new Const(Integer.parseInt(constanta.toString()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect number");
            }
        }

        private InterfaceExpression parseVariable() {
            char name = take();
            return new Variable(String.valueOf(name));
        }
    }
}
