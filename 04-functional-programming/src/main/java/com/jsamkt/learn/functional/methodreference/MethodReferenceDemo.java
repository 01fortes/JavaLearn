package com.jsamkt.learn.functional.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceDemo {

    public static void demo() {
        demonstrateMethodReferences();
    }

    private static void demonstrateMethodReferences() {
        System.out.println("\n--- Method References ---");

        // Static method reference
        System.out.println("Static method reference:");
        Function<Integer, String> intToString = String::valueOf;
        System.out.println(intToString.apply(42));

        // Instance method reference of a particular object
        System.out.println("\nInstance method reference (particular object):");
        String prefix = "User: ";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println(addPrefix.apply("John"));

        // Instance method reference of an arbitrary object of a particular type
        System.out.println("\nInstance method reference (arbitrary object):");
        Function<String, Integer> stringLength = String::length;
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));

        // Constructor reference
        System.out.println("\nConstructor reference:");
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("Item");
        System.out.println("List created with constructor reference: " + newList);

        // Method reference with stream operations
        System.out.println("\nMethod references with streams:");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Using lambda
        names.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // Using method references (more concise)
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::print);
        System.out.println();
    }
}
