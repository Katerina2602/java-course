package edu.project1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private static final Random RANDOM = new Random();
    private final Path path;

    public Dictionary(Path path) {
        this.path = path;
    }

    public String randomWord() {
        List<String> words = readFile();
        return randomWord(words);
    }

    private List<String> readFile() {
        try {
            return Files.readAllLines(path);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to read file");
        }
    }

    private String randomWord(List<String> words) {
        return words.get(RANDOM.nextInt(words.size() - 1));
    }
}
