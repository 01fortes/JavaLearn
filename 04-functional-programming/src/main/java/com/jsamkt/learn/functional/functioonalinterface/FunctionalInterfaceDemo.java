package com.jsamkt.learn.functional.functioonalinterface;

import java.util.function.*;

public class FunctionalInterfaceDemo {

    public static void demo() {
        demonstrateFunctionalInterfaces();
    }

    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n--- Functional Interfaces and Lambda Expressions ---");

        // Using built-in functional interfaces

        // Predicate<T> - Takes a value and returns boolean
        System.out.println("Predicates:");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Function<T, R> - Takes a value and returns a result
        System.out.println("\nFunctions:");
        Function<Integer, String> intToString = n -> "Number: " + n;
        System.out.println(intToString.apply(42));

        // Consumer<T> - Takes a value and performs an action
        System.out.println("\nConsumers:");
        Consumer<String> printer = s -> System.out.println("Consuming: " + s);
        printer.accept("Hello, functional world!");

        // Supplier<T> - Takes no arguments and returns a value
        System.out.println("\nSuppliers:");
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Random value: " + randomSupplier.get());
        System.out.println("Another random value: " + randomSupplier.get());

        // UnaryOperator<T> - Takes a value and returns a value of the same type
        System.out.println("\nUnaryOperators:");
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("5 squared: " + square.apply(5));

        // BinaryOperator<T> - Takes two values and returns a value of the same type
        System.out.println("\nBinaryOperators:");
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("3 * 4 = " + multiply.apply(3, 4));

        // BiFunction<T, U, R> - Takes two values of potentially different types and returns a result
        System.out.println("\nBiFunctions:");
        BiFunction<Integer, String, String> repeat = (count, str) -> str.repeat(count);
        System.out.println(repeat.apply(3, "Java "));

        // Creating your own functional interface
        System.out.println("\nCustom Functional Interface:");
        TriFunction<Integer, Integer, Integer, Integer> add3Numbers = (a, b, c) -> a + b + c;
        System.out.println("1 + 2 + 3 = " + add3Numbers.apply(1, 2, 3));
    }
}
