package com.jsamkt.learn.functional.practical;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticalApplicationDemo {

    public static void demo() {
        demonstratePracticalApplications();
    }

    private static void demonstratePracticalApplications() {
        System.out.println("\n--- Practical Applications ---");

        // Event handling with lambdas
        System.out.println("Event handling:");
        Button button = new Button("Click me");
        button.setOnClick(() -> System.out.println("Button clicked!"));
        button.click();

        // Building a simple data processing pipeline
        System.out.println("\nData processing pipeline:");
        List<Transaction> transactions = Arrays.asList(
                new Transaction("John", 100.0, "groceries"),
                new Transaction("Alice", 200.0, "electronics"),
                new Transaction("Bob", 50.0, "groceries"),
                new Transaction("John", 300.0, "electronics"),
                new Transaction("Alice", 150.0, "clothing")
        );

        // Find total spent on groceries by each person
        Map<String, Double> grocerySpendingByPerson = transactions.stream()
                .filter(t -> t.getCategory().equals("groceries"))
                .collect(Collectors.groupingBy(
                        Transaction::getCustomer,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Grocery spending by person: " + grocerySpendingByPerson);

        // Find the total spending per category
        Map<String, Double> spendingByCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Spending by category: " + spendingByCategory);

        // Functional programming for validation
        System.out.println("\nValidation pipeline:");

        // Create a validation framework
        Validator<String> notEmpty = s -> s != null && !s.isEmpty() ?
                ValidationResult.valid() :
                ValidationResult.invalid("String cannot be empty");

        Validator<String> notTooLong = s -> s.length() <= 10 ?
                ValidationResult.valid() :
                ValidationResult.invalid("String too long");

        Validator<String> combinedValidator = notEmpty.and(notTooLong);

        System.out.println(combinedValidator.validate("Hello"));
        System.out.println(combinedValidator.validate(""));
        System.out.println(combinedValidator.validate("This string is way too long"));

        // Functional error handling
        System.out.println("\nFunctional error handling:");
        Result<Integer> success = Result.success(10);
        Result<Integer> failure = Result.failure("Something went wrong");

        System.out.println(success.map(n -> n * 2));
        System.out.println(failure.map(n -> n * 2));

        // Combining computations that might fail
        Function<Integer, Result<Integer>> safeDivideBy = divisor ->
                divisor == 0 ? Result.failure("Cannot divide by zero") : Result.success(100 / divisor);

        System.out.println(Result.success(10).flatMap(safeDivideBy));
        System.out.println(Result.success(0).flatMap(safeDivideBy));
//        System.out.println(Result.failure("Already failed").flatMap(safeDivideBy));
    }
}
