package expression.generic;

public interface CharSource {
    boolean hasNext();

    char next();

    IllegalArgumentException error(String message);
}
