package com.jsamkt.learn.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalProgrammingDemoTest {

    @Test
    void testFunctionalInterfaces() {
        // Test Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        assertTrue(isEven.test(4));
        assertFalse(isEven.test(7));
        
        // Test Function
        Function<Integer, String> intToString = n -> "Number: " + n;
        assertEquals("Number: 42", intToString.apply(42));
        
        // Test Consumer (checking side effects)
        List<String> consumed = new ArrayList<>();
        Consumer<String> collector = consumed::add;
        collector.accept("Hello");
        collector.accept("World");
        assertEquals(List.of("Hello", "World"), consumed);
        
        // Test Supplier
        Supplier<Integer> constantSupplier = () -> 42;
        assertEquals(Integer.valueOf(42), constantSupplier.get());
        
        // Test UnaryOperator
        UnaryOperator<Integer> square = n -> n * n;
        assertEquals(Integer.valueOf(25), square.apply(5));
        
        // Test BinaryOperator
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        assertEquals(Integer.valueOf(12), multiply.apply(3, 4));
    }
    
    @Test
    void testFunctionComposition() {
        // Setup functions
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;
        Function<Integer, Integer> add3 = n -> n + 3;
        
        // Test andThen
        Function<Integer, Integer> multiplyBy2ThenAdd3 = multiplyBy2.andThen(add3);
        assertEquals(Integer.valueOf(13), multiplyBy2ThenAdd3.apply(5));
        
        // Test compose
        Function<Integer, Integer> add3ThenMultiplyBy2 = multiplyBy2.compose(add3);
        assertEquals(Integer.valueOf(16), add3ThenMultiplyBy2.apply(5));
        
        // Test predicate composition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        
        // and
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        assertTrue(isEvenAndPositive.test(4));
        assertFalse(isEvenAndPositive.test(-2));
        
        // or
        Predicate<Integer> isEvenOrPositive = isEven.or(isPositive);
        assertTrue(isEvenOrPositive.test(-2));
        assertTrue(isEvenOrPositive.test(3));
        assertFalse(isEvenOrPositive.test(-3));
        
        // negate
        Predicate<Integer> isNotEven = isEven.negate();
        assertTrue(isNotEven.test(7));
        assertFalse(isNotEven.test(8));
    }
    
    @Test
    void testHigherOrderFunctions() {
        // Test function that takes a function as argument
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Create higher-order function
        Function<Function<Integer, Integer>, List<Integer>> applyToList = 
            fn -> numbers.stream().map(fn).collect(Collectors.toList());
        
        // Apply different functions
        List<Integer> doubled = applyToList.apply(n -> n * 2);
        List<Integer> squared = applyToList.apply(n -> n * n);
        
        assertEquals(Arrays.asList(2, 4, 6, 8, 10), doubled);
        assertEquals(Arrays.asList(1, 4, 9, 16, 25), squared);
        
        // Test function that returns a function
        Function<Integer, Function<Integer, Integer>> multiplier = 
            factor -> number -> factor * number;
        
        Function<Integer, Integer> multiplyBy3 = multiplier.apply(3);
        Function<Integer, Integer> multiplyBy5 = multiplier.apply(5);
        
        assertEquals(Integer.valueOf(12), multiplyBy3.apply(4));
        assertEquals(Integer.valueOf(20), multiplyBy5.apply(4));
    }
    
    @Test
    void testPureFunctions() {
        // Test pure function (idempotent, no side effects)
        BinaryOperator<Integer> add = (a, b) -> a + b;
        
        // Same inputs always produce same outputs
        assertEquals(Integer.valueOf(7), add.apply(3, 4));
        assertEquals(Integer.valueOf(7), add.apply(3, 4));
        assertEquals(Integer.valueOf(7), add.apply(3, 4));
        
        // Different execution order produces same results
        int result1 = add.apply(add.apply(1, 2), 3);
        int result2 = add.apply(1, add.apply(2, 3));
        assertEquals(result1, result2);
    }
    
    @Test
    void testMemoization() {
        // Test memoization for fibonacci
        // Create a way to count function calls
        int[] callCount = {0};
        
        // Function with side effect of incrementing counter
        Function<Integer, Long> fibonacci = n -> {
            callCount[0]++;
            if (n <= 1) return (long)n;
            return fibonacci.apply(n-1) + fibonacci.apply(n-2);
        };
        
        // Create memoized version
        Map<Integer, Long> cache = new HashMap<>();
        Function<Integer, Long> memoizedFib = n -> {
            return cache.computeIfAbsent(n, key -> {
                callCount[0]++;
                if (key <= 1) return (long)key;
                return memoizedFib.apply(key-1) + memoizedFib.apply(key-2);
            });
        };
        
        // Reset counter and use memoized version
        callCount[0] = 0;
        long result = memoizedFib.apply(10);
        int memoizedCalls = callCount[0];
        
        // Reset counter and use non-memoized version
        callCount[0] = 0;
        long sameResult = fibonacci.apply(10);
        int normalCalls = callCount[0];
        
        // Verify both give same result
        assertEquals(sameResult, result);
        
        // Verify memoized version makes far fewer calls
        assertTrue(memoizedCalls < normalCalls, 
            "Memoized version should make fewer calls (" + memoizedCalls + 
            " vs " + normalCalls + ")");
    }
    
    @Test
    void testImmutability() {
        // Test immutable collection operations
        List<String> original = List.of("apple", "banana", "cherry");
        
        // Creating new collections with modifications
        List<String> added = Stream.concat(original.stream(), Stream.of("date"))
                                  .collect(Collectors.toList());
        
        List<String> removed = original.stream()
                                     .filter(s -> !s.equals("banana"))
                                     .collect(Collectors.toList());
        
        // Original list should be unchanged
        assertEquals(List.of("apple", "banana", "cherry"), original);
        
        // New lists should have expected content
        assertEquals(Arrays.asList("apple", "banana", "cherry", "date"), added);
        assertEquals(Arrays.asList("apple", "cherry"), removed);
        
        // Test immutable objects
        FunctionalProgrammingDemo.ImmutablePerson person = 
            new FunctionalProgrammingDemo.ImmutablePerson("John", 30);
        
        FunctionalProgrammingDemo.ImmutablePerson olderPerson = person.withAge(31);
        FunctionalProgrammingDemo.ImmutablePerson renamedPerson = person.withName("Johnny");
        
        // Original object should be unchanged
        assertEquals("John", person.getName());
        assertEquals(30, person.getAge());
        
        // New objects should have expected properties
        assertEquals("John", olderPerson.getName());
        assertEquals(31, olderPerson.getAge());
        
        assertEquals("Johnny", renamedPerson.getName());
        assertEquals(30, renamedPerson.getAge());
    }
    
    @ParameterizedTest
    @MethodSource("provideOptionsForValidation")
    void testValidation(String input, boolean expectedValid, String expectedMessage) {
        // Create validators
        Validator<String> notEmpty = s -> s != null && !s.isEmpty() ? 
                ValidationResult.valid() : 
                ValidationResult.invalid("String cannot be empty");
        
        Validator<String> notTooLong = s -> s.length() <= 10 ? 
                ValidationResult.valid() : 
                ValidationResult.invalid("String too long");
        
        // Combine validators
        Validator<String> combinedValidator = notEmpty.and(notTooLong);
        
        // Validate
        ValidationResult result = combinedValidator.validate(input);
        
        // Assert
        assertEquals(expectedValid, result.isValid());
        if (!expectedValid) {
            assertEquals(expectedMessage, result.getMessage());
        }
    }
    
    static Stream<Arguments> provideOptionsForValidation() {
        return Stream.of(
            Arguments.of("Hello", true, null),
            Arguments.of("", false, "String cannot be empty"),
            Arguments.of("This string is way too long", false, "String too long")
        );
    }
    
    @Test
    void testResultMonad() {
        // Test success path
        Result<Integer> success = Result.success(10);
        assertEquals("Success: 20", success.map(n -> n * 2).toString());
        
        // Test failure path
        Result<Integer> failure = Result.failure("Something went wrong");
        assertEquals("Failure: Something went wrong", failure.map(n -> n * 2).toString());
        
        // Test flatMap with success
        Function<Integer, Result<String>> safeConvert = n -> {
            if (n >= 0) {
                return Result.success("Number: " + n);
            } else {
                return Result.failure("Number cannot be negative");
            }
        };
        
        assertEquals("Success: Number: 10", success.flatMap(safeConvert).toString());
        
        // Test flatMap with failure
        Result<Integer> negativeResult = Result.success(-5);
        assertEquals("Failure: Number cannot be negative", 
                    negativeResult.flatMap(safeConvert).toString());
        
        // Failure propagation
        assertEquals("Failure: Something went wrong", 
                    failure.flatMap(safeConvert).toString());
    }
    
    // Helper classes from main class to make tests easier
    @FunctionalInterface
    interface Validator<T> {
        ValidationResult validate(T t);
        
        default Validator<T> and(Validator<T> other) {
            return t -> {
                ValidationResult result = this.validate(t);
                return result.isValid() ? other.validate(t) : result;
            };
        }
    }
    
    static class ValidationResult {
        private final boolean valid;
        private final String message;
        
        private ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() { return valid; }
        public String getMessage() { return message; }
        
        public static ValidationResult valid() {
            return new ValidationResult(true, null);
        }
        
        public static ValidationResult invalid(String message) {
            return new ValidationResult(false, message);
        }
        
        @Override
        public String toString() {
            return valid ? "Valid" : "Invalid: " + message;
        }
    }
    
    static class Result<T> {
        private final T value;
        private final String error;
        private final boolean success;
        
        private Result(T value, String error, boolean success) {
            this.value = value;
            this.error = error;
            this.success = success;
        }
        
        public static <T> Result<T> success(T value) {
            return new Result<>(value, null, true);
        }
        
        public static <T> Result<T> failure(String error) {
            return new Result<>(null, error, false);
        }
        
        public <R> Result<R> map(Function<T, R> fn) {
            if (success) {
                return Result.success(fn.apply(value));
            } else {
                return Result.failure(error);
            }
        }
        
        public <R> Result<R> flatMap(Function<T, Result<R>> fn) {
            if (success) {
                return fn.apply(value);
            } else {
                return Result.failure(error);
            }
        }
        
        @Override
        public String toString() {
            return success ? "Success: " + value : "Failure: " + error;
        }
    }
}