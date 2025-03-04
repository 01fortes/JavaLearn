package com.jsamkt.learn.concurrencyissues.resourceexhaustion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ResourceHog {
    private final ExecutorService executor;

    public ResourceHog() {
        // Create a thread pool with a small number of threads
        this.executor = new ThreadPoolExecutor(
                2, // Core pool size
                2, // Max pool size
                60, TimeUnit.SECONDS, // Keep alive time for excess threads
                new ArrayBlockingQueue<>(1), // Very small queue - will reject quickly
                new ThreadPoolExecutor.AbortPolicy() // Reject policy
        );
    }

    public void startResourceIntensiveTasks(int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started");
                try {
                    // Simulate intensive work
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Task " + taskId + " result";
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
