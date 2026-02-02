import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Wspp {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, IntList> words = new LinkedHashMap<>();
        int totalWordCount = 0;
        try {
            MyScanner scanner = new MyScanner(new File(inputFile));
            try {
                while (scanner.hasNextWord(true)) {
                    String word = scanner.nextWord(true).toLowerCase();
                    totalWordCount++;
                    if (!words.containsKey(word)) {
                        words.put(word, new IntList());
                    }
                    words.get(word).add(totalWordCount);
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
                for (Map.Entry<String, IntList> entry : words.entrySet()) {
                    String word = entry.getKey();
                    IntList positions = entry.getValue();
                    writer.write(word + " " + positions.size());
                    for (int position : positions.returnList()) {
                        writer.write(" " + position);
                    }
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