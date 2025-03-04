package com.jsamkt.learn.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {

    private static final AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void demo()  {
        try {
            demonstrateAtomicVariables();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateAtomicVariables() throws InterruptedException {
        System.out.println("\n--- Atomic Variables ---");

        // Reset counter
        atomicCounter.set(0);

        // Create threads that increment the atomic counter
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Atomic counter: " + atomicCounter.get() + " (Expected: 5000)");
    }
}
