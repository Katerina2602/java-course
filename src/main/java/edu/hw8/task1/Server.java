package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final List<String> QUOTES = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    );
    private final ExecutorService executorService;
    private final int port;
    private final int maxConnections;
    private boolean isRun = true;

    public Server(int port, int maxConnections) {
        this.port = port;
        this.maxConnections = maxConnections;
        this.executorService = Executors.newFixedThreadPool(maxConnections + 1);
    }

    public void start() {
        executorService.submit(() -> {
            try (ServerSocket server = new ServerSocket(port, maxConnections)) {
                LOGGER.info("Server started on port {}", port);

                while (isRun) {
                    Socket clientSocket = server.accept();
                    executorService.submit(() -> handleSocket(clientSocket));
                }
            } catch (Exception ex) {
                LOGGER.error("Server crashed", ex);
            }
        });
    }

    public void stop() {
        isRun = false;
        executorService.shutdown();
    }

    private void handleSocket(Socket socket) {
        try (
            socket;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String request = in.readLine();
            LOGGER.info("Got request from client: {}", request);
            String quote = findQuoteByWord(request);
            if (quote != null) {
                out.write(quote + "\n");
            }
            out.flush();
            LOGGER.info("Server sent response to client: {}", quote);
        } catch (Exception ex) {
            LOGGER.error("Got error in executor", ex);
        }
    }

    private String findQuoteByWord(String word) {
        return QUOTES.stream()
            .filter(quote -> quote.contains(word))
            .findFirst()
            .orElse(null);
    }
}
