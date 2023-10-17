package edu.project1;

import edu.project1.model.GuessResult;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;
    private static final int WORD_LENGTH = 5;
    private static final char WILDCARD = '*';
    private static final String EXIT = "exit";
    private final Dictionary dictionary;
    private final Reader reader;

    public Game(Dictionary dictionary, Reader reader) {
        this.dictionary = dictionary;
        this.reader = reader;
    }

    public void run() {
        String randomWord = dictionary.randomWord();
        validateWordLength(randomWord);

        Session session = new Session(randomWord, generateTemplate(), MAX_ATTEMPTS, 0);
        GuessResult result;

        do {
            result = tryGuess(session, reader.read());
            printState(result);
        } while (!result.isFinal());
    }

    private GuessResult tryGuess(Session session, String input) {
        String tmpInput = input;

        while (!isValidSymbol(tmpInput)) {
            if (EXIT.equals(tmpInput)) {
                return session.giveUp();
            } else {
                LOGGER.info("Symbol entered incorrectly");
            }
            tmpInput = reader.read();
        }

        return session.guess(tmpInput.charAt(0));
    }

    private void printState(GuessResult guess) {
        LOGGER.info("The word: {}", Arrays.toString(guess.state()));
        LOGGER.info(guess.message());
    }

    private char[] generateTemplate() {
        char[] template = new char[WORD_LENGTH];

        Arrays.fill(template, WILDCARD);

        return template;
    }

    private void validateWordLength(String word) {
        if (word.length() != WORD_LENGTH) {
            throw new RuntimeException("Length word is wrong, word has need %s symbols".formatted(WORD_LENGTH));
        }
    }

    private boolean isValidSymbol(String symbol) {
        return symbol.length() == 1 && symbol.charAt(0) >= 'a' && symbol.charAt(0) <= 'z';
    }
}
