import java.math.BigInteger;

public class SumBigIntegerOctal {
    public static void main(String[] args) {
        BigInteger totalSum = BigInteger.ZERO;
        for (String arg : args) {
            String strippArg = arg.strip();
            if (strippArg.isEmpty()) {
                continue;
            }
            int leftP = 0;
            for (int rightP = 0; rightP < strippArg.length(); rightP++) {
                while (rightP < strippArg.length() && !Character.isWhitespace(strippArg.charAt(rightP))) {
                    rightP++;
                } 
                if (leftP != rightP) {
                    if (strippArg.substring(leftP, rightP).charAt(rightP - leftP - 1) == 'o' ||
                            strippArg.substring(leftP, rightP).charAt(rightP - leftP - 1) == 'O') {
                        totalSum = totalSum.add(new BigInteger(strippArg.substring(leftP, rightP - 1), 8));
                    } else {
                        totalSum = totalSum.add(new BigInteger(strippArg.substring(leftP, rightP)));
                    }
                }
                leftP = rightP + 1;
            }
        }
        System.out.println(totalSum);
    }
}

