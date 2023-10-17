package edu.hw2.task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FaultyConnectionManagerTest {
    private final Coin coin = mock(Coin.class);
    private final ConnectionManager connectionManager = new FaultyConnectionManager(coin);

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void willAlwaysReturnFaultyConnection(boolean isEagle) {
        when(coin.isEagle()).thenReturn(isEagle);

        Connection connection = connectionManager.getConnection();

        assertTrue(connection instanceof FaultyConnection);
    }

}
