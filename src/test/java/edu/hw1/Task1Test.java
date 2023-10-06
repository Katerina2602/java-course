package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    @Test
    void willReturnMinusOneWhenSecondsMoreOrEqualsSixty() {
        assertEquals(-1, Task1.timeToSeconds("12:60"));
        assertEquals(-1, Task1.timeToSeconds("12:61"));
        assertEquals(-1, Task1.timeToSeconds("12:120"));
        assertEquals(-1, Task1.timeToSeconds("12:312"));
    }

    @Test
    void willReturnMinusOneWhenIncorrectDataReceived() {
        assertEquals(-1, Task1.timeToSeconds("1212"));
        assertEquals(-1, Task1.timeToSeconds("12c:12"));
        assertEquals(-1, Task1.timeToSeconds(":"));
    }

    @Test
    void willReturnSecondsWhenMinutesOrSecondsStartsWithZero() {
        assertEquals(120, Task1.timeToSeconds("002:00"));
        assertEquals(602, Task1.timeToSeconds("10:002"));
        assertEquals(0, Task1.timeToSeconds("00:00"));
    }

    @Test
    void willReturnSecondsWhenMinutesOrSecondsWrittenInOneNumber() {
        assertEquals(130, Task1.timeToSeconds("2:10"));
        assertEquals(605, Task1.timeToSeconds("10:5"));
    }

    @Test
    void willReturnSecondsWhenMinutesMoreOrOneHundred() {
        assertEquals(59999, Task1.timeToSeconds("999:59"));
    }
}
