package edu.hw5;

import edu.hw5.task5.CarNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarNumberTest {
    @Test
    void test(){
        assertTrue(CarNumber.checksCarNumber("А123ВЕ777"));
        assertTrue(CarNumber.checksCarNumber("О777ОО177"));
        assertFalse(CarNumber.checksCarNumber("123АВЕ777"));
        assertFalse(CarNumber.checksCarNumber("А123ВГ77"));
        assertFalse(CarNumber.checksCarNumber("А123ВЕ7777"));
    }

}
