package com.jsamkt.learn.functional.immutability;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImmutabilityDemo {

    public static void demo() {
        demonstrateImmutability();
    }

    private static void demonstrateImmutability() {
        System.out.println("\n--- Immutability and Persistent Data Structures ---");

        // Immutable objects
        System.out.println("Immutable objects:");
        String s1 = "Hello";
        String s2 = s1.concat(", World!"); // Creates a new string, doesn't modify s1

        System.out.println("s1: " + s1); // Still "Hello"
        System.out.println("s2: " + s2); // "Hello, World!"

        // Creating an immutable list
        System.out.println("\nImmutable collections:");
        List<String> immutableList = List.of("one", "two", "three");
        System.out.println("Immutable list: " + immutableList);

        try {
            immutableList.add("four"); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list");
        }

        // Working with immutable collections
        System.out.println("\nWorking with immutable data:");
        List<String> original = Arrays.asList("apple", "banana", "cherry");
        System.out.println("Original: " + original);

        // Adding an element (creating a new list)
        List<String> added = Stream.concat(original.stream(), Stream.of("date"))
                .collect(Collectors.toList());
        System.out.println("After adding 'date': " + added);

        // Removing an element (creating a new list)
        List<String> removed = original.stream()
                .filter(s -> !s.equals("banana"))
                .collect(Collectors.toList());
        System.out.println("After removing 'banana': " + removed);

        // Original list remains unchanged
        System.out.println("Original is still: " + original);

        // Benefits of immutability
        System.out.println("\nBenefits of immutability:");
        System.out.println("1. Thread safety without locks");
        System.out.println("2. Easy to reason about (no unexpected changes)");
        System.out.println("3. Better caching and performance optimizations");
        System.out.println("4. Enables structural sharing in persistent data structures");

        // Creating an immutable object
        System.out.println("\nDesigning immutable classes:");
        ImmutablePerson person = new ImmutablePerson("John", 30);
        System.out.println(person);

        // To 'modify', create a new instance
        ImmutablePerson olderPerson = person.withAge(31);
        System.out.println(person);      // Unchanged
        System.out.println(olderPerson); // New instance with updated age
    }
}
