package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task7Test {
    @Test
    void willWorkCorrectlyWhenRotateRight(){
        assertEquals(4, Task7.rotateRight(8,1));
        assertEquals(8, Task7.rotateRight(8,4));
        assertEquals(6, Task7.rotateRight(17,3));
    }
    @Test
    void willWorkCorrectlyWhenRotateLeft() {
       assertEquals(6, Task7.rotateLeft(17,2));
        assertEquals(1, Task7.rotateLeft(16,1));
        assertEquals(5, Task7.rotateLeft(6,1));
    }

    @Test
    void willWorkCorrectlyWhenShiftIsGreaterThanDigitOfNumber(){
        assertEquals(8, Task7.rotateRight(8,12));
        assertEquals(0, Task7.rotateRight(0,12));
        assertEquals(1, Task7.rotateRight(1,11));
        assertEquals(2, Task7.rotateLeft(8,10));
        assertEquals(3, Task7.rotateLeft(5,10));
    }
}
