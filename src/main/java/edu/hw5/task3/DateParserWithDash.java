package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateParserWithDash extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("^\\d{4}-\\d{2}-(\\d|\\d{2})$");
    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("[yyyy]-[MM]-[dd][d]");

    @Override
    public Optional<LocalDate> parse(String string) {
        if (PATTERN.matcher(string).matches()) {
            return Optional.of(LocalDate.parse(string, DATA_FORMATTER));
        } else {
            return Optional.empty();
        }
    }
}
