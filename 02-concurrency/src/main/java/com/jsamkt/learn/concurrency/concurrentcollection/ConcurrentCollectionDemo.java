package com.jsamkt.learn.concurrency.concurrentcollection;

import java.util.concurrent.*;

public class ConcurrentCollectionDemo {

    public static void demo() {
        demonstrateConcurrentCollections();
    }

    private static void demonstrateConcurrentCollections() {
        System.out.println("\n--- Concurrent Collections ---");

        // ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            concurrentMap.put("key" + i, i);
        }

        // ConcurrentHashMap operations are thread-safe
        concurrentMap.forEach((k, v) -> System.out.println(k + ": " + v));

        // Atomic update operations
        concurrentMap.compute("key1", (k, v) -> v == null ? 1 : v + 10);
        System.out.println("After compute: " + concurrentMap.get("key1"));

        // CopyOnWriteArrayList - thread-safe list for scenarios with rare modifications
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        copyOnWriteList.add("Item 1");
        copyOnWriteList.add("Item 2");

        // ConcurrentLinkedQueue - non-blocking queue
        ConcurrentLinkedQueue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
        concurrentQueue.add("Task 1");
        concurrentQueue.add("Task 2");

        System.out.println("Queue poll: " + concurrentQueue.poll());
        System.out.println("Queue poll: " + concurrentQueue.poll());

        // BlockingQueue - blocks when empty or full
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(2); // Capacity of 2
        try {
            blockingQueue.put("Task A");
            blockingQueue.put("Task B");
            System.out.println("BlockingQueue size: " + blockingQueue.size());

            // This would block if we didn't retrieve an element first
            System.out.println("Took from BlockingQueue: " + blockingQueue.take());
            blockingQueue.put("Task C");

            System.out.println("BlockingQueue contents: " + blockingQueue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
