package com.jsamkt.learn.concurrencyissues.starvation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairResource {
    private final Map<String, Integer> accessCount = new HashMap<>();
    private final Lock fairLock = new ReentrantLock(true); // true means fair lock

    public void useResource(String user) {
        fairLock.lock();
        try {
            // Update access count
            accessCount.put(user, accessCount.getOrDefault(user, 0) + 1);

            // Simulate using the resource
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } finally {
            fairLock.unlock();
        }
    }

    public void printStats() {
        System.out.println("Fair resource access statistics:");
        for (Map.Entry<String, Integer> entry : accessCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }
    }
}
