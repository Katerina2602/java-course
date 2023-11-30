package edu.hw5;

import edu.hw5.task3.DateParser;
import edu.hw5.task3.DateParserWithDash;
import edu.hw5.task3.DateParserWithSlash;
import edu.hw5.task3.DateParserWithString;
import edu.hw5.task3.DateParserWithWord;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateParserTest {
    private static DateParser dateParser;

    @BeforeAll
    static void setUp() {
        DateParser dateParserWithWord = new DateParserWithWord();
        DateParser dateParserWithDash = new DateParserWithDash();
        DateParser dateParserWithSlash = new DateParserWithSlash();
        DateParser dateParserWithString = new DateParserWithString();

        dateParserWithWord.setNextParser(dateParserWithDash);
        dateParserWithDash.setNextParser(dateParserWithSlash);
        dateParserWithSlash.setNextParser(dateParserWithString);

        dateParser = dateParserWithWord;
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-10-1", "2020-10-01"})
    void willReturnCorrectDataFormatWithDash(String data) {
        Optional<LocalDate> actual = dateParser.parseDate(data);
        LocalDate expected = LocalDate.of(2020, 10, 1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1/3/20", "01/03/2020"})
    void willReturnCorrectDataFormatWithSlash(String data) {
        Optional<LocalDate> actual = dateParser.parseDate(data);
        LocalDate expected = LocalDate.of(2020, 3, 1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void willReturnCorrectDataFormatWithWord() {
        Optional<LocalDate> tomorrow = dateParser.parseDate("tomorrow");
        Optional<LocalDate> today = dateParser.parseDate("today");
        Optional<LocalDate> yesterday = dateParser.parseDate("yesterday");

        assertTrue(tomorrow.isPresent());
        assertTrue(today.isPresent());
        assertTrue(yesterday.isPresent());

        assertEquals(LocalDate.now().minusDays(1), yesterday.get());
        assertEquals(LocalDate.now(), today.get());
        assertEquals(LocalDate.now().plusDays(1), tomorrow.get());
    }

    @Test
    void willReturnCorrectDataFormatWithString() {
        Optional<LocalDate> oneDay = dateParser.parseDate("1 day ago");
        Optional<LocalDate> lotOfDay = dateParser.parseDate("2234 days ago");

        assertTrue(oneDay.isPresent());
        assertTrue(lotOfDay.isPresent());

        assertEquals(LocalDate.now().minusDays(1), oneDay.get());
        assertEquals(LocalDate.now().minusDays(2234), lotOfDay.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-12-2020", "day before yesterday", "1 day ahead", "15.09.1996"})
    void willReturnEmptyOptionIfTheFormatIsNotSuitable(String data) {
        Optional<LocalDate> actual = dateParser.parseDate(data);
        assertTrue(actual.isEmpty());
    }
}
