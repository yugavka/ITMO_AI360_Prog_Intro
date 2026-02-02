package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) throws ParseException {
        return new InnerExpressionParser(new StringSource(expression)).parse();
    }

    private static class InnerExpressionParser extends BaseParser {
        InnerExpressionParser(StringSource source) {
            super(source);
        }

        private TripleExpression parse() throws ParseException {
            skipWhitespace();
            InterfaceExpression result = parseGcdLcm();
            if (!checkEOF()) {
                if (ch == ')' || ch == ']') {
                    throw new ParserParenthesisException("No opening parenthesis" + getContext());
                }
                throw new ParseEOFException("Expected EOF, but found: " + ch + getContext());
            }
            return result;
        }

        private InterfaceExpression parseGcdLcm() throws ParseException {
            skipWhitespace();
            InterfaceExpression result = parseAddSubtract();
            while (true) {
                skipWhitespace();
                if (take("gcd")) {
                    if (Character.isDigit(ch) || Character.isLetter(ch)) {
                        throw new ParseIncorrectSymbolException("Expected separator after gcd" + getContext());
                    }
                    skipWhitespace();
                    result = new CheckedGcd(result, parseAddSubtract());
                } else if (take("lcm")) {
                    if (Character.isDigit(ch) || Character.isLetter(ch)) {
                        throw new ParseIncorrectSymbolException("Expected separator after lcm" + getContext());
                    }
                    skipWhitespace();
                    result = new CheckedLcm(result, parseAddSubtract());
                } else {
                    return result;
                }
            }
        }
        
        private InterfaceExpression parseAddSubtract() throws ParseException {
            skipWhitespace();
            InterfaceExpression result = parseMultiplyDivide();
            while (true) {
                if (take('+')) {
                    InterfaceExpression right = parseMultiplyDivide();
                    result = new CheckedAdd(result, right);
                } else if (take('-')) {
                    InterfaceExpression right = parseMultiplyDivide();
                    result = new CheckedSubtract(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseMultiplyDivide() throws ParseException {
            skipWhitespace();
            InterfaceExpression result = parseUnaryMinus();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    InterfaceExpression right = parseUnaryMinus();
                    result = new CheckedMultiply(result, right);
                } else if (take('/')) {
                    InterfaceExpression right = parseUnaryMinus();
                    result = new CheckedDivide(result, right);
                } else {
                    return result;
                }
            }
        }

        private InterfaceExpression parseUnaryMinus() throws ParseException {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst(true);
                }
                return new CheckedNegate(parseUnaryMinus());
            } else if (take('(')) {
                if (take(')')) {
                    throw new ParserParenthesisException("Empty parentheses" + getContext());
                }
                InterfaceExpression result = parseGcdLcm();
                if (take(']')) {
                    throw new ParserParenthesisException("Missmatched closing parentheses" + getContext());
                }
                if (!take(')')) {
                    throw new ParserParenthesisException("No closing parenthesis" + getContext());
                }
                return result;
            } else if (take('[')) {
                throw new ParserParenthesisException("Missmatched open parenthesis" + getContext());
            } else if (between('0', '9')) {
                return parseConst(false);
            }
            return parseVariable();
        }

        private InterfaceExpression parseConst(boolean negative) throws ParseException {
            StringBuilder constanta = new StringBuilder();
            if (negative) {
                constanta.append('-');
            }
            while (between('0', '9')) {
                constanta.append(take());
            }
            if (between('0', '9')) {
                throw new ParseConstException("Spaces in numbers are not allowed" + getContext());
            }
            try {
                return new Const(Integer.parseInt(constanta.toString()));
            } catch (NumberFormatException e) {
                throw new ParseConstException("Incorrect number" + getContext());
            }
        }

        private InterfaceExpression parseVariable() throws ParseException {
            char name = take();
            if (!(name == 'x' || name == 'y' || name == 'z')) {
                throw new ParseIncorrectSymbolException("Expected correct symbol, but found: " + name + getContext());
            }
            return new Variable(String.valueOf(name));
        }

        private String getContext() {
            StringSource stringSource = (StringSource) source;
            int position = stringSource.position() - 1;
            String context = stringSource.context(position, 5, 5);
            return ", context: " + context + "";
        }
    }
}

