package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParserWithString extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("^(\\d+) days? ago$");

    @Override
    public Optional<LocalDate> parse(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(matcher.group(1))));
        } else {
            return Optional.empty();
        }
    }
}
