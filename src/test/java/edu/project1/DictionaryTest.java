package edu.project1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DictionaryTest {
    @Test
    void willReturnErrorWhenFileNotFound() {
        Dictionary dictionary = new Dictionary(Paths.get("unknown_path"));

        RuntimeException exception = assertThrows(RuntimeException.class, dictionary::randomWord);
        assertThat(exception.getMessage()).contains("Failed to read file");
    }

    @Test
    void willReturnErrorWhenFileIsEmpty() throws IOException {
        List<String> words = List.of("word1", "word2", "word3");

        Path path = Files.createTempFile("dictionary-tmp", ".txt");
        Files.write(path, words);

        Dictionary dictionary = new Dictionary(path);

        String randomWord = dictionary.randomWord();

        assertTrue(words.contains(randomWord));
    }
}
