import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyScanner {
    private static final int BUFFER_SIZE = 512;
    private final char[] buffer = new char[BUFFER_SIZE];
    private BufferedReader reader;
    private char current = 0;
    private int indexCurChar = 0;
    private int read;
    private int currentIndex = 0;

    public MyScanner(InputStream input) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }

    public MyScanner(String line) throws IOException {
        this.reader = new BufferedReader(new StringReader(line));
    }

    public MyScanner(File file) throws FileNotFoundException, IOException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
    }

    public boolean hasNextLine() throws IOException {
        if (indexCurChar < read) {
            return true;
        }
        read = reader.read(buffer);
        indexCurChar = 0;
        return read > 0;
    }

    public String nextLine() throws IOException {
        if (!hasNextLine()) {
            return "";
        }
        StringBuilder line = new StringBuilder();
        while (hasNextLine()) {
            current = buffer[indexCurChar++];
            if (current == '\n') {
                break;
            }
            if (current == '\r') {
                if (hasNextLine()) {
                    if (buffer[indexCurChar] == '\n') {
                        indexCurChar++;
                    }
                }
                break;
            }
            line.append(current);
        }
        return line.toString();
    }

    private boolean isWordChar(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    private boolean isWordCharMod(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || Character.getType(c) == Character.DASH_PUNCTUATION
                || c == '\'' || c == '$' || c == '_';
    }

    public boolean hasNextWord(boolean flag) throws IOException {
        while (hasNextLine()) {
            char currentChar = buffer[indexCurChar];
            if (flag) {
                if (isWordChar(currentChar)) {
                    return true;
                }
            } else {
                if (isWordCharMod(currentChar)) {
                    return true;
                }
            }
            if (currentChar == '\n') {
                currentIndex++;
                indexCurChar++;
            } else if (currentChar == '\r') {
                currentIndex++;
                indexCurChar++;
                if (hasNextLine() && buffer[indexCurChar] == '\n') {
                    indexCurChar++;
                }
            } else {
                indexCurChar++;
            }
        }
        return false;
    }

    public String nextWord(boolean flag) throws IOException {
        StringBuilder word = new StringBuilder();
        if (flag) {
            if (!hasNextLine()) return "";
            while (hasNextLine() && isWordChar(buffer[indexCurChar])) {
                current = buffer[indexCurChar++];
                word.append(current);
            }
        }
        if (!flag) {
            if (!hasNextLine()) return "";
            while (hasNextLine() && isWordCharMod(buffer[indexCurChar])) {
                current = buffer[indexCurChar++];
                word.append(current);
            }
        }
        return word.toString();
    }

    public int getCurrentLine() {
        return currentIndex;
    }

    public boolean hasNextInt() throws IOException {
        while (hasNextLine()) {
            current = buffer[indexCurChar];
            if (Character.isDigit(current) || current == '-') {
                return true;
            }
            else {
                indexCurChar++;
            }
        }
        return false;
    }

    public int nextInt() throws IOException {
        StringBuilder number = new StringBuilder();
        boolean readingNumber = false;
        while (hasNextLine()) {
            current = buffer[indexCurChar];
            if (Character.isDigit(current) || (!readingNumber && current == '-')) {
                number.append(current);
                indexCurChar++;
                readingNumber = true;
            } else if (readingNumber) {
                break;
            } else {
                indexCurChar++;
            }
        }
        if (number.length() == 0) {
            return 0;
        }
        return Integer.parseInt(number.toString());
    }

    public void close() throws IOException {
        reader.close();
    }
}
