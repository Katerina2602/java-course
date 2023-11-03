package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubsequenceCheck {
    private SubsequenceCheck() {

    }

    public static boolean checksTheRequiredCharacter(String s, String t) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(t);
        return matcher.find();
    }
}
