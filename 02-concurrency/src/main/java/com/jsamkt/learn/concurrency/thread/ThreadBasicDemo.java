package com.jsamkt.learn.concurrency.thread;

public class ThreadBasicDemo {

    public static void demo(){
        try {
            demonstrateThreadBasics();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateThreadBasics() throws InterruptedException {
        System.out.println("\n--- Thread Basics ---");

        // Creating a thread with Runnable
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 running (Runnable)");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread 1 was interrupted");
            }
            System.out.println("Thread 1 finished");
        });

        // Creating a thread by extending Thread
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread 2 running (Thread subclass)");
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    System.out.println("Thread 2 was interrupted");
                }
                System.out.println("Thread 2 finished");
            }
        };

        System.out.println("Starting threads...");

        thread1.start();
        thread2.start();

        // Wait for threads to complete using join
        thread1.join();
        thread2.join();

        System.out.println("All basic threads completed");

        // Thread states and interrupts
        final Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("Sleeping thread going to sleep...");
                Thread.sleep(10000); // Sleep for 10 seconds
                System.out.println("Sleeping thread woke up naturally");
            } catch (InterruptedException e) {
                System.out.println("Sleeping thread was interrupted");
            }
        });

        sleepingThread.start();
        System.out.println("Sleeping thread state: " + sleepingThread.getState());
        Thread.sleep(1000);
        System.out.println("Interrupting sleeping thread...");
        sleepingThread.interrupt();
        sleepingThread.join();
    }
}
