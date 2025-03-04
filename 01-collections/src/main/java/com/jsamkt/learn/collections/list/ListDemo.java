package com.jsamkt.learn.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {
    public static void demo(){
        demonstrateListTypes();
    }

    private static void demonstrateListTypes() {
        System.out.println("\n--- List Implementations ---");

        // ArrayList - Fast access by index, slow for insertions/deletions in the middle
        List<String> arrayList = new ArrayList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            arrayList.add("Item " + i);
        }
        System.out.printf("ArrayList add time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        // LinkedList - Fast insertions/deletions, slow for random access
        List<String> linkedList = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            linkedList.add("Item " + i);
        }
        System.out.printf("LinkedList add time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        // Random access performance
        startTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            arrayList.get(i * 10);
        }
        System.out.printf("ArrayList random access time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            linkedList.get(i * 10);
        }
        System.out.printf("LinkedList random access time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        // Insertion in the middle
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(arrayList.size() / 2, "New Item " + i);
        }
        System.out.printf("ArrayList middle insertion time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.add(linkedList.size() / 2, "New Item " + i);
        }
        System.out.printf("LinkedList middle insertion time: %.2f ms%n", (System.nanoTime() - startTime) / 1_000_000.0);

        // Thread-safe list
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        List<String> concurrentList = new CopyOnWriteArrayList<>();
        System.out.println("Thread-safe list options: Collections.synchronizedList and CopyOnWriteArrayList");
    }
}
