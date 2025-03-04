package com.jsamkt.learn.concurrency.parallelstream;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamDemo {

    public static void demo() {
        demonstrateParallelStreams();
    }

    private static void demonstrateParallelStreams() {
        System.out.println("\n--- Parallel Streams ---");

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            numbers.add(i);
        }

        // Sequential stream
        long startTimeSeq = System.nanoTime();
        long sumSeq = numbers.stream()
                .mapToLong(Integer::longValue)
                .filter(n -> n % 2 == 0)
                .sum();
        long endTimeSeq = System.nanoTime();

        System.out.printf("Sequential sum: %d, Time: %.2f ms%n",
                sumSeq, (endTimeSeq - startTimeSeq) / 1_000_000.0);

        // Parallel stream
        long startTimePar = System.nanoTime();
        long sumPar = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .filter(n -> n % 2 == 0)
                .sum();
        long endTimePar = System.nanoTime();

        System.out.printf("Parallel sum: %d, Time: %.2f ms%n",
                sumPar, (endTimePar - startTimePar) / 1_000_000.0);

        // Important note about non-thread-safe operations
        System.out.println("\nWarning: Be careful with stateful operations in parallel streams!");
        List<Integer> resultList = new ArrayList<>();

        // This is NOT thread-safe:
        numbers.stream()
                .limit(100)
                .parallel()
                .forEach(resultList::add);

        System.out.println("Size of result list with parallel unsafe operation: " + resultList.size());
        System.out.println("(Note: expected 100, but could be less due to race conditions)");

        // Thread-safe alternative:
        List<Integer> safeList = numbers.stream()
                .limit(100)
                .parallel()
                .toList();

        System.out.println("Size of result list with thread-safe operation: " + safeList.size());
    }
}
