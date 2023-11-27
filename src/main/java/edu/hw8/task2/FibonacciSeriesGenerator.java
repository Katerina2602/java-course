package edu.hw8.task2;

import java.util.List;

public class FibonacciSeriesGenerator {
    private static final int START_INDEX = 3;
    private static final int PREV_INDEX = 2;
    private static final int PREV_PREV_INDEX = 3;

    public void findFibonacciSeries(int n, List<Integer> series) {
        if (n <= 2) {
            if (n == 1) {
                series.add(0);
            }
            if (n == 2) {
                series.add(0);
                series.add(1);
            }
            return;
        }

        int i = START_INDEX;
        series.add(0);
        series.add(1);

        while (i <= n) {
            series.add(series.get(i - PREV_INDEX) + series.get(i - PREV_PREV_INDEX));
            i++;
        }
    }
}
