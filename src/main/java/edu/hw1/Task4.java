package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String sourceString) {
        StringBuilder fixedString = new StringBuilder(sourceString.length());

        for (int i = 0; i < sourceString.length() - 1; i += 2) {
            fixedString.append(sourceString.charAt(i + 1)).append(sourceString.charAt(i));
        }

        if (sourceString.length() != fixedString.length()) {
            fixedString.append(sourceString.charAt(sourceString.length() - 1));
        }
        return fixedString.toString();
    }
}
