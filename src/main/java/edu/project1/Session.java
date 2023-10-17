package edu.project1;

import edu.project1.model.Defeat;
import edu.project1.model.FailedGuess;
import edu.project1.model.GuessResult;
import edu.project1.model.SuccessfulGuess;
import edu.project1.model.Win;
import java.util.ArrayList;
import java.util.List;

public class Session {
    public Session(String answer, char[] maskedAnswer, int maxAttempts, int attempts) {
        this.answer = answer;
        this.maskedAnswer = maskedAnswer;
        this.maxAttempts = maxAttempts;
        this.attempts = attempts;
        this.guessSymbolCount = 0;
    }

    private final String answer;
    private final char[] maskedAnswer;
    private final int maxAttempts;
    private int attempts;
    private int guessSymbolCount;

    public GuessResult guess(char guess) {
        List<Integer> indexes = getIndexesOfSymbol(guess);

        if (indexes.isEmpty()) {
            attempts++;
            if (attempts >= maxAttempts) {
                return new Defeat(maskedAnswer);
            } else {
                return new FailedGuess(maskedAnswer, attempts, maxAttempts);
            }
        }

        replaceSymbols(indexes, guess);

        if (isWin()) {
            return new Win(maskedAnswer);
        } else {
            return new SuccessfulGuess(maskedAnswer);
        }
    }

    private boolean isWin() {
        return guessSymbolCount == answer.length();
    }

    private void replaceSymbols(List<Integer> indexes, char symbol) {
        for (Integer index : indexes) {
            if (maskedAnswer[index] == '*') {
                maskedAnswer[index] = symbol;
                guessSymbolCount++;
            }
        }
    }

    public GuessResult giveUp() {
        return new Defeat(maskedAnswer);
    }

    private List<Integer> getIndexesOfSymbol(char symbol) {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < answer.length(); i++) {
            if (symbol == answer.charAt(i)) {
                indexes.add(i);
            }
        }

        return indexes;
    }
}
