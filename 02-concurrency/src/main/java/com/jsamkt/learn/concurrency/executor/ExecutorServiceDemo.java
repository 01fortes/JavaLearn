package com.jsamkt.learn.concurrency.executor;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void demo(){
        try {
            demonstrateExecutorService();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateExecutorService() throws InterruptedException {
        System.out.println("\n--- ExecutorService ---");

        // Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Result of task " + taskId;
            });
        }

        // Shutdown executor and wait for tasks to complete
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("All executor tasks completed");

        // Using Callable to get a result
        ExecutorService executorWithResult = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorWithResult.submit(() -> {
            System.out.println("Calculating...");
            Thread.sleep(1000);
            return 42;
        });

        try {
            System.out.println("Future result: " + future.get());
        } catch (ExecutionException e) {
            System.out.println("Computation threw an exception: " + e.getCause());
        }

        executorWithResult.shutdown();
    }
}
