package com.jsamkt.learn.concurrencyissues.starvation;

import java.util.HashMap;
import java.util.Map;

public class SharedResource {
    private final Map<String, Integer> accessCount = new HashMap<>();
    private final Object lockObject = new Object();

    public void useResource(String user) {
        synchronized (lockObject) {
            // Update access count
            accessCount.put(user, accessCount.getOrDefault(user, 0) + 1);

            // Simulate using the resource
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printStats() {
        System.out.println("Resource access statistics:");
        for (Map.Entry<String, Integer> entry : accessCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }
    }
}
