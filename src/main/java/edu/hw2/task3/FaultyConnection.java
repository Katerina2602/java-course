package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Coin coin;

    public FaultyConnection(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void execute(String command) {
        LOGGER.info("Executing command -> {}", command);
        if (coin.isEagle()) {
            LOGGER.info("Successful");
        } else {
            throw new ConnectionException("Failed to execute command");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Connection closed");
    }
}
