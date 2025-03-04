package com.jsamkt.learn.concurrencyissues.livelock;

public class LiveLockDemo {


    public static void demo(){
        try {
            demonstrateLivelock();
            demonstrateLivelockSolution();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateLivelock() throws InterruptedException {
        System.out.println("\n--- Livelock ---");

        // Create a shared resource
        final Spoon spoon = new Spoon();

        // Create two hungry philosophers
        final Thread philosopher1 = new Thread(() -> {
            try {
                // Try to eat repeatedly
                for (int i = 0; i < 10; i++) {
                    spoon.use("Philosopher 1");
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        final Thread philosopher2 = new Thread(() -> {
            try {
                // Try to eat repeatedly
                for (int i = 0; i < 10; i++) {
                    spoon.use("Philosopher 2");
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start both philosophers
        philosopher1.start();
        philosopher2.start();

        // Wait a bit to observe the livelock
        Thread.sleep(1000);

        // Interrupt both threads to continue with the demo
        philosopher1.interrupt();
        philosopher2.interrupt();

        System.out.println("\nWhat happened:");
        System.out.println("Both philosophers kept being polite and offering the spoon to each other");
        System.out.println("but neither was able to use it (livelock)");

        System.out.println("\nLivelock Prevention Strategies:");
        System.out.println("1. Introduce randomness/backoff strategy");
        System.out.println("2. Use priorities to break symmetry");
        System.out.println("3. Use timeouts and retry mechanisms");
    }

    private static void demonstrateLivelockSolution() throws InterruptedException {
        System.out.println("\n3. Solution to Livelock: Random Backoff");

        // Create a shared resource with random backoff
        final ImprovedSpoon spoon = new ImprovedSpoon();

        // Create two philosophers
        Thread philosopher1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    spoon.use("Philosopher 1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread philosopher2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    spoon.use("Philosopher 2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        philosopher1.start();
        philosopher2.start();

        philosopher1.join();
        philosopher2.join();
    }

}
