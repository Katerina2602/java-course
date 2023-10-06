package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean palindrome(String str) {
        String newStr = new StringBuilder(str).reverse().toString();
        return str.equals(newStr);
    }

    public static boolean isPalindromeDescendant(int number) {
        int num = number;

        if (num < 0) {
            num = num * -1;
        }

        String numberStr = String.valueOf(num);

        while (numberStr.length() > 1) {
            if (palindrome(numberStr)) {
                return true;
            } else {
                StringBuilder newStr = new StringBuilder();

                for (int i = 0; i < numberStr.length() - 1; i += 2) {
                    newStr.append(Character.getNumericValue(numberStr.charAt(i))
                        + Character.getNumericValue(numberStr.charAt(i + 1)));
                }

                if (numberStr.length() % 2 != 0) {
                    newStr.append(numberStr.charAt(numberStr.length() - 1));
                }

                numberStr = newStr.toString();
            }
        }
        return false;
    }
}
