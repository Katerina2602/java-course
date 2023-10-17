package edu.project1.model;

public record FailedGuess(char[] word, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public char[] state() {
        return word;
    }

    @Override
    public String message() {
        return "Missed, mistake %s out of %s".formatted(attempt, maxAttempts);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
