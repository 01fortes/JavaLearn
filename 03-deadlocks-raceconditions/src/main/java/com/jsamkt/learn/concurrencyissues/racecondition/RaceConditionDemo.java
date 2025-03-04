package com.jsamkt.learn.concurrencyissues.racecondition;

import com.jsamkt.learn.concurrencyissues.livelock.ImprovedSpoon;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionDemo {
    private static int unsafeCounter = 0;
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);
    private static final int ITERATIONS = 100000;

    public static void demo()  {
        try {
            demonstrateAtomicSolution();
            demonstrateAtomicSolution();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateRaceCondition() throws InterruptedException {
        System.out.println("\n--- Race Condition ---");

        // Reset counter
        unsafeCounter = 0;
        atomicCounter.set(0);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                unsafeCounter++; // UNSAFE - race condition!
                atomicCounter.incrementAndGet(); // Safe - uses atomic operation
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                unsafeCounter++; // UNSAFE - race condition!
                atomicCounter.incrementAndGet(); // Safe - uses atomic operation
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Unsafe counter: " + unsafeCounter + " (Expected: " + (ITERATIONS * 2) + ")");
        System.out.println("Atomic counter: " + atomicCounter.get() + " (Expected: " + (ITERATIONS * 2) + ")");
    }

    private static void demonstrateAtomicSolution() throws InterruptedException {
        System.out.println("\n1. Solution to Race Conditions: Atomic Variables");

        AtomicInteger counter = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final counter value: " + counter.get() + " (Expected: 2000)");
    }
}
