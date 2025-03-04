package com.jsamkt.learn.concurrencyissues.threadleak;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProperThreadManager {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void startTask() {
        executor.submit(() -> {
            try {
                System.out.println("Task running in " + Thread.currentThread().getName());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
