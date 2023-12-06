package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsCollectorTest {
    private static final Random RANDOM = new Random();

    @Test
    void testMultiThreadsPushAndCollectStat() {
        StatsCollector collector = new StatsCollector();

        List<CompletableFuture<Void>> pushFutures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int metricSuffix = i;
            pushFutures.add(
                CompletableFuture.runAsync(
                    () -> collector.push(
                        "metric-" + metricSuffix,
                        new double[] {
                            RANDOM.nextDouble(),
                            RANDOM.nextDouble(),
                            RANDOM.nextDouble(),
                            RANDOM.nextDouble(),
                            RANDOM.nextDouble()
                        }
                    )
                )
            );
        }

        List<CompletableFuture<List<Metric>>> statsFutures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            statsFutures.add(
                CompletableFuture.supplyAsync(collector::stats)
            );
        }

        CompletableFuture.allOf(pushFutures.toArray(CompletableFuture[]::new));
        CompletableFuture.allOf(statsFutures.toArray(CompletableFuture[]::new));

        statsFutures.stream()
            .map(CompletableFuture::join)
            .forEach(System.out::println);
    }

    @Test
    void willCorrectCalculateStat() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric-1", new double[] {1, 2, 3});
        collector.push("metric-2", new double[] {0, 0, 0});

        List<Metric> stats = collector.stats();

        Metric metric1 = stats.get(1);
        Metric metric2 = stats.get(0);

        assertEquals("metric-1", metric1.name());
        assertEquals(6, metric1.sum());
        assertEquals(2, metric1.average());
        assertEquals(1, metric1.min());
        assertEquals(3, metric1.max());

        assertEquals("metric-2", metric2.name());
        assertEquals(0, metric2.sum());
        assertEquals(0, metric2.average());
        assertEquals(0, metric2.min());
        assertEquals(0, metric2.max());
    }
}
