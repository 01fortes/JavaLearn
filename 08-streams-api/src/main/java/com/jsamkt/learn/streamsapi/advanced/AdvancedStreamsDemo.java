package com.jsamkt.learn.streamsapi.advanced;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates advanced Stream API features and techniques.
 * This includes specialized streams, custom collectors, infinite streams,
 * and other advanced uses of the Streams API.
 */
public class AdvancedStreamsDemo {
    
    public static void demo() {
        System.out.println("\n----- ADVANCED STREAMS -----");
        
        // Primitive streams
        primitiveStreamsDemo();
        
        // Infinite streams
        infiniteStreamsDemo();
        
        // Stream operations with Optional
        optionalWithStreamsDemo();
        
        // Specialized stream operations
        specializedOperationsDemo();
        
        System.out.println("----- END OF ADVANCED STREAMS -----\n");
    }
    
    private static void primitiveStreamsDemo() {
        System.out.println("\n# Primitive Streams (IntStream, LongStream, DoubleStream)");
        
        // IntStream creation
        System.out.println("IntStream.range(1, 6):");
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("IntStream.rangeClosed(1, 5):");
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Specialized operations
        int[] numbers = {5, 2, 8, 1, 7, 3};
        System.out.println("\nStatistics on array " + Arrays.toString(numbers) + ":");
        
        // sum
        int sum = IntStream.of(numbers).sum();
        System.out.println("Sum: " + sum);
        
        // min
        OptionalInt min = IntStream.of(numbers).min();
        System.out.println("Min: " + min.orElse(0));
        
        // max
        OptionalInt max = IntStream.of(numbers).max();
        System.out.println("Max: " + max.orElse(0));
        
        // average
        double avg = IntStream.of(numbers).average().orElse(0);
        System.out.println("Average: " + avg);
        
        // Converting between streams
        System.out.println("\nConverting between stream types:");
        
        // Object stream to int stream
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        int totalLength = words.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println("Total length of " + words + ": " + totalLength);
        
        // IntStream to object stream
        System.out.println("\nIntegers boxed and collected:");
        IntStream.rangeClosed(1, 5)
                .boxed() // Convert from IntStream to Stream<Integer>
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
    
    private static void infiniteStreamsDemo() {
        System.out.println("\n# Infinite Streams");
        
        // Generate infinite stream and limit it
        System.out.println("Generate 5 random numbers:");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(n -> System.out.printf("%.4f ", n));
        System.out.println();
        
        // Iterate with a seed and function
        System.out.println("\nPowers of 2 (first 6):");
        Stream.iterate(1, n -> n * 2)
                .limit(6)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Iterate with predicate (Java 9+)
        System.out.println("\nFibonacci sequence until > 100:");
        Stream.iterate(
                new int[]{0, 1}, 
                fib -> fib[1] <= 100,
                fib -> new int[]{fib[1], fib[0] + fib[1]})
                .map(fib -> fib[0])
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
    
    private static void optionalWithStreamsDemo() {
        System.out.println("\n# Optional with Streams");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", null, "Dave");
        System.out.println("Names list: " + names);
        
        // Filter out nulls
        System.out.println("\nFiltering nulls with Optional:");
        names.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(name -> System.out.print(name + " "));
        System.out.println();
        
        // Providing default values with Optional
        System.out.println("\nProviding defaults with Optional:");
        names.stream()
                .map(name -> Optional.ofNullable(name).orElse("Unknown"))
                .forEach(name -> System.out.print(name + " "));
        System.out.println();
    }
    
    private static void specializedOperationsDemo() {
        System.out.println("\n# Specialized Stream Operations");
        
        // takeWhile and dropWhile (Java 9+)
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 9, 10, 12);
        System.out.println("Numbers: " + numbers);
        
        System.out.println("\ntakeWhile (take elements until a condition becomes false):");
        numbers.stream()
                .takeWhile(n -> n % 2 == 0) // Take while even
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("\ndropWhile (drop elements until a condition becomes false):");
        numbers.stream()
                .dropWhile(n -> n % 2 == 0) // Drop while even
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Stream.ofNullable (Java 9+)
        System.out.println("\nStream.ofNullable (creates a stream of 0 or 1 elements):");
        Stream.ofNullable("not null").forEach(System.out::println);
        Stream.ofNullable(null).forEach(System.out::println); // Prints nothing
        
        // Stream concatenation
        System.out.println("\nConcatenating streams:");
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of("x", "y", "z");
        Stream.concat(stream1, stream2)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
}