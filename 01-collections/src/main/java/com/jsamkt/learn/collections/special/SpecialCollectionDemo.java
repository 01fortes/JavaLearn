package com.jsamkt.learn.collections.special;

import java.util.*;

public class SpecialCollectionDemo {

    public static void demo() {
        demonstrateSpecialCollections();
    }

    private static void demonstrateSpecialCollections() {
        System.out.println("\n--- Specialized Collections ---");

        // PriorityQueue - Elements are ordered by priority
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(3);
        System.out.print("PriorityQueue removes elements in priority order: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
        System.out.println();

        // Deque - Double-ended queue
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("First");
        deque.addLast("Last");
        deque.addFirst("Very First");
        System.out.println("Deque (double-ended queue): " + deque);
        System.out.println("Deque.removeFirst(): " + deque.removeFirst());
        System.out.println("Deque.removeLast(): " + deque.removeLast());

        // BitSet - Compact bit array
        BitSet bitSet = new BitSet(16);
        bitSet.set(0);
        bitSet.set(4);
        bitSet.set(8);
        bitSet.set(15);
        System.out.println("BitSet: " + bitSet);
        System.out.println("BitSet cardinality (count of set bits): " + bitSet.cardinality());

        // WeakHashMap - Entries can be garbage collected
        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        Object key = new Object();
        weakMap.put(key, "Value");
        System.out.println("WeakHashMap before losing strong reference: " + !weakMap.isEmpty());
        key = null; // Now key can be garbage collected
        System.gc(); // Request garbage collection
        // After garbage collection, the entry might be removed
    }
}
