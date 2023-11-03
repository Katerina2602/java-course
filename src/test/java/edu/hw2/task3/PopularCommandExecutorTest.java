package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PopularCommandExecutorTest {
    private final static int MAX_ATTEMPTS = 3;
    private final Coin coin = mock(Coin.class);
    private final ConnectionManager connectionManager = mock(ConnectionManager.class);
    private final PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPTS);

    @Test
    void willSuccessExecuteCommandWhenStableConnection() {
        when(connectionManager.getConnection()).thenReturn(new StableConnection());

        assertDoesNotThrow(executor::updatePackages);
        verify(connectionManager, times(1)).getConnection();
    }

    @Test
    void willFailedExecuteCommandWhenFaultyConnectionAlwaysThrowsException() {
        when(coin.isEagle()).thenReturn(false);
        when(connectionManager.getConnection()).thenReturn(new FaultyConnection(coin));

        ConnectionException exception = assertThrows(ConnectionException.class, executor::updatePackages);
        assertEquals("Execution command failed, max attempts reached", exception.getMessage());
        assertEquals("Failed to execute command", exception.getCause().getMessage());
        verify(connectionManager, times(3)).getConnection();
    }

    @Test
    void willFailedExecuteCommandWhenFaultyConnectionOnceThrowsException() {
        when(coin.isEagle())
            .thenReturn(false)
            .thenReturn(true);
        when(connectionManager.getConnection()).thenReturn(new FaultyConnection(coin));

        assertDoesNotThrow(executor::updatePackages);
        verify(connectionManager, times(2)).getConnection();
    }
}
