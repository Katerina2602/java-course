package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateParserWithSlash implements DateParser {
    private static final Pattern PATTERN = Pattern.compile("^(\\d|\\d{2})/(\\d|\\d{2})/(\\d{2}|\\d{4})$");
    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("[dd][d]/[MM][M]/[yyyy][yy]");
    private final DateParser nextParser;

    public DateParserWithSlash(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (PATTERN.matcher(string).matches()) {
            return Optional.of(LocalDate.parse(string, DATA_FORMATTER));

        } else if (nextParser != null) {
            return nextParser.parseDate(string);
        } else {
            return Optional.empty();
        }
    }
}
