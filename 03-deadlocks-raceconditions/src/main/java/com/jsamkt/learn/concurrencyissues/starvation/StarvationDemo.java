package com.jsamkt.learn.concurrencyissues.starvation;

import java.util.ArrayList;
import java.util.List;

public class StarvationDemo {

    public static void demo() {
        try {
            demonstrateStarvation();
            demonstrateStarvationSolution();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateStarvation() throws InterruptedException {
        System.out.println("\n--- Starvation ---");

        // Create a shared resource
        final SharedResource resource = new SharedResource();

        // Create a greedy thread that hogs the resource
        Thread greedyThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                resource.useResource("Greedy");
            }
        });

        // Create multiple starving threads that get little CPU time
        List<Thread> starvingThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int id = i;
            Thread starvingThread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    resource.useResource("Starving-" + id);
                }
            });
            starvingThread.setPriority(Thread.MIN_PRIORITY); // Lower priority
            starvingThreads.add(starvingThread);
        }

        // Set greedy thread to max priority
        greedyThread.setPriority(Thread.MAX_PRIORITY);

        // Start all threads
        for (Thread thread : starvingThreads) {
            thread.start();
        }
        greedyThread.start();

        // Wait for all threads to finish
        greedyThread.join();
        for (Thread thread : starvingThreads) {
            thread.join();
        }

        // Print statistics
        resource.printStats();

        System.out.println("\nStarvation Prevention Strategies:");
        System.out.println("1. Use fair locks (ReentrantLock(true))");
        System.out.println("2. Implement resource time slicing");
        System.out.println("3. Implement priority aging (gradually increase starved threads' priority)");
        System.out.println("4. Use higher-level constructs with fairness policies");
    }

    private static void demonstrateStarvationSolution() throws InterruptedException {
        System.out.println("\n4. Solution to Starvation: Fair Locks");

        // Create a shared resource with a fair lock
        final FairResource resource = new FairResource();

        // Create a greedy thread
        Thread greedyThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                resource.useResource("Greedy");
            }
        });

        // Create multiple normal threads
        List<Thread> normalThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final int id = i;
            Thread normalThread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    resource.useResource("Normal-" + id);
                }
            });
            normalThreads.add(normalThread);
        }

        // Start all threads (startving threads first to demonstrate fairness)
        for (Thread thread : normalThreads) {
            thread.start();
        }
        greedyThread.start();

        // Wait for all threads to finish
        greedyThread.join();
        for (Thread thread : normalThreads) {
            thread.join();
        }

        // Print statistics
        resource.printStats();
    }

}
