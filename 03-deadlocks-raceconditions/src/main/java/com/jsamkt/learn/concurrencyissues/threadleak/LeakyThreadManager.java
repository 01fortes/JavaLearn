package com.jsamkt.learn.concurrencyissues.threadleak;

public class LeakyThreadManager {
    public void startTask() {
        // This creates a thread but doesn't keep track of it or clean it up
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Simulate work
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
        System.out.println("Started task, but no way to clean it up later!");
    }
}
