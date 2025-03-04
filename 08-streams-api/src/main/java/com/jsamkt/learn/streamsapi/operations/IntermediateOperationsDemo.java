package com.jsamkt.learn.streamsapi.operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates intermediate operations in the Streams API.
 * Intermediate operations return a new stream and are lazy.
 * They are not executed until a terminal operation is invoked.
 */
public class IntermediateOperationsDemo {
    
    public static void demo() {
        System.out.println("\n----- INTERMEDIATE OPERATIONS -----");
        
        List<String> words = Arrays.asList(
                "apple", "banana", "cherry", "date", "elderberry", 
                "fig", "grape", "honeydew", "kiwi", "lemon");
        
        System.out.println("Original list: " + words);
        
        // filter demo
        System.out.println("\n# filter operation");
        System.out.println("Words that start with 'a' to 'f':");
        words.stream()
                .filter(word -> word.charAt(0) >= 'a' && word.charAt(0) <= 'f')
                .forEach(word -> System.out.print(word + " "));
        System.out.println();
        
        // map demo
        System.out.println("\n# map operation");
        System.out.println("Words converted to uppercase:");
        words.stream()
                .map(String::toUpperCase)
                .forEach(word -> System.out.print(word + " "));
        System.out.println();
        
        // flatMap demo
        System.out.println("\n# flatMap operation");
        System.out.println("Displaying individual characters from all words:");
        words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> String.valueOf((char) c)))
                .limit(20) // Limit the output for demonstration
                .forEach(c -> System.out.print(c + " "));
        System.out.println();
        
        // sorted demo
        System.out.println("\n# sorted operation");
        System.out.println("Words sorted by length (shortest to longest):");
        words.stream()
                .sorted(Comparator.comparing(String::length))
                .forEach(word -> System.out.print(word + " "));
        System.out.println();
        
        // distinct demo
        System.out.println("\n# distinct operation");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 1, 4, 5, 4, 6);
        System.out.println("Original numbers: " + numbers);
        System.out.println("Distinct numbers:");
        numbers.stream()
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // peek demo
        System.out.println("\n# peek operation");
        System.out.println("Using peek to print each word before filtering:");
        words.stream()
                .peek(word -> System.out.print("Peek: " + word + " -> "))
                .filter(word -> word.length() > 5)
                .forEach(word -> System.out.println("Filtered: " + word));
        
        // limit and skip demo
        System.out.println("\n# limit and skip operations");
        System.out.println("First 3 words:");
        words.stream()
                .limit(3)
                .forEach(word -> System.out.print(word + " "));
        System.out.println();
        
        System.out.println("Skipping first 7 words:");
        words.stream()
                .skip(7)
                .forEach(word -> System.out.print(word + " "));
        System.out.println();
        
        System.out.println("----- END OF INTERMEDIATE OPERATIONS -----\n");
    }
}