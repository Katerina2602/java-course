package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    private static final int LENGTH_ALPHABET = 25;

    private Alphabet() {

    }

    public static Map<Character, Character> encryptAlphabet() {
        Map<Character, Character> emptyMap = new HashMap<>();
        int num = LENGTH_ALPHABET;
        char symbol = 'A';

        while (symbol != 'z' + 1) {
            emptyMap.put(symbol, (char) (symbol + num));
            symbol++;
            num -= 2;

            if (num < -LENGTH_ALPHABET) {
                num = LENGTH_ALPHABET;
            }

            if (symbol == '[') {
                symbol = 'a';
            }
        }

        return emptyMap;
    }
}
