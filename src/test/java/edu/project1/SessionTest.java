package edu.project1;

import edu.project1.model.Defeat;
import edu.project1.model.FailedGuess;
import edu.project1.model.GuessResult;
import edu.project1.model.SuccessfulGuess;
import edu.project1.model.Win;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void willReturnDefeatWhenLastAttemptIsFailed() {
        Session session = new Session("abc", new char[] {'*', '*', '*'}, 3, 2);

        GuessResult result = session.guess('x');

        assertTrue(result instanceof Defeat);
    }

    @Test
    void willReturnSuccessfulResultWhenAttemptIsSuccess() {
        Session session = new Session("abc", new char[] {'*', '*', '*'}, 3, 0);

        GuessResult result = session.guess('a');

        assertTrue(result instanceof SuccessfulGuess);
    }

    @Test
    void willReturnFailedResultWhenAttemptIsFailed() {
        Session session = new Session("abc", new char[] {'*', '*', '*'}, 3, 0);

        GuessResult result = session.guess('x');

        assertTrue(result instanceof FailedGuess);
    }

    @Test
    void willReturnWinWhenAllAttemptsSuccess() {
        Session session = new Session("abc", new char[] {'*', '*', '*'}, 3, 0);

        session.guess('a');
        session.guess('b');
        GuessResult result = session.guess('c');

        assertTrue(result instanceof Win);
    }

    @Test
    void willReturnDefeatWhenGiveUp() {
        Session session = new Session("abc", new char[] {'*', '*', '*'}, 3, 2);

        GuessResult result = session.giveUp();

        assertTrue(result instanceof Defeat);
    }
}
