package edu.hw3.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RomanNumeralsTest {
    @Test
    void willReturnRomanNumerals() {
        assertEquals("X", RomanNumerals.getRumanNumerals(10));
        assertEquals("XVI", RomanNumerals.getRumanNumerals(16));
        assertEquals("CLV", RomanNumerals.getRumanNumerals(155));
        assertEquals("MCLV", RomanNumerals.getRumanNumerals(1155));
        assertEquals("MMMCMXCIX", RomanNumerals.getRumanNumerals(3999));
    }

    @Test
    void willReturnErrorWhenTheNumberIsNotWithinTheSpecifiedRange() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumerals.getRumanNumerals(40000));
        assertThrows(IllegalArgumentException.class, () -> RomanNumerals.getRumanNumerals(-300));
    }
}
