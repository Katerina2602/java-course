package edu.hw3.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AtbashCipherTest {
    private static final AtbashCipher atbashCipher = new AtbashCipher();

    @Test
    void willReturnEncryptedCharsLatinAlphabet() {

        assertEquals("Svool dliow!", atbashCipher.encrypt("Hello world!"));
        assertEquals("Привет dliow", atbashCipher.encrypt("Привет world"));
        assertEquals("", atbashCipher.encrypt(""));
        assertEquals(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw.",
            atbashCipher.encrypt("Any fool can write code that a computer can understand.")
        );

    }

}
