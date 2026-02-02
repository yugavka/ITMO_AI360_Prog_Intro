package expression.generic;

public class BaseParser {
    protected final CharSource source;
    protected char ch;
    public static final char END = (char) -1;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(char c) {
        return ch == c;
    }

    protected char take() {
        char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(char c) {
        if (test(c)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean take(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!take(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkEOF() {
        return ch == END;
    }

    protected void expect(char c) {
        if (!take(c)) {
            throw source.error("Expected: " + c + " , but found: " + ch);
        }
    }

    protected void expect(String s) {
        for (char c : s.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(char start, char end) {
        return start <= ch && ch <= end;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
