package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String str) {
        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < str.length() - 1; i += 2) {
            newStr.append(str.charAt(i + 1)).append(str.charAt(i));
        }

        if (str.length() != newStr.length()) {
            newStr.append(str.charAt(str.length() - 1));
        }
        return newStr.toString();
    }
}
