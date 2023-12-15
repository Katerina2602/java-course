package edu.hw9.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryFinderTest {
    private static final Path ROOT = Path.of("src/test/resources/hw9");

    @Test
    void willReturnDirectoriesHasMoreFilesThenOne() throws IOException {
        List<Path> dirs = DirectoryFinder.findDirectoriesHaveMoreFilesThen(ROOT, 1);

        assertEquals(2, dirs.size());
        assertThat(dirs)
            .contains(Path.of("src/test/resources/hw9/dir2"))
            .contains(Path.of("src/test/resources/hw9/dir3"));
    }

    @Test
    void willReturnDirectoriesHasMoreFilesThenTwo() throws IOException {
        List<Path> dirs = DirectoryFinder.findDirectoriesHaveMoreFilesThen(ROOT, 2);

        assertEquals(1, dirs.size());
        assertThat(dirs).contains(Path.of("src/test/resources/hw9/dir3"));
    }

    @Test
    void willReturnEmptyDirectoriesHasMoreFilesThenThree() throws IOException {
        List<Path> dirs = DirectoryFinder.findDirectoriesHaveMoreFilesThen(ROOT, 3);

        assertTrue(dirs.isEmpty());
    }
}
