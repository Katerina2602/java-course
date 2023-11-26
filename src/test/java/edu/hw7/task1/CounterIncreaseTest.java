package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterIncreaseTest {
    @Test
    void willReturnCorrectResult() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            CounterIncrease.willIncrementTheCounter();
        }
        assertEquals(2000, CounterIncrease.count.get());
    }
}
