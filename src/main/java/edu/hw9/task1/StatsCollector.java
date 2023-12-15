package edu.hw9.task1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final Map<String, double[]> metrics = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        metrics.put(metricName, values);
    }

    public List<Metric> stats() {
        return metrics.entrySet()
            .stream()
            .map(entry -> buildMetric(entry.getKey(), entry.getValue()))
            .toList();
    }

    private Metric buildMetric(String key, double[] values) {
        CompletableFuture<Double> sumFuture = CompletableFuture.supplyAsync(() -> getSum(values));
        CompletableFuture<Double> averageFuture = CompletableFuture.supplyAsync(() -> getAverage(values));
        CompletableFuture<Double> minFuture = CompletableFuture.supplyAsync(() -> getMin(values));
        CompletableFuture<Double> maxFuture = CompletableFuture.supplyAsync(() -> getMax(values));

        CompletableFuture.allOf(sumFuture, averageFuture, minFuture, maxFuture).join();

        return new Metric(
            key,
            sumFuture.join(),
            averageFuture.join(),
            minFuture.join(),
            maxFuture.join()
        );
    }

    private double getSum(double[] values) {
        return Arrays.stream(values)
            .sum();
    }

    private double getAverage(double[] values) {
        return Arrays.stream(values)
            .average()
            .getAsDouble();
    }

    private double getMin(double[] values) {
        return Arrays.stream(values)
            .min()
            .getAsDouble();
    }

    private double getMax(double[] values) {
        return Arrays.stream(values)
            .max()
            .getAsDouble();
    }
}
