package com.jsamkt.learn.functional.purefunction;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class PureFunctionDemo {

    public static void demo() {
        demonstratePureFunctions();
    }

    private static void demonstratePureFunctions() {
        System.out.println("\n--- Pure Functions and Side Effects ---");

        // Pure function example
        System.out.println("Pure function (add):");
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("3 + 4 = " + add.apply(3, 4));
        System.out.println("3 + 4 still = " + add.apply(3, 4)); // Always returns the same result for same inputs

        // Impure function with side effects
        System.out.println("\nImpure function (modifies external state):");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println("Before: " + numbers);

        // This is impure because it modifies external state
        Consumer<List<Integer>> addToList = list -> list.add(4);
        addToList.accept(numbers);
        System.out.println("After: " + numbers); // Side effect: list is modified

        // Converting impure function to pure
        System.out.println("\nConverting to pure function:");

        // Pure version that doesn't modify the input
        Function<List<Integer>, List<Integer>> pureAddToList = list -> {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(4);
            return newList;
        };

        List<Integer> newNumbers = pureAddToList.apply(numbers);
        System.out.println("Original: " + numbers); // Unchanged
        System.out.println("New list: " + newNumbers); // New list with the added element

        // Benefits of pure functions
        System.out.println("\nBenefits of pure functions:");
        System.out.println("1. Easier to test (same input always produces same output)");
        System.out.println("2. Can be memoized (cached) for performance");
        System.out.println("3. Can be parallelized safely");
        System.out.println("4. Order of execution doesn't matter");

        // Example of memoization
        System.out.println("\nMemoization example with Fibonacci:");

        // Slow recursive implementation
        Instant start = Instant.now();
        long fibResult = slowFibonacci(40);
        Instant end = Instant.now();
        System.out.println("Slow fibonacci(40) = " + fibResult);
        System.out.println("Time taken: " + Duration.between(start, end).toMillis() + "ms");

        // Memoized implementation
        Map<Integer, Long> cache = new HashMap<>();
        Function<Integer, Long> memoizedFib = n -> memoizedFibonacci(n, cache);

        start = Instant.now();
        fibResult = memoizedFib.apply(40);
        end = Instant.now();
        System.out.println("Memoized fibonacci(40) = " + fibResult);
        System.out.println("Time taken: " + Duration.between(start, end).toMillis() + "ms");
    }

    // Slow recursive Fibonacci implementation
    private static long slowFibonacci(int n) {
        if (n <= 1) return n;
        return slowFibonacci(n - 1) + slowFibonacci(n - 2);
    }

    // Memoized Fibonacci implementation
    private static long memoizedFibonacci(int n, Map<Integer, Long> cache) {
        if (n <= 1) return n;

        return cache.computeIfAbsent(n, k ->
                memoizedFibonacci(k - 1, cache) + memoizedFibonacci(k - 2, cache)
        );
    }
}
