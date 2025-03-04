package com.jsamkt.learn.functional.functioncomposition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionCompositionDemo {

    public static void demo() {
        demonstrateFunctionComposition();
    }

    private static void demonstrateFunctionComposition() {
        System.out.println("\n--- Function Composition ---");

        // Function composition with andThen
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;
        Function<Integer, Integer> add3 = n -> n + 3;

        Function<Integer, Integer> multiplyBy2ThenAdd3 = multiplyBy2.andThen(add3);
        System.out.println("multiplyBy2.andThen(add3) with input 5: " + multiplyBy2ThenAdd3.apply(5));

        // Function composition with compose
        Function<Integer, Integer> add3ThenMultiplyBy2 = multiplyBy2.compose(add3);
        System.out.println("multiplyBy2.compose(add3) with input 5: " + add3ThenMultiplyBy2.apply(5));

        // Predicate composition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;

        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("isEven.and(isPositive) with input 4: " + isEvenAndPositive.test(4));
        System.out.println("isEven.and(isPositive) with input -2: " + isEvenAndPositive.test(-2));

        Predicate<Integer> isEvenOrPositive = isEven.or(isPositive);
        System.out.println("isEven.or(isPositive) with input -2: " + isEvenOrPositive.test(-2));
        System.out.println("isEven.or(isPositive) with input -3: " + isEvenOrPositive.test(-3));

        Predicate<Integer> isNotEven = isEven.negate();
        System.out.println("isEven.negate() with input 7: " + isNotEven.test(7));

        // Building a complex processing pipeline
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<String> result = numbers.stream()
                .filter(isEvenAndPositive)
                .map(multiplyBy2)
                .map(String::valueOf)
                .collect(Collectors.toList());

        System.out.println("Complex pipeline result: " + result);
    }
}
