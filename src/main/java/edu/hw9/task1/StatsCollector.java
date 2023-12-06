package edu.hw9.task1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final Map<String, double[]> metrics = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        metrics.put(metricName, values);
    }

    public List<Metric> stats() {
        return metrics.entrySet()
            .stream()
            .map(entry -> new Metric(
                entry.getKey(),
                getSum(entry.getValue()),
                getAverage(entry.getValue()),
                getMin(entry.getValue()),
                getMax(entry.getValue())
            ))
            .toList();
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
