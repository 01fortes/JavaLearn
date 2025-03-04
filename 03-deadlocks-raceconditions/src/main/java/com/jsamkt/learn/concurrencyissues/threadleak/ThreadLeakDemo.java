package com.jsamkt.learn.concurrencyissues.threadleak;

public class ThreadLeakDemo {

    public static void demo()  {
        try {
            demonstrateThreadLeak();
            demonstrateThreadLeakSolution();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateThreadLeak() throws InterruptedException {
        System.out.println("\n--- Thread Leak ---");

        // Create a class that spawns but doesn't properly manage threads
        LeakyThreadManager manager = new LeakyThreadManager();

        // Start some tasks (without properly cleaning up)
        System.out.println("Starting tasks without proper cleanup...");
        for (int i = 0; i < 5; i++) {
            manager.startTask();
        }

        // Wait for some time
        Thread.sleep(1000);

        System.out.println("Active threads: " + Thread.activeCount());

        System.out.println("\nThread Leak Prevention Strategies:");
        System.out.println("1. Always clean up threads (join or interrupt)");
        System.out.println("2. Use ExecutorService for thread management");
        System.out.println("3. Use daemon threads for background tasks");
        System.out.println("4. Implement proper shutdown hooks");
    }

    private static void demonstrateThreadLeakSolution() throws InterruptedException {
        System.out.println("\n5. Solution to Thread Leaks: ExecutorService");

        // Create a proper thread manager with ExecutorService
        ProperThreadManager manager = new ProperThreadManager();

        // Start some tasks
        System.out.println("Starting tasks with proper management...");
        for (int i = 0; i < 5; i++) {
            manager.startTask();
        }

        // Wait for some time
        Thread.sleep(500);

        // Properly shut down
        System.out.println("Shutting down executor...");
        manager.shutdown();

        System.out.println("All tasks properly managed!");
    }
}
