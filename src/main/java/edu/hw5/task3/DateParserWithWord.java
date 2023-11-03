package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserWithWord implements DateParser {
    private final DateParser nextParser;

    public DateParserWithWord(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        return switch (strDate) {
            case null -> Optional.empty();
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            default -> {
                if (nextParser != null) {
                    yield nextParser.parseDate(strDate);
                } else {
                    yield Optional.empty();
                }
            }
        };
    }
}
