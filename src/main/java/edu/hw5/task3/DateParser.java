package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    private DateParser nextParser;

    public void setNextParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public Optional<LocalDate> parseDate(String string) {
        Optional<LocalDate> parsed = parse(string);

        if (parsed.isPresent()) {
            return parsed;
        } else {
            if (nextParser != null) {
                return nextParser.parseDate(string);
            } else {
                return Optional.empty();
            }
        }
    }

    public abstract Optional<LocalDate> parse(String string);
}
