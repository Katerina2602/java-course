package edu.hw5;

import edu.hw5.task1.AnalyticsForComputerClub;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyticsForComputerClubTest {
    @Test
    void willReturnCorrectAverageTime (){
        ArrayList<String> list= new ArrayList<>();
        list.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        list.add("2022-03-12, 21:30 - 2022-03-13, 01:20");

        AnalyticsForComputerClub averageTime= new AnalyticsForComputerClub();
        String result="3ч 40м";

        assertEquals(result,averageTime.gatAverageTime(list));
    }
    @Test
    void willCorrectZeroIfListEmpty (){
        ArrayList<String> list= new ArrayList<>();
        AnalyticsForComputerClub averageTime= new AnalyticsForComputerClub();

        String result="0ч 0м";
        assertEquals(result,averageTime.gatAverageTime(list));
    }

}
