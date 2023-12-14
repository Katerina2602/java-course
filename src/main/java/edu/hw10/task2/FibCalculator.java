package edu.hw10.task2;

import edu.hw10.task2.annotation.Cache;

public interface FibCalculator {
    @Cache
    long fibWithInMemoryCache(int number);

    @Cache(persist = true)
    long fibWithPersistCache(int number);
}
