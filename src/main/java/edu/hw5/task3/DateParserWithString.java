package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParserWithString implements DateParser {
    private static final Pattern PATTERN = Pattern.compile("^(\\d+) days? ago$");
    private final DateParser nextParser;

    public DateParserWithString(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(matcher.group(1))));

        } else if (nextParser != null) {
            return nextParser.parseDate(string);
        } else {
            return Optional.empty();
        }
    }
}
