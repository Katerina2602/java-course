package edu.hw1;

public class Task2 {
    private static final int TEN = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        int num = number;

        while (num != 0) {
            num = num / TEN;
            count++;
        }

        return count;
    }
}
