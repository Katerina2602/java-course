package edu.hw5;

import edu.hw5.task6.SubsequenceCheck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsequenceCheckTest {
    @Test
    void test(){
        assertTrue(SubsequenceCheck.checksTheRequiredCharacter("abc", "ajffabcgd"));
    }
}
