package edu.hw3.task4;

@SuppressWarnings("MagicNumber")

public class RomanNumerals {
    private static final int MAX_ROMAN_NUMERAL = 3999;

    private RomanNumerals() {

    }

    public static String getRumanNumerals(int number) {
        int arabicNumber = number;
        if (arabicNumber <= 0 || arabicNumber > MAX_ROMAN_NUMERAL) {
            throw new IllegalArgumentException("Number is not in the specified range");
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder romanNumerals = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (arabicNumber >= values[i]) {
                arabicNumber = arabicNumber - values[i];
                romanNumerals.append(romanLetters[i]);
            }
        }
        return romanNumerals.toString();
    }

}
