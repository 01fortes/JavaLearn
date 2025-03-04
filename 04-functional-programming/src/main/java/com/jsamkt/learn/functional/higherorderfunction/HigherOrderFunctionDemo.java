package com.jsamkt.learn.functional.higherorderfunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HigherOrderFunctionDemo {

    public static void demo() {
        demonstrateHigherOrderFunctions();
    }

    private static void demonstrateHigherOrderFunctions() {
        System.out.println("\n--- Higher-Order Functions ---");

        // Higher-order function that takes a function as an argument
        System.out.println("Function as an argument:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Apply a function to each element in a list
        List<Integer> doubled = applyToEach(numbers, n -> n * 2);
        System.out.println("Doubled: " + doubled);

        List<Integer> squared = applyToEach(numbers, n -> n * n);
        System.out.println("Squared: " + squared);

        // Higher-order function that returns a function
        System.out.println("\nFunction as a return value:");
        Function<Integer, Integer> multiplyBy3 = createMultiplier(3);
        Function<Integer, Integer> multiplyBy5 = createMultiplier(5);

        System.out.println("multiplyBy3(4) = " + multiplyBy3.apply(4));
        System.out.println("multiplyBy5(4) = " + multiplyBy5.apply(4));

        // Creating a reusable decorator
        System.out.println("\nFunction decorator:");
        Function<Integer, Integer> addOne = n -> n + 1;
        Function<Integer, Integer> loggedAddOne = withLogging(addOne, "addOne");

        System.out.println(loggedAddOne.apply(5));

        // Currying example
        System.out.println("\nCurrying (transforming a function with multiple arguments into a sequence of functions):");
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

        // Using the curried function
        Function<Integer, Integer> add5 = curriedAdd.apply(5);
        System.out.println("add5(3) = " + add5.apply(3));
        System.out.println("curriedAdd(5)(3) = " + curriedAdd.apply(5).apply(3));

        // Partial application
        System.out.println("\nPartial application:");
        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers = (a, b, c) -> a + b + c;
        BiFunction<Integer, Integer, Integer> add10AndSomething = (b, c) -> addThreeNumbers.apply(10, b, c);

        System.out.println("add10AndSomething(5, 7) = " + add10AndSomething.apply(5, 7));
    }

    // Higher-order function that takes a function as an argument
    private static <T, R> List<R> applyToEach(List<T> list, Function<T, R> fn) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(fn.apply(item));
        }
        return result;
    }

    // Higher-order function that returns a function
    private static Function<Integer, Integer> createMultiplier(int factor) {
        return n -> n * factor;
    }

    // Function decorator example
    private static <T, R> Function<T, R> withLogging(Function<T, R> fn, String name) {
        return input -> {
            System.out.println("Calling " + name + " with input: " + input);
            R result = fn.apply(input);
            System.out.println("Result of " + name + ": " + result);
            return result;
        };
    }
}
