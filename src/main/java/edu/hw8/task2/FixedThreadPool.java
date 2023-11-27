package edu.hw8.task2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = false;
    private final int size;

    private FixedThreadPool(int size) {
        this.size = size;
    }

    public static FixedThreadPool create(int size) {
        return new FixedThreadPool(size);
    }

    @Override
    public void start() {
        isRunning = true;
        for (int i = 0; i < size; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            workQueue.offer(runnable);
        } else {
            LOGGER.warn("Thread pool not running");
        }
    }

    @Override
    public void close() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
