package edu.hw5;

import edu.hw5.task7.RegularExpressionsForString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularExpressionsForStringTest {
    @Test
    void willReturnTrueIfLengthIsGreaterThanThreeAndTheThirdIsZeroElseReturnFalse() {
        assertTrue(RegularExpressionsForString.isLeastThreeChars("110"));
        assertTrue(RegularExpressionsForString.isLeastThreeChars("000"));
        assertTrue(RegularExpressionsForString.isLeastThreeChars("10011111"));

        assertFalse(RegularExpressionsForString.isLeastThreeChars("00"));
        assertFalse(RegularExpressionsForString.isLeastThreeChars("111"));

    }

    @Test
    void willReturnTrueIfBeginningAndEndTheSameElseReturnFalse() {

        assertTrue(RegularExpressionsForString.isSameBeginningAndEnd("10001"));
        assertTrue(RegularExpressionsForString.isSameBeginningAndEnd("11111"));
        assertTrue(RegularExpressionsForString.isSameBeginningAndEnd("00"));

        assertFalse(RegularExpressionsForString.isSameBeginningAndEnd("10"));
        assertFalse(RegularExpressionsForString.isSameBeginningAndEnd("011101"));
    }

    @Test
    void willReturnTrueIfLengthIsFromOneToThree() {

        assertTrue(RegularExpressionsForString.isFromOneToThree("1"));
        assertTrue(RegularExpressionsForString.isFromOneToThree("11"));
        assertTrue(RegularExpressionsForString.isFromOneToThree("101"));

        assertFalse(RegularExpressionsForString.isFromOneToThree("1010"));
        assertFalse(RegularExpressionsForString.isFromOneToThree(""));
    }

}
