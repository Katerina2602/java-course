package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileFinder {
    private FileFinder() {
    }

    public static List<Path> findFilesBySizeAndExtension(
        Path root,
        Predicate<Long> sizePredicate,
        Predicate<String> extensionPredicate
    ) throws IOException {
        try (Stream<Path> stream = Files.walk(root)) {
            return stream
                .parallel()
                .filter(path -> isFileAndMatchPredicates(path, sizePredicate, extensionPredicate))
                .toList();
        }
    }

    private static boolean isFileAndMatchPredicates(
        Path path,
        Predicate<Long> sizePredicate,
        Predicate<String> extensionPredicate
    ) {
        try {
            return !Files.isDirectory(path)
                && sizePredicate.test(Files.size(path))
                && extensionPredicate.test(getExtension(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getExtension(Path path) {
        String filename = path.getFileName().toString();
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
