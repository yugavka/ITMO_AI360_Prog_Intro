import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatLengthMiddle {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, Integer> words = new LinkedHashMap<>();
        try {
            MyScanner scanner = new MyScanner(new File(inputFile));
            try {
                while (scanner.hasNextWord(true)) {
                    String word = scanner.nextWord(true).toLowerCase();
                    if (word.length() >= 7) {
                        String middle = word.substring(3, word.length() - 3);
                        words.put(middle, words.getOrDefault(middle, 0) + 1);
                    }
                }
            } finally {
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input IO error: " + e.getMessage());
        }
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());
        sortedWords.sort((elFirst, elSecond) -> Integer.compare(elFirst.getKey().length(), elSecond.getKey().length()));
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, Integer> newWord : sortedWords) {
                    writer.write(newWord.getKey() + " " + newWord.getValue());
                    writer.newLine();
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output IO error: " + e.getMessage());
        }
    }
}
