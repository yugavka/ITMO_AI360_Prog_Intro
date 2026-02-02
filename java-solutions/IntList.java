import java.util.*;

public class IntList {
    private final int LIST_SIZE = 64;
    private int[] ints = new int[LIST_SIZE];
    private int[] lineIndexes = new int[LIST_SIZE];
    private int arrayLength = 0;
    private int count = 0;
    public void add(int element) {
        if (arrayLength >= LIST_SIZE) {
            ints = Arrays.copyOf(ints, arrayLength * 2);
        }
        ints[arrayLength] = element;
        arrayLength++;
    }

    public void addCount() {
        count++;
    }

    public void addWithIndex(int element, int currentIndex) {
        if (arrayLength >= LIST_SIZE) {
            ints = Arrays.copyOf(ints, arrayLength * 2);
            lineIndexes = Arrays.copyOf(lineIndexes, arrayLength * 2);
        }
        ints[arrayLength] = element;
        lineIndexes[arrayLength] = currentIndex;
        arrayLength++;
    }

    private int[] decreaseSize(int[] ints) {
        ints = Arrays.copyOf(ints, arrayLength);
        return ints;
    }

    public int[] returnList() {
        return decreaseSize(ints);
    }

    public boolean isAddNeeded(int currentIndex) {
        if (arrayLength == 0) {
            return true;
        }
        return lineIndexes[arrayLength - 1] != currentIndex;
    }

    public void replaceNumber(int number) {
        ints[arrayLength - 1] = number;
    }

    public int returnCount() {
        return count;
    }

    public int size() {
        return arrayLength;
    }
}