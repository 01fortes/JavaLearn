package com.jsamkt.learn.streamsapi.basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates basic Stream operations and creation methods.
 * Streams are sequences of elements supporting sequential and parallel operations.
 */
public class StreamBasicsDemo {
    
    public static void demo() {
        System.out.println("\n----- STREAM BASICS -----");
        
        createStreamsDemo();
        streamCharacteristicsDemo();
        
        System.out.println("----- END OF STREAM BASICS -----\n");
    }
    
    private static void createStreamsDemo() {
        System.out.println("\n# Creating Streams");
        
        // 1. Stream.of
        System.out.println("\nCreating a stream using Stream.of:");
        Stream<String> streamOf = Stream.of("apple", "banana", "cherry");
        streamOf.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
        
        // 2. Stream from array
        System.out.println("\nCreating a stream from an array:");
        String[] fruitArray = {"apple", "banana", "cherry"};
        Stream<String> streamFromArray = Arrays.stream(fruitArray);
        streamFromArray.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
        
        // 3. Stream from collection
        System.out.println("\nCreating a stream from a collection:");
        List<String> fruitList = Arrays.asList("apple", "banana", "cherry");
        Stream<String> streamFromCollection = fruitList.stream();
        streamFromCollection.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
        
        // 4. Stream.generate
        System.out.println("\nCreating a stream using Stream.generate (limited to 5 elements):");
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        randomStream.forEach(rand -> System.out.print(rand + " "));
        System.out.println();
        
        // 5. Stream.iterate
        System.out.println("\nCreating a stream using Stream.iterate (limited to 5 elements):");
        Stream<Integer> iteratedStream = Stream.iterate(0, n -> n + 2).limit(5);
        iteratedStream.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 6. IntStream, LongStream, DoubleStream
        System.out.println("\nCreating a primitive IntStream (1 to 5):");
        IntStream intStream = IntStream.rangeClosed(1, 5);
        intStream.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
    
    private static void streamCharacteristicsDemo() {
        System.out.println("\n# Stream Characteristics");
        
        // 1. Streams can't be reused
        System.out.println("\nStreams cannot be reused:");
        List<String> letters = Arrays.asList("a", "b", "c");
        Stream<String> stream = letters.stream();
        List<String> result = stream.collect(Collectors.toList());
        System.out.println("First operation: " + result);
        
        try {
            // This will throw an IllegalStateException
            stream.forEach(System.out::println);
            System.out.println("This line won't be reached");
        } catch (IllegalStateException e) {
            System.out.println("Error when trying to reuse stream: " + e.getMessage());
        }
        
        // 2. Intermediate vs. Terminal operations
        System.out.println("\nIntermediate operations are lazy, terminal operations are eager:");
        Stream<String> streamWithMap = Stream.of("apple", "banana", "cherry")
                .map(s -> {
                    System.out.println("Mapping: " + s);
                    return s.toUpperCase();
                });
        
        // No output yet since map is an intermediate operation (lazy)
        System.out.println("Map operation defined but not executed yet");
        
        // Terminal operation triggers the processing
        System.out.println("Executing terminal operation (forEach):");
        streamWithMap.forEach(s -> System.out.println("ForEach: " + s));
    }
}