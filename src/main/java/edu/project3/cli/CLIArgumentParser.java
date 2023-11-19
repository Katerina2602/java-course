package edu.project3.cli;

import edu.project3.cli.model.Options;
import edu.project3.stat.model.StatFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CLIArgumentParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH_KEY = "--path";
    private static final String FROM_KEY = "--from";
    private static final String TO_KEY = "--to";
    private static final String FORMAT_KEY = "--format";

    private CLIArgumentParser() {
    }

    public static Options parse(String[] args) {
        Options options = new Options();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case PATH_KEY -> options.setPath(args[i + 1]);
                case FROM_KEY -> options.setFrom(Optional.of(parseDate(args[i + 1], FROM_KEY)));
                case TO_KEY -> options.setTo(Optional.of(parseDate(args[i + 1], TO_KEY)));
                case FORMAT_KEY -> options.setFormat(Optional.of(StatFormat.parse(args[i + 1])));
                default -> LOGGER.warn("Input unknown argument");
            }
        }

        validateRequiredOptions(options);

        return options;
    }

    private static LocalDate parseDate(String date, String argumentName) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Can't parse value of %s argument".formatted(argumentName), ex);
        }
    }

    private static void validateRequiredOptions(Options options) {
        if (options.getPath() == null || options.getPath().isBlank()) {
            throw new IllegalArgumentException("Absent required argument `%s`".formatted(PATH_KEY));
        }
    }
}
