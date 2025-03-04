package com.jsamkt.learn.concurrencyissues.resourceexhaustion;

import java.util.concurrent.RejectedExecutionException;

public class ResourceExhaustionDemo {

    public static void demo(){
        try {
            demonstrateResourceExhaustion();
            demonstrateResourceExhaustionSolution();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateResourceExhaustion() throws InterruptedException {
        System.out.println("\n--- Resource Exhaustion ---");

        // Create a class that uses excessive resources
        ResourceHog hog = new ResourceHog();

        // Run some tasks that use too many resources
        System.out.println("Starting resource-intensive tasks...");
        try {
            hog.startResourceIntensiveTasks(5);
        } catch (RejectedExecutionException e) {
            System.out.println("Task rejected: " + e.getMessage());
        }

        // Wait for some time
        Thread.sleep(1000);

        // Clean up
        hog.shutdown();

        System.out.println("\nResource Exhaustion Prevention Strategies:");
        System.out.println("1. Use bounded queues and thread pools");
        System.out.println("2. Implement backpressure mechanisms");
        System.out.println("3. Use semaphores to limit concurrent access");
        System.out.println("4. Implement circuit breakers and fallbacks");
        System.out.println("5. Monitor and alert on resource usage");
    }

    private static void demonstrateResourceExhaustionSolution() throws InterruptedException {
        System.out.println("\n6. Solution to Resource Exhaustion: Bounded Queues with Rejections");

        // Create a class that properly manages resources
        ResourceManager manager = new ResourceManager();

        // Run a bunch of tasks
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            try {
                manager.submitTask(() -> {
                    try {
                        System.out.println("Task " + taskId + " running");
                        Thread.sleep(100);
                        return "Result " + taskId;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "Interrupted";
                    }
                });
                System.out.println("Submitted task " + i);
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + i + " rejected: " + e.getMessage());
            }
        }

        // Wait for tasks to complete
        Thread.sleep(1000);

        // Shut down properly
        manager.shutdown();
    }
}
