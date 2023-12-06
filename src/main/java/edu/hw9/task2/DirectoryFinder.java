package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryFinder {
    private DirectoryFinder() {
    }

    public static List<Path> findDirectoriesHaveMoreFilesThen(Path root, int fileCount) throws IOException {
        try (Stream<Path> stream = Files.walk(root)) {
            return stream
                .parallel()
                .filter(path -> isDirectoryAndHasMoreFilesThen(path, fileCount))
                .toList();
        }
    }

    private static boolean isDirectoryAndHasMoreFilesThen(Path path, int fileCount) {
        return Files.isDirectory(path) && hasMoreFilesThen(path, fileCount);
    }

    private static boolean hasMoreFilesThen(Path dir, int fileCount) {
        try (Stream<Path> stream = Files.list(dir)) {
            return stream
                .filter(path -> !Files.isDirectory(path))
                .count() > fileCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
