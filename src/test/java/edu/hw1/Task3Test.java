package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    @Test
    public void willReturnTrueWhenArrayNestable() {
        assertTrue(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
        assertTrue(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0}));
        assertTrue(Task3.isNestable(new int[] {3}, new int[] {1, 4}));
    }

    @Test
    public void willReturnFalseWhenArrayIsNotNestable() {
        assertFalse(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
        assertFalse(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
        assertFalse(Task3.isNestable(new int[] {3, -1}, new int[] {4, 0}));
        assertFalse(Task3.isNestable(new int[] {3, 1}, new int[] {0}));
        assertFalse(Task3.isNestable(new int[] {3, 1}, new int[] {3, 1}));
        assertFalse(Task3.isNestable(new int[] {-2, -4}, new int[] {-5}));
        assertFalse(Task3.isNestable(new int[] {-2}, new int[] {-1}));
    }
}
