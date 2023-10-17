package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    @Test
    void willReturnCountNumber() {
        assertEquals(3, Task2.countDigits(456));
        assertEquals(1, Task2.countDigits(0));
        assertEquals(6, Task2.countDigits(560002));
    }

    @Test
    void willReturnCountNumberWhenNegativeNumberReceived() {
        assertEquals(3, Task2.countDigits(-254));
        assertEquals(2, Task2.countDigits(-35));
        assertEquals(1, Task2.countDigits(-5));
    }

    @Test
    void willReturnCountNumberWhenThereAreLeadingZeros() {
        assertEquals(2, Task2.countDigits(025));
        assertEquals(4, Task2.countDigits(03050));
        assertEquals(1, Task2.countDigits(0005));
    }

}
