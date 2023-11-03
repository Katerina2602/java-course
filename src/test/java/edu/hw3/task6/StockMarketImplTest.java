package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StockMarketImplTest {

    @Test
    void willReturnTheMostExpensiveShare() {
        Stock stock1 = new Stock(5479.0, "Gazprom");
        Stock stock2 = new Stock(4879.9, "Aplle");
        Stock stock3 = new Stock(8745.3, "Sberbank");
        Stock stock4 = new Stock(9745.3, "Tinkoff");
        StockMarket market = new StockMarketImpl();
        market.add(stock1);
        market.add(stock2);
        market.add(stock3);
        market.add(stock4);
        market.remove(stock4);
        assertEquals(stock3, market.mostValuableStock());

    }

}
