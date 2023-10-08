package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int sourceNumber) {
        String numberStr = String.valueOf(Math.abs(sourceNumber));

        while (numberStr.length() > 1) {
            if (palindrome(numberStr)) {
                return true;
            } else {
                numberStr = createChild(numberStr);
            }
        }
        return false;
    }

    public static boolean palindrome(String str) {
        return str.contentEquals(new StringBuilder(str).reverse());
    }

    public static String createChild(String parent) {
        StringBuilder child = new StringBuilder();

        for (int i = 0; i < parent.length() - 1; i += 2) {
            child.append(Character.getNumericValue(parent.charAt(i))
                + Character.getNumericValue(parent.charAt(i + 1)));
        }

        if (parent.length() % 2 != 0) {
            child.append(parent.charAt(parent.length() - 1));
        }

        return child.toString();
    }
}
