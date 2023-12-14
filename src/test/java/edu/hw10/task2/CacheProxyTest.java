package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CacheProxyTest {
    @Test
    void testInMemoryCache() {
        long expectedResult = 4L;
        int number = 5;

        FibCalculator calculator = mock(FibCalculator.class);
        when(calculator.fibWithInMemoryCache(number)).thenReturn(expectedResult);

        FibCalculator proxyCalculator = CacheProxy.create(calculator, FibCalculator.class);

        long actualResult1 = proxyCalculator.fibWithInMemoryCache(number);
        long actualResult2 = proxyCalculator.fibWithInMemoryCache(number);

        assertEquals(expectedResult, actualResult1);
        assertEquals(expectedResult, actualResult2);

        verify(calculator, times(1)).fibWithInMemoryCache(number);
    }

    @Test
    void testPersistCache() {
        long expectedResult = 4L;
        int number = 5;

        FibCalculator calculator = mock(FibCalculator.class);
        when(calculator.fibWithPersistCache(number)).thenReturn(expectedResult);

        FibCalculator proxyCalculator = CacheProxy.create(calculator, FibCalculator.class);

        long actualResult1 = proxyCalculator.fibWithPersistCache(number);
        long actualResult2 = proxyCalculator.fibWithPersistCache(number);

        assertEquals(expectedResult, actualResult1);
        assertEquals(expectedResult, actualResult2);

        verify(calculator, times(1)).fibWithPersistCache(number);
    }
}
