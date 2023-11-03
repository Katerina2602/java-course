package edu.hw5;

import edu.hw5.task4.PasswordCheck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordCheckTest {
    @Test
    void willReturnTrueWhenTheSymbolIs(){
        PasswordCheck check= new PasswordCheck();
        assertTrue( check.checksTheRequiredCharacter("dgsvcn!4425"));
    }
    @Test
    void willReturnFalseWhenTheISNoSymbol(){
        PasswordCheck check= new PasswordCheck();
        assertFalse( check.checksTheRequiredCharacter("dgsvcn4425"));
    }
    @Test
    void willReturnFalseWhenThePasswordIsEmpty(){
        PasswordCheck check= new PasswordCheck();
        assertFalse( check.checksTheRequiredCharacter(""));
    }

}
