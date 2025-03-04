package com.jsamkt.learn.functional.advanced;

import com.jsamkt.learn.functional.FunctionalProgrammingDemo;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvancedTechniqueDemo {

    public static void demo() {
        demonstrateAdvancedTechniques();
    }

    private static void demonstrateAdvancedTechniques() {
        System.out.println("\n--- Advanced Functional Techniques ---");

        // Lazy evaluation
        System.out.println("Lazy evaluation with Supplier:");
        Supplier<BigInteger> heavyComputation = () -> {
            System.out.println("Performing heavy computation...");
            return factorial(20);
        };

        System.out.println("Supplier created, but computation not performed yet");

        // Only computed when needed
        System.out.println("Computation result: " + heavyComputation.get());

        // Streams are also lazily evaluated
        System.out.println("\nLazy evaluation with streams:");
        Stream<Integer> stream = Stream.iterate(0, n -> n + 1)
                .peek(n -> System.out.println("Processing: " + n))
                .limit(5);

        System.out.println("Stream created but nothing processed yet");
        System.out.println("Collecting:");
        List<Integer> collected = stream.collect(Collectors.toList());

        // Function composition for complex logic
        System.out.println("\nComplex function composition:");
        Function<Integer, Integer> complexFunction = ((Function<Integer, Integer>) n -> n * 2)
                .andThen(n -> n + 3)
                .andThen(n -> n * n)
                .compose(n -> n + 1);

        System.out.println("complexFunction(5) = " + complexFunction.apply(5));

        // Function memoization for expensive operations
        System.out.println("\nGeneric memoization:");
        Function<Integer, BigInteger> expensiveFactorial = AdvancedTechniqueDemo::factorial;
        Function<Integer, BigInteger> memoizedFactorial = memoize(expensiveFactorial);

        System.out.println("First call for factorial(25):");
        Instant start = Instant.now();
        BigInteger result1 = memoizedFactorial.apply(25);
        System.out.println("Time: " + Duration.between(start, Instant.now()).toMillis() + "ms");

        System.out.println("Second call for factorial(25) (should be instant):");
        start = Instant.now();
        BigInteger result2 = memoizedFactorial.apply(25);
        System.out.println("Time: " + Duration.between(start, Instant.now()).toMillis() + "ms");

        // Optional for better null handling
        System.out.println("\nUsing Optional for null safety:");
        Optional<String> optional1 = Optional.of("Hello");
        Optional<String> optional2 = Optional.empty();

        System.out.println(optional1.map(String::toUpperCase).orElse("Not found"));
        System.out.println(optional2.map(String::toUpperCase).orElse("Not found"));

        // Handling deep null checks with Optional
        System.out.println("\nDeep null handling with Optional:");
        User user = new User("john@example.com",
                new Address("123 Java St", new City("Javatown", "Java Country")));

        // Without Optional (prone to NullPointerException)
        try {
            String country = user.getAddress().getCity().getCountry();
            System.out.println("Country: " + country);
        } catch (NullPointerException e) {
            System.out.println("NPE occurred with traditional approach");
        }

        // With Optional
        String country = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .map(City::getCountry)
                .orElse("Unknown");

        System.out.println("Country with Optional: " + country);

        // Null user test
        User nullUser = null;
        String nullUserCountry = Optional.ofNullable(nullUser)
                .map(User::getAddress)
                .map(Address::getCity)
                .map(City::getCountry)
                .orElse("Unknown");

        System.out.println("Country for null user: " + nullUserCountry);
    }

    // Generic memoization function
    private static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new HashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }

    // Expensive factorial function
    private static BigInteger factorial(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
