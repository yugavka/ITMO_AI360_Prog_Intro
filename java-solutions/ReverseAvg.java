import java.util.*;
import java.io.*;

public class ReverseAvg {

    public static int NUMBERS_OF_LINES_DEFAULT = 16;

    public static void main(String[] args) {
        int lengthOfArray = 0;
        int lengthOfNumber;
        int[][] numbersOfLines = new int[NUMBERS_OF_LINES_DEFAULT][];
        try {
            MyScanner scannerMain = new MyScanner(System.in);
            try {
                while (scannerMain.hasNextLine()) {
                    String currentString = scannerMain.nextLine();
                    try {
                        lengthOfNumber = 0;
                        numbersOfLines[lengthOfArray] = new int[NUMBERS_OF_LINES_DEFAULT];
                        MyScanner scannerNumber = new MyScanner(currentString);
                        try {
                            while (scannerNumber.hasNextInt()) {
                                numbersOfLines[lengthOfArray][lengthOfNumber] = scannerNumber.nextInt();
                                lengthOfNumber++;
                                if (lengthOfNumber >= numbersOfLines[lengthOfArray].length) {
                                    numbersOfLines[lengthOfArray] = Arrays.copyOf(
                                            numbersOfLines[lengthOfArray], 2 * lengthOfNumber);
                                }
                            }
                            numbersOfLines[lengthOfArray] = Arrays.copyOf(numbersOfLines[lengthOfArray], lengthOfNumber);
                            lengthOfArray++;
                            if (lengthOfArray >= numbersOfLines.length) {
                                numbersOfLines = Arrays.copyOf(numbersOfLines, 2 * lengthOfArray);
                            }
                        } finally {
                            scannerNumber.close();
                        }
                    } catch (IOException e) {
                        System.out.println("IOException scannerNumber" + e.getMessage());
                    }
                }
            } finally {
                scannerMain.close();
            }
        } catch (IOException e) {
            System.out.println("IOException scannerMain" + e.getMessage());
        }

        numbersOfLines = Arrays.copyOf(numbersOfLines, lengthOfArray);
        int maxOfCol = 0;
        for (int[] numbersOfLine : numbersOfLines) {
            if (numbersOfLine.length > maxOfCol) {
                maxOfCol = numbersOfLine.length;
            }
        }

        long[] sumOfRow = new long[lengthOfArray];
        int[] countOfRow = new int[lengthOfArray];
        long[] sumOfCol = new long[maxOfCol];
        int[] countOfCol = new int[maxOfCol];

        for (int i = 0; i < numbersOfLines.length; i++) {
            for (int j = 0; j < numbersOfLines[i].length; j++) {
                sumOfRow[i] += numbersOfLines[i][j];
                countOfRow[i]++;
                sumOfCol[j] += numbersOfLines[i][j];
                countOfCol[j]++;
            }
        }

        for (int i = 0; i < lengthOfArray; i++) {
            for (int j = 0; j < numbersOfLines[i].length; j++) {
                long average = (sumOfRow[i] + sumOfCol[j] - numbersOfLines[i][j]) / (countOfRow[i] + countOfCol[j] - 1);
                System.out.print(average + " ");
            }
            System.out.println();
        }
    }
}