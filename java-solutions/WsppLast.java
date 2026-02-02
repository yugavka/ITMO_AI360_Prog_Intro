import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WsppLast {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, IntList> words = new LinkedHashMap<>();
        int totalWordCount = 0;
        try {
            MyScanner scanner = new MyScanner(new File(inputFile));
            try {
                while (scanner.hasNextWord(false)) {
                    String word = scanner.nextWord(false).toLowerCase();
                    int lineIndex = scanner.getCurrentLine();
                    totalWordCount++;
                    IntList wordList = words.getOrDefault(word, new IntList());
                    if (wordList.isAddNeeded(lineIndex)) {
                        wordList.addWithIndex(totalWordCount, lineIndex);
                        words.putIfAbsent(word, wordList);
                    } else {
                        wordList.replaceNumber(totalWordCount);
                    }
                    wordList.addCount();
                }
            } finally {
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input IO error: " + e.getMessage());
        }
        List<Map.Entry<String, IntList>> entries = new ArrayList<>(words.entrySet());
        entries.sort(Comparator.comparingInt(word -> word.getKey().length()));
        try {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
                for (Map.Entry<String, IntList> entry : entries) {
                    String word = entry.getKey();
                    int count = entry.getValue().returnCount();
                    int[] positions = entry.getValue().returnList();
                    writer.write(word + " " + count);
                    for (int position : positions) {
                        writer.write(" " + position);
                    }
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output IO error: " + e.getMessage());
        }
    }
}