public class Sum {
    public static void main(String[] args) {
        int totalSum = 0;
        for (String arg : args) {
            String strippedArg = arg.strip();
            if (strippedArg.isEmpty()) {
                continue;
            }
            int leftPointer = 0;
            for (int rightPointer = 0; rightPointer < strippedArg.length(); rightPointer++) {
                while (rightPointer < strippedArg.length() && !Character.isWhitespace(strippedArg.charAt(rightPointer))) {
                    rightPointer++;
                } if (leftPointer != rightPointer) {
                    totalSum += Integer.parseInt(strippedArg.substring(leftPointer, rightPointer));
                }
                leftPointer = rightPointer + 1;
            }
        }
        System.out.println(totalSum);
    }
}
