package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {
    @Test
    public void willReturnTrueIfPalindrome() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
        assertTrue(Task5.isPalindromeDescendant(13001120));
        assertTrue(Task5.isPalindromeDescendant(23336014));
        assertTrue(Task5.isPalindromeDescendant(11));
    }

    @Test
    public void willReturnFalseIfNotPalindrome() {
        assertFalse(Task5.isPalindromeDescendant(5));
        assertFalse(Task5.isPalindromeDescendant(12));
        assertFalse(Task5.isPalindromeDescendant(348));
    }

    @Test
    public void worksCorrectlyWithNegativeNumbers() {
        assertFalse(Task5.isPalindromeDescendant(-348));
        assertTrue(Task5.isPalindromeDescendant(-565));
    }
}
