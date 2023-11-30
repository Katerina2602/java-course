package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateParserWithSlash extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("^(\\d|\\d{2})/(\\d|\\d{2})/(\\d{2}|\\d{4})$");
    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("[dd][d]/[MM][M]/[yyyy][yy]");

    @Override
    public Optional<LocalDate> parse(String string) {
        if (PATTERN.matcher(string).matches()) {
            return Optional.of(LocalDate.parse(string, DATA_FORMATTER));
        } else {
            return Optional.empty();
        }
    }
}
