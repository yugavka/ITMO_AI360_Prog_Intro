import java.io.*;
import java.util.*;

public class Reverse {
    public static void main(String[] args) {
        int lengthOfArray = 0;
        int lengthOfNumber;
        int[][] numbersOfLines = new int[16][];
        try {
            MyScanner scannerMain = new MyScanner(System.in);
            try {
                while (scannerMain.hasNextLine()) {
                    String currentString = scannerMain.nextLine();
                    try {
                        MyScanner scannerNumber = new MyScanner(currentString);
                        lengthOfNumber = 0;
                        numbersOfLines[lengthOfArray] = new int[16];
                        try {
                            while (scannerNumber.hasNextInt()) {
                                numbersOfLines[lengthOfArray][lengthOfNumber] = scannerNumber.nextInt();;
                                lengthOfNumber++;
                                if (lengthOfNumber >= numbersOfLines[lengthOfArray].length) {
                                    numbersOfLines[lengthOfArray] = Arrays.copyOf(
                                            numbersOfLines[lengthOfArray], numbersOfLines[lengthOfArray].length * 2);
                                }
                            }
                            numbersOfLines[lengthOfArray] = Arrays.copyOf(numbersOfLines[lengthOfArray], lengthOfNumber);
                            lengthOfArray++;
                            if (lengthOfArray >= numbersOfLines.length) {
                                numbersOfLines = Arrays.copyOf(numbersOfLines, numbersOfLines.length * 2);
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
        for (int i = numbersOfLines.length - 1; i >= 0; i--) {
            for (int j = numbersOfLines[i].length - 1; j >= 0; j--) {
                System.out.print(numbersOfLines[i][j]);
                if (j > 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}