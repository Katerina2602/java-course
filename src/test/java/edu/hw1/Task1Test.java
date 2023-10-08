package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    @ParameterizedTest
    @ValueSource(strings = { "12:60", "12:61", "12:120", "12:312"})
    void willReturnMinusOneWhenSecondsMoreOrEqualsSixty(String time) {
        assertEquals(-1, Task1.convertTimeToSeconds(time));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1212", "12c:12",":", "12:12:12","aps:aps" })
    void willReturnMinusOneWhenIncorrectDataReceived(String time) {
        assertEquals(-1, Task1.convertTimeToSeconds(time));
    }

    @Test
    void willReturnSecondsWhenMinutesOrSecondsStartsWithZero() {
        assertEquals(120, Task1.convertTimeToSeconds("002:00"));
        assertEquals(602, Task1.convertTimeToSeconds("10:002"));
        assertEquals(0, Task1.convertTimeToSeconds("00:00"));
    }

    @Test
    void willReturnSecondsWhenMinutesOrSecondsWrittenInOneNumber() {
        assertEquals(130, Task1.convertTimeToSeconds("2:10"));
        assertEquals(605, Task1.convertTimeToSeconds("10:5"));
    }

    @Test
    void willReturnSecondsWhenMinutesMoreOrOneHundred() {
        assertEquals(59999, Task1.convertTimeToSeconds("999:59"));
    }
}
