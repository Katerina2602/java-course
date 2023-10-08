package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task6Test {
    @ParameterizedTest
    @ValueSource(ints = {12340,123,1000,3333,-3433})
    void willReturnMinusOneIfNumberDoesNotMatch(int number) {
        assertEquals(-1, Task6.countSteps(number));
    }

    @Test
    void willWorkCorrectlyGivenTheCorrectNumbers() {
        assertEquals(3, Task6.countSteps(3524));
        assertEquals(0, Task6.countSteps(6174));
        assertEquals(6, Task6.countSteps(3303));
        assertEquals(5, Task6.countSteps(6621));
        assertEquals(4, Task6.countSteps(6554));
        assertEquals(3, Task6.countSteps(1234));
        assertEquals(5, Task6.countSteps(2221));
    }

}
