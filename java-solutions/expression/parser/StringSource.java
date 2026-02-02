package expression.parser;

public class StringSource implements CharSource {
    private final String string;
    private int pos;

    public StringSource(String string) {
        this.string = string;
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(pos + ":" + message);
    }

    public int position() {
        return pos;
    }

    public String data() {
        return string;
    }

    public String context(int index, int before, int after) {
        final int start = Math.max(0, index - before);
        final int end = Math.min(string.length(), index + after);
        return string.substring(start, end);
    }
}
