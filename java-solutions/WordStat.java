import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStat {
    public static void main(String[] args) {
        Map<String, Integer> words = new LinkedHashMap<>();
        String inputFile = args[0];
        String outputFile = args[1];
        try {
            MyScanner scanner = new MyScanner(new File(inputFile));
            try {
                while (scanner.hasNextWord(true)) {
                    String word = scanner.nextWord(true).toLowerCase();
                    words.put(word, words.getOrDefault(word, 0) + 1);
                }
            } finally {
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input IO error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, Integer> word : words.entrySet()) {
                    writer.write(word.getKey() + " " + word.getValue());
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