package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameTest {
    private final Dictionary dictionary = mock(Dictionary.class);
    private final Reader reader = mock(Reader.class);
    private final Game game = new Game(dictionary, reader);

    @Test
    void throwsExceptionWhenRandomWordHasWrongLength() {
        when(dictionary.randomWord()).thenReturn("a");

        RuntimeException exception = assertThrows(RuntimeException.class, game::run);
        assertThat(exception.getMessage()).contains("Length word is wrong");
    }

    @Test
    /* Если метод бы принял первый ответ, то игра  бы завершилась проигрышом на 5 ход. У нас игра делает 6 ходов, значит первый вызов не учитывается.
    Как и требуеся в условии. При вводе символа неккоректной длины, игра просит повторить ввод */
    @DisplayName("Если бы ")
    void willRepeatInputWhenSymbolIsWrong() {
        when(dictionary.randomWord()).thenReturn("abcde");
        when(reader.read())
            .thenReturn("xxx")
            .thenReturn("x")
            .thenReturn("x")
            .thenReturn("x")
            .thenReturn("x")
            .thenReturn("x");

        game.run();

        verify(reader, times(6)).read();
    }

    @Test
    void willGiveUpWhenInputExit() {
        when(dictionary.randomWord()).thenReturn("abcde");
        when(reader.read()).thenReturn("exit");

        game.run();

        verify(reader, times(1)).read();
    }
}
