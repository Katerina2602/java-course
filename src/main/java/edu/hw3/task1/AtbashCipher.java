package edu.hw3.task1;

import java.util.Map;

public class AtbashCipher {
    public String encrypt(String str) {

        Map<Character, Character> emptyMap = Alphabet.encryptAlphabet();
        StringBuilder chipherStr = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (checkingSymbol(str.charAt(i))) {
                chipherStr.append(emptyMap.get(str.charAt(i)));
            } else {
                chipherStr.append(str.charAt(i));
            }
        }

        return chipherStr.toString();
    }

    private boolean checkingSymbol(char symbol) {
        return (symbol >= 'A' && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z');
    }
}
