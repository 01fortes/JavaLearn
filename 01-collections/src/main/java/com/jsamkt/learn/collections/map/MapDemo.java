package com.jsamkt.learn.collections.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {

    public static void demo() {
        demonstrateMapTypes();
    }

    private static void demonstrateMapTypes() {
        System.out.println("\n--- Map Implementations ---");

        // HashMap - General purpose, fast
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        System.out.println("HashMap: " + hashMap);

        // LinkedHashMap - Maintains insertion order
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("three", 3);
        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
        System.out.println("LinkedHashMap (maintains insertion order): " + linkedHashMap);

        // TreeMap - Sorted by keys
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("banana", 2);
        treeMap.put("apple", 1);
        treeMap.put("cherry", 3);
        System.out.println("TreeMap (sorted by keys): " + treeMap);

        // EnumMap - Specialized for enum keys
        enum Days {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY}
        Map<Days, String> enumMap = new EnumMap<>(Days.class);
        enumMap.put(Days.MONDAY, "Start of week");
        enumMap.put(Days.FRIDAY, "End of week");
        System.out.println("EnumMap (specialized for enum keys): " + enumMap);

        // ConcurrentHashMap - Thread-safe
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("concurrent", 1);
        concurrentMap.put("thread-safe", 2);
        System.out.println("ConcurrentHashMap (thread-safe): " + concurrentMap);

        // IdentityHashMap - Uses reference equality (==) instead of .equals()
        Map<String, Integer> identityMap = new IdentityHashMap<>();
        String a = new String("key");
        String b = new String("key");
        identityMap.put(a, 1);
        identityMap.put(b, 2);
        System.out.println("IdentityHashMap size (uses == instead of .equals()): " + identityMap.size());
    }
}
