package edu.hw2.task3;

public class FaultyConnectionManager implements ConnectionManager {
    private final Coin coin;

    public FaultyConnectionManager(Coin coin) {
        this.coin = coin;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(coin);
    }
}
