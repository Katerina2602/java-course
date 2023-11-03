package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    public DefaultConnectionManager(Coin coin) {
        this.coin = coin;
    }

    private final Coin coin;

    @Override
    public Connection getConnection() {
        if (coin.isEagle()) {
            return new StableConnection();
        } else {
            return new FaultyConnection(coin);
        }
    }
}
