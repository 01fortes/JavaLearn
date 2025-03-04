package com.jsamkt.learn.collections.set;

import java.util.*;

public class SetDemo {

    public static void demo() {
        demonstrateSetTypes();
    }

    private static void demonstrateSetTypes() {
        System.out.println("\n--- Set Implementations ---");

        // HashSet - Fast, unordered
        Set<String> hashSet = new HashSet<>();
        hashSet.add("banana");
        hashSet.add("apple");
        hashSet.add("cherry");
        hashSet.add("apple"); // Duplicate, will be ignored
        System.out.println("HashSet (unordered, no duplicates): " + hashSet);

        // LinkedHashSet - Maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("banana");
        linkedHashSet.add("apple");
        linkedHashSet.add("cherry");
        System.out.println("LinkedHashSet (maintains insertion order): " + linkedHashSet);

        // TreeSet - Sorted
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("banana");
        treeSet.add("apple");
        treeSet.add("cherry");
        System.out.println("TreeSet (sorted): " + treeSet);

        // EnumSet - Specialized for enums
        enum Size { SMALL, MEDIUM, LARGE, XLARGE }
        Set<Size> enumSet = EnumSet.of(Size.SMALL, Size.LARGE);
        System.out.println("EnumSet (specialized for enums): " + enumSet);

        // Set operations
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);
    }
}
