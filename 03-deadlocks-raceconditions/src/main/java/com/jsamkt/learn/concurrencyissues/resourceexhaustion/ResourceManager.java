package com.jsamkt.learn.concurrencyissues.resourceexhaustion;

import java.util.concurrent.*;

public class ResourceManager {
    private final ExecutorService executor;
    private final Semaphore semaphore;

    public ResourceManager() {
        // Create a bounded thread pool
        this.executor = new ThreadPoolExecutor(
                2, // Core pool size
                4, // Max pool size
                60, TimeUnit.SECONDS, // Thread keep-alive time
                new ArrayBlockingQueue<>(4), // Bounded queue
                new ThreadPoolExecutor.CallerRunsPolicy() // Run in caller's thread if rejected
        );

        // Also use a semaphore to limit concurrent tasks
        this.semaphore = new Semaphore(5);
    }

    public <T> Future<T> submitTask(Callable<T> task) throws InterruptedException {
        // Try to acquire a permit from the semaphore
        if (!semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
            throw new RejectedExecutionException("Too many concurrent tasks");
        }

        // Wrap the task to release the semaphore when done
        return executor.submit(() -> {
            try {
                return task.call();
            } finally {
                semaphore.release();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
