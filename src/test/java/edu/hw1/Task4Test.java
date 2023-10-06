package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {
    @Test
    public void checkingForAnEvenNumberOfCharacters() {
        assertEquals("2143", Task4.fixString("1234"));
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals(" 1  2 ", Task4.fixString("1    2"));
    }

    @Test
    public void checkingWorkingWithAnEmptyString() {
        assertEquals("", Task4.fixString(""));
    }

    @Test
    public void checkingWorkWithAnOddNumberOfСharacters() {
        assertEquals("21435", Task4.fixString("12345"));
        assertEquals("badce", Task4.fixString("abcde"));
        assertEquals("a", Task4.fixString("a"));
    }
}
