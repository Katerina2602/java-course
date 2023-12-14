package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientServerTest {
    private static Server server;
    private static Client client;

    @BeforeAll
    static void setUp() throws IOException, InterruptedException {
        int port;
        String hostName;
        // чтобы найти свободный порт при сборке в githab
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
            hostName = socket.getInetAddress().getHostName();
        }
        server = new Server(port, 2);
        client = new Client(port, hostName);
        server.start();
        TimeUnit.SECONDS.sleep(2);
    }

    @AfterAll
    static void tearDown() {
        server.stop();
    }

    @Test
    void willReturnQuoteWhenMatchByWord() {
        String quote = client.getQuoteByWord("личности");

        assertEquals("Не переходи на личности там, где их нет", quote);
    }

    @Test
    void willReturnNullWhenNotMatchByWord() {
        String quote = client.getQuoteByWord("цитата которой нет");

        assertNull(quote);
    }

    @Test
    void willThrowsExceptionWhenFailedConnectToServer() throws UnknownHostException {
        Client testClient = new Client(1111, "unknown");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> testClient.getQuoteByWord("word"));
        assertEquals("Failed get quote from server", exception.getMessage());
    }
}
