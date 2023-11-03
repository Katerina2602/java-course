package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public interface DateParser {
    Optional<LocalDate> parseDate(String string);
}
