package edu.hw5;

import edu.hw5.task2.FridayTheThirteenth;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FridayTheThirteenthTest {
    @Test
    void willReturnNextFridayThirteenth() {
        FridayTheThirteenth fridayThirteenth = new FridayTheThirteenth();
        LocalDate nextFriday = LocalDate.parse("1925-02-13");
        assertEquals(nextFriday,fridayThirteenth.getNextFridayTheThirteenth("1925-01-01"));
    }
    @Test
    void willReturnListAllFridayThirteenth() throws ParseException {
        FridayTheThirteenth fridayThirteenth = new FridayTheThirteenth();
        ArrayList<String> fridayDates = new ArrayList<>();
        fridayDates.add("1925-02-13");
        fridayDates.add("1925-03-13");
        fridayDates.add("1925-11-13");
        assertEquals(fridayDates,fridayThirteenth.getAllFridaysThirteenthInYear(1925));
    }

    @Test
    void willReturnError() {
        FridayTheThirteenth fridayThirteenth = new FridayTheThirteenth();
        assertThrows(DateTimeParseException.class, () -> fridayThirteenth.getNextFridayTheThirteenth(""));
    }
}

