package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Thread.sleep;

public class PiCalculation {
    private static final AtomicInteger TOTAL_COUNT = new AtomicInteger(0);
    private static final AtomicInteger CIRCLE_COUNT = new AtomicInteger(0);
    private static final int ONE_HUNDRED = 100;
    private static final double FOUR = 4.0;

    private PiCalculation() {
    }

    public static double funk(int n, int threads) throws InterruptedException {
        for (int i = 0; i < threads; i++) {
            new MyThread(n / threads).start();
        }

        sleep(ONE_HUNDRED);

        return (FOUR * CIRCLE_COUNT.get() / TOTAL_COUNT.get());
    }

    private static boolean isPointInCircle(double r, double x, double y) {
        return (x * x + y * y) <= r * r;
    }

    static class MyThread extends Thread {
        private final int n;

        MyThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            Random r = new Random();
            for (int i = 0; i <= n; i++) {
                if (isPointInCircle(1.0, r.nextDouble(), r.nextDouble())) {
                    CIRCLE_COUNT.incrementAndGet();
                }
                TOTAL_COUNT.incrementAndGet();
            }
        }
    }
}
