package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterIncrease {
    private CounterIncrease() {

    }

    static AtomicInteger count = new AtomicInteger(0);

    public static void willIncrementTheCounter() throws InterruptedException {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread1.join();
        myThread2.join();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            count.incrementAndGet();
        }

    }

}
