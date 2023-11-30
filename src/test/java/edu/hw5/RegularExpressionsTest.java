package edu.hw5;

import edu.hw5.task8.RegularExpressions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularExpressionsTest {
    @Test
    void willReturnTrueIfOddLengthAndReturnFalseIfEvenLength() {
        assertTrue(RegularExpressions.isOddLength("1"));
        assertTrue(RegularExpressions.isOddLength("101"));
        assertTrue(RegularExpressions.isOddLength("10111"));

        assertFalse(RegularExpressions.isOddLength("11"));
        assertFalse(RegularExpressions.isOddLength("1010"));
    }

    @Test
    void willReturnTrueIfTheFirstZeroAndOddLengthOreIfTheFirstUnitAndEvenLengthAndReturnFalseOtherwise() {
        assertTrue(RegularExpressions.isOddIfZeroAndEvenIfOne("10"));
        assertTrue(RegularExpressions.isOddIfZeroAndEvenIfOne("1111"));
        assertTrue(RegularExpressions.isOddIfZeroAndEvenIfOne("011"));
        assertTrue(RegularExpressions.isOddIfZeroAndEvenIfOne("0"));

        assertFalse(RegularExpressions.isOddIfZeroAndEvenIfOne("111"));
        assertFalse(RegularExpressions.isOddIfZeroAndEvenIfOne("01"));
    }

    @Test
    void willReturnTrueIfEveryOddOneAndReturnFalseElse() {
        assertTrue(RegularExpressions.isEveryOddOne("10111"));
        assertTrue(RegularExpressions.isEveryOddOne("1"));

        assertFalse(RegularExpressions.isEveryOddOne("00"));
        assertFalse(RegularExpressions.isEveryOddOne("1001"));
    }

    @Test
    void willReturnTrueIfCountOfZerosIdAMultipleOfThreeFalseElse() {
        assertTrue(RegularExpressions.isCountOfZerosIsAMultipleOfThree("000"));
        assertTrue(RegularExpressions.isCountOfZerosIsAMultipleOfThree("10100"));
        assertTrue(RegularExpressions.isCountOfZerosIsAMultipleOfThree("10001000"));
        assertTrue(RegularExpressions.isCountOfZerosIsAMultipleOfThree("111"));

        assertFalse(RegularExpressions.isCountOfZerosIsAMultipleOfThree("00"));
        assertFalse(RegularExpressions.isCountOfZerosIsAMultipleOfThree("1101"));
        assertFalse(RegularExpressions.isCountOfZerosIsAMultipleOfThree("1101000"));

    }

    @Test
    void willReturnTrueToAnyStringExceptTwoUnitsAndThreeUnits() {
        assertTrue(RegularExpressions.isNotTwoOrThreeUnits("10111"));
        assertTrue(RegularExpressions.isNotTwoOrThreeUnits("1"));
        assertTrue(RegularExpressions.isNotTwoOrThreeUnits("1111"));

        assertFalse(RegularExpressions.isNotTwoOrThreeUnits("11"));
        assertFalse(RegularExpressions.isNotTwoOrThreeUnits("111"));

    }

    @Test
    void willReturnTrueIfMoreThatTwoZerosAndLessThanOneUnits() {
        assertTrue(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("001"));
        assertTrue(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("000"));
        assertTrue(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("010000"));

        assertFalse(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("11"));
        assertFalse(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("10001"));
        assertFalse(RegularExpressions.isMoreThatTwoZerosAndLessThanOneUnits("01"));

    }

    @Test
    void willReturnTrueIfNoConsecutiveUnitsAndReturnFalseElse() {
        assertTrue(RegularExpressions.isNoConsecutiveUnits("0010"));
        assertTrue(RegularExpressions.isNoConsecutiveUnits("101010"));
        assertTrue(RegularExpressions.isNoConsecutiveUnits("1"));

        assertFalse(RegularExpressions.isNoConsecutiveUnits("11"));
        assertFalse(RegularExpressions.isNoConsecutiveUnits("000111000"));

    }

}
