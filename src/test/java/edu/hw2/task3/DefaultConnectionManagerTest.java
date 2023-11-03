package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultConnectionManagerTest {
    private final Coin coin = mock(Coin.class);
    private final ConnectionManager connectionManager = new DefaultConnectionManager(coin);

    @Test
    void willReturnStableConnectionWhenCoinIsEagle() {
        when(coin.isEagle()).thenReturn(true);

        Connection connection = connectionManager.getConnection();

        assertTrue(connection instanceof StableConnection);
    }

    @Test
    void willReturnFaultyConnectionWhenCoinIsTail() {
        when(coin.isEagle()).thenReturn(false);

        Connection connection = connectionManager.getConnection();

        assertTrue(connection instanceof FaultyConnection);
    }
}
