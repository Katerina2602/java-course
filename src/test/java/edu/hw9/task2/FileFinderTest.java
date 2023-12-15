package edu.hw9.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileFinderTest {
    private static final Path ROOT = Path.of("src/test/resources/hw9");

    @Test
    void willReturnFilesNotTxtAndNotEmpty() throws IOException {
        List<Path> files = FileFinder.findFilesBySizeAndExtension(
            ROOT,
            size -> size > 0,
            extension -> !extension.equals("txt")
        );

        assertEquals(1, files.size());
        assertThat(files)
            .contains(Path.of("src/test/resources/hw9/dir1/file1.png"));
    }

    @Test
    void willReturnFilesTxtAndEmpty() throws IOException {
        List<Path> files = FileFinder.findFilesBySizeAndExtension(
            ROOT,
            size -> size == 0,
            extension -> extension.equals("txt")
        );

        assertEquals(3, files.size());
        assertThat(files)
            .contains(Path.of("src/test/resources/hw9/dir2/file1.txt"))
            .contains(Path.of("src/test/resources/hw9/dir3/file2.txt"))
            .contains(Path.of("src/test/resources/hw9/dir3/file3.txt"));
    }
}
