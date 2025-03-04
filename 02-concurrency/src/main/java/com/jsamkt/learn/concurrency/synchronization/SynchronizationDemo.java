package com.jsamkt.learn.concurrency.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizationDemo {

    private static int unsafeCounter = 0;
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);
    private static int lockProtectedCounter = 0;

    public static void demo()  {
        try {
            demonstrateSynchronization();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateSynchronization() throws InterruptedException {
        System.out.println("\n--- Thread Synchronization ---");

        // Reset counters
        unsafeCounter = 0;
        atomicCounter.set(0);
        lockProtectedCounter = 0;

        // Create threads that increment the unsafe counter
        Thread[] unsafeThreads = new Thread[5];
        for (int i = 0; i < unsafeThreads.length; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeCounter++; // Not thread-safe
                }
            });
            unsafeThreads[i].start();
        }

        // Create threads that increment the counter using synchronized method
        Thread[] synchronizedThreads = new Thread[5];
        for (int i = 0; i < synchronizedThreads.length; i++) {
            synchronizedThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementCounterSynchronized();
                }
            });
            synchronizedThreads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : unsafeThreads) {
            thread.join();
        }
        for (Thread thread : synchronizedThreads) {
            thread.join();
        }

        // Check the final values
        System.out.println("Unsafe counter: " + unsafeCounter + " (Expected: 5000)");
        System.out.println("Synchronized counter: " + lockProtectedCounter + " (Expected: 5000)");
    }

    private static synchronized void incrementCounterSynchronized() {
        lockProtectedCounter++;
    }

}
