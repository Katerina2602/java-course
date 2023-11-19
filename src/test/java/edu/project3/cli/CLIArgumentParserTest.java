package edu.project3.cli;

import edu.project3.cli.model.Options;
import edu.project3.stat.model.StatFormat;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CLIArgumentParserTest {
    @Test
    void willSuccessParseWithAllParams() {
        String path = "/logs/*";
        String from = "2023-09-01";
        String to = "2023-09-02";

        String[] args = new String[] {
            "--path",
            path,
            "--from",
            from,
            "--to",
            to,
            "--format",
            "markdown"
        };

        Options options = CLIArgumentParser.parse(args);

        assertEquals(path, options.getPath());
        assertEquals(LocalDate.parse(from), options.getFrom().get());
        assertEquals(LocalDate.parse(to), options.getTo().get());
        assertEquals(StatFormat.MARKDOWN, options.getFormat().get());
    }

    @Test
    void willSuccessWithOnlyPath() {
        String path = "/logs/*";

        String[] args = new String[] {
            "--path",
            path
        };

        Options options = CLIArgumentParser.parse(args);

        assertEquals(path, options.getPath());
        assertTrue(options.getFrom().isEmpty());
        assertTrue(options.getTo().isEmpty());
        assertTrue(options.getFormat().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-13-13", "2023-09-01T10:10:00", "not_date"})
    void willThrowsExceptionWhenDateIsWrong(String from) {
        String[] args = new String[] {"--path", "/logs/*", "--from", from};

        assertThrows(IllegalArgumentException.class, () -> CLIArgumentParser.parse(args));
    }

    @Test
    void willThrowsExceptionWhenPathIsAbsent() {
        String[] args = new String[] {"--from", "2023-09-01"};

        assertThrows(IllegalArgumentException.class, () -> CLIArgumentParser.parse(args));
    }
}
