package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateParserWithDash implements DateParser {
    private static final Pattern PATTERN = Pattern.compile("^\\d{4}-\\d{2}-(\\d|\\d{2})$");
    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("[yyyy]-[MM]-[dd][d]");
    private final DateParser nextParser;

    public DateParserWithDash(DateParser nextParser) {
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
