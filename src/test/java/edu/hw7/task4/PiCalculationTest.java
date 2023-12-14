package edu.hw7.task4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PiCalculationTest {
    @Test
    @Disabled(
        "Тест сделан с целью удобства выполнения сравнений, " +
            "выполняется долго и ничего не сравнивает, поэтому выключен"
    )
    void test() throws InterruptedException {

        double pi = findsTheAverage(10000, 100);
        System.out.println("Уровень погрешности для симуляции в 10тыс " + Math.abs(Math.PI - pi));
        pi = findsTheAverage(100000, 100);
        System.out.println("Уровень погрешности для симуляции в 100тыс " + Math.abs(Math.PI - pi));
        pi = findsTheAverage(1000000, 100);
        System.out.println("Уровень погрешности для симуляции в 1млн " + Math.abs(Math.PI - pi));
        pi = findsTheAverage(10000000, 100);
        System.out.println("Уровень погрешности для симуляции в 10млн " + Math.abs(Math.PI - pi));

        long executionTime = findsAverageTime(10000, 1);
        System.out.println("Время выполнения 1 потоком " + executionTime);
        executionTime = findsAverageTime(10000, 10);
        System.out.println("Время выполнения 10 потоками " + executionTime);
    }

    double findsTheAverage(int simulations, int threads) throws InterruptedException {
        double sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum = sum + PiCalculation.funk(simulations, threads);
        }
        return sum / 100;
    }

    long findsAverageTime(int simulations, int threads) throws InterruptedException {
        long time = 0;
        long start;
        for (int i = 0; i <= 100; i++) {
            start = System.nanoTime();
            PiCalculation.funk(simulations, threads);
            time = time + System.nanoTime() - start;
        }
        return time / 100;
    }
}
