package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserWithWord extends DateParser {
    @Override
    public Optional<LocalDate> parse(String strDate) {
        return switch (strDate) {
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case null, default -> Optional.empty();
        };
    }
}
