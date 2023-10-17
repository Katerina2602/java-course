package edu.project1.model;

public record SuccessfulGuess(char[] word) implements GuessResult {
    @Override
    public char[] state() {
        return word;
    }

    @Override
    public String message() {
        return "Hit!";
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
