package com.jsamkt.learn.streamsapi.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Demonstrates parallel streams in the Java Streams API.
 * Parallel streams divide the work across multiple threads,
 * potentially improving performance for large data sets and
 * operations that can be executed concurrently.
 */
public class ParallelStreamsDemo {
    
    public static void demo() {
        System.out.println("\n----- PARALLEL STREAMS -----");
        
        // Creating parallel streams
        creatingParallelStreamsDemo();
        
        // Parallel vs sequential performance
        parallelPerformanceDemo();
        
        // Common parallel stream operations
        commonParallelOperationsDemo();
        
        // Pitfalls and considerations
        parallelStreamConsiderationsDemo();
        
        System.out.println("----- END OF PARALLEL STREAMS -----\n");
    }
    
    private static void creatingParallelStreamsDemo() {
        System.out.println("\n# Creating Parallel Streams");
        
        // From a collection using parallel()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("Sequential processing:");
        numbers.stream()
                .peek(n -> System.out.println("Processing " + n + " on thread: " + Thread.currentThread().getName()))
                .forEach(n -> {
                    // Simulate some work
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        
        System.out.println("\nParallel processing:");
        numbers.parallelStream()
                .peek(n -> System.out.println("Processing " + n + " on thread: " + Thread.currentThread().getName()))
                .forEach(n -> {
                    // Simulate some work
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        
        // From a regular stream using parallel()
        System.out.println("\nConverting sequential to parallel:");
        IntStream.range(1, 5)
                .parallel()
                .forEach(n -> System.out.println(n + " processed by thread: " + Thread.currentThread().getName()));
    }
    
    private static void parallelPerformanceDemo() {
        System.out.println("\n# Parallel vs Sequential Performance");
        
        int size = 10_000_000;
        
        // Sequential sum
        long start = System.currentTimeMillis();
        long sequentialSum = IntStream.range(0, size).sum();
        long sequentialTime = System.currentTimeMillis() - start;
        
        // Parallel sum
        start = System.currentTimeMillis();
        long parallelSum = IntStream.range(0, size).parallel().sum();
        long parallelTime = System.currentTimeMillis() - start;
        
        System.out.println("Sequential sum time: " + sequentialTime + "ms");
        System.out.println("Parallel sum time: " + parallelTime + "ms");
        System.out.println("Sequential sum: " + sequentialSum);
        System.out.println("Parallel sum: " + parallelSum);
        
        // CPU-intensive tasks benefit more from parallelism
        System.out.println("\nCPU-intensive task performance:");
        
        start = System.currentTimeMillis();
        double sequentialComputation = IntStream.range(0, 1_000_000)
                .mapToDouble(i -> computeExpensiveValue(i))
                .sum();
        long sequentialComputationTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        double parallelComputation = IntStream.range(0, 1_000_000)
                .parallel()
                .mapToDouble(i -> computeExpensiveValue(i))
                .sum();
        long parallelComputationTime = System.currentTimeMillis() - start;
        
        System.out.println("Sequential computation time: " + sequentialComputationTime + "ms");
        System.out.println("Parallel computation time: " + parallelComputationTime + "ms");
    }
    
    private static double computeExpensiveValue(int i) {
        // Simulate an expensive computation
        return Math.sin(i) * Math.cos(i) / (Math.tan(i) + 1);
    }
    
    private static void commonParallelOperationsDemo() {
        System.out.println("\n# Common Parallel Stream Operations");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // filter
        System.out.println("Parallel filter (even numbers):");
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // map
        System.out.println("\nParallel map (square each number):");
        numbers.parallelStream()
                .map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // reduce
        System.out.println("\nParallel reduce (sum of all numbers):");
        int sum = numbers.parallelStream()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
    }
    
    private static void parallelStreamConsiderationsDemo() {
        System.out.println("\n# Parallel Stream Considerations");
        
        // 1. Order of execution is not guaranteed
        System.out.println("Order is not guaranteed in parallel streams:");
        IntStream.range(1, 11)
                .parallel()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2. Use forEachOrdered to maintain order
        System.out.println("\nUsing forEachOrdered to maintain order:");
        IntStream.range(1, 11)
                .parallel()
                .forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
        
        // 3. Non-associative operations may produce incorrect results
        System.out.println("\nNon-associative operations may produce incorrect results:");
        
        // Sequential subtraction (not associative)
        int sequentialResult = IntStream.range(1, 11)
                .reduce(0, (a, b) -> a - b);
        System.out.println("Sequential subtraction result: " + sequentialResult);
        
        // Parallel subtraction (incorrect result due to non-associativity)
        int parallelResult = IntStream.range(1, 11)
                .parallel()
                .reduce(0, (a, b) -> a - b);
        System.out.println("Parallel subtraction result: " + parallelResult + " (incorrect due to non-associativity)");
        
        // 4. Custom thread pool info
        System.out.println("\nDefault parallelism level: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
    }
}