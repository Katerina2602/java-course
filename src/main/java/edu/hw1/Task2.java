package edu.hw1;

public class Task2 {
    private static final int TEN = 10;

    private Task2() {
    }

    public static int countDigits(int sourceNumber) {
        if (sourceNumber == 0) {
            return 1;
        }

        int count = 0;
        int currentNumber = sourceNumber;

        while (currentNumber != 0) {
            currentNumber = currentNumber / TEN;
            count++;
        }

        return count;
    }
}
