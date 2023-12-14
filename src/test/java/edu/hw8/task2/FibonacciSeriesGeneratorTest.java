package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciSeriesGeneratorTest {

    @Test
    void willSuccessGenerateFibonacciSeriesMultiThreads() {
        ArrayList<Integer> generatedSeries1 = new ArrayList<>();
        ArrayList<Integer> generatedSeries2 = new ArrayList<>();
        ArrayList<Integer> generatedSeries3 = new ArrayList<>();
        ArrayList<Integer> generatedSeries4 = new ArrayList<>();

        try (ThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();
            threadPool.execute(() -> new FibonacciSeriesGenerator().findFibonacciSeries(0, generatedSeries1));
            threadPool.execute(() -> new FibonacciSeriesGenerator().findFibonacciSeries(1, generatedSeries2));
            threadPool.execute(() -> new FibonacciSeriesGenerator().findFibonacciSeries(2, generatedSeries3));
            threadPool.execute(() -> new FibonacciSeriesGenerator().findFibonacciSeries(5, generatedSeries4));

            TimeUnit.SECONDS.sleep(2);

            assertEquals(List.of(), generatedSeries1);
            assertEquals(List.of(0), generatedSeries2);
            assertEquals(List.of(0, 1), generatedSeries3);
            assertEquals(List.of(0, 1, 1, 2, 3), generatedSeries4);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void willSuccessGenerateFibonacciSeriesSingleThread() {
        ArrayList<Integer> generatedSeries = new ArrayList<>();

        new FibonacciSeriesGenerator().findFibonacciSeries(5, generatedSeries);

        assertEquals(List.of(0, 1, 1, 2, 3), generatedSeries);
    }
}
