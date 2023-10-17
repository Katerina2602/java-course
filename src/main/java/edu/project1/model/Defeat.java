package edu.project1.model;

public record Defeat(char[] word) implements GuessResult {
    @Override
    public char[] state() {
        return word;
    }

    @Override
    public String message() {
        return "You lost!";
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
