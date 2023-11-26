package edu.hw7.task2;

import java.util.stream.IntStream;

public class Factorial {
    private Factorial() {

    }

    public static int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return IntStream.rangeClosed(2, f).parallel().reduce((x, y) -> x * y).getAsInt();
        }
    }
}
