package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task6Test {
    @Test
    public void willReturnMinusOneIfNumberDoesNotMatch() {
        assertEquals(-1, Task6.countK(12340));
        assertEquals(-1, Task6.countK(123));
        assertEquals(-1, Task6.countK(1000));
        assertEquals(-1, Task6.countK(3333));
        assertEquals(-1, Task6.countK(-3433));
    }

    @Test
    public void willWorkCorrectlyGivenTheCorrectNumbers() {
        assertEquals(3, Task6.countK(3524));
        assertEquals(0, Task6.countK(6174));
        assertEquals(6, Task6.countK(3303));
        assertEquals(5, Task6.countK(6621));
        assertEquals(4, Task6.countK(6554));
        assertEquals(3, Task6.countK(1234));
    }

}
