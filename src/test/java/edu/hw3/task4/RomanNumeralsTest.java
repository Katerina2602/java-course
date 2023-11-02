package edu.hw3.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RomanNumeralsTest {
    @Test
    void willReturnRomanNumerals() {
        assertEquals("X", RomanNumerals.getRomanNumerals(10));
        assertEquals("XVI", RomanNumerals.getRomanNumerals(16));
        assertEquals("CLV", RomanNumerals.getRomanNumerals(155));
        assertEquals("MCLV", RomanNumerals.getRomanNumerals(1155));
        assertEquals("MMMCMXCIX", RomanNumerals.getRomanNumerals(3999));
    }

    @Test
    void willReturnErrorWhenTheNumberIsNotWithinTheSpecifiedRange() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumerals.getRomanNumerals(40000));
        assertThrows(IllegalArgumentException.class, () -> RomanNumerals.getRomanNumerals(-300));
    }
}
