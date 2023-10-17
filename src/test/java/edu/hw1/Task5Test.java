package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {
    @ParameterizedTest
    @ValueSource(ints = {11211230,13001120,23336014,11})
    void willReturnTrueIfPalindrome(int number) {
        assertTrue(Task5.isPalindromeDescendant(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {5,12,348})
    void willReturnFalseIfNotPalindrome(int number) {
        assertFalse(Task5.isPalindromeDescendant(number));
    }

    @Test
    void worksCorrectlyWithNegativeNumbers() {
        assertFalse(Task5.isPalindromeDescendant(-348));
        assertTrue(Task5.isPalindromeDescendant(-565));
    }
}
