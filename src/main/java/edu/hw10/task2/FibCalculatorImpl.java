package edu.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public long fibWithInMemoryCache(int number) {
        return fib(number);
    }

    @Override
    public long fibWithPersistCache(int number) {
        return fib(number);
    }

    private long fib(int number) {
        int a = 0;
        int b = 1;
        int c;

        if (number == 0) {
            return a;
        }
        for (int i = 2; i <= number; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
