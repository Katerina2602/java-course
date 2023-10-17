package edu.project1.model;

public record Win(char[] word) implements GuessResult {
    @Override
    public char[] state() {
        return word;
    }

    @Override
    public String message() {
        return "You won!";
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
