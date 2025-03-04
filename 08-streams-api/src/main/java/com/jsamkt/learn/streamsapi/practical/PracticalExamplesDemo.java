package com.jsamkt.learn.streamsapi.practical;

import com.jsamkt.learn.streamsapi.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates practical examples and real-world applications of the Streams API.
 * These examples show how streams can be used to solve common programming tasks
 * in a concise and expressive way.
 */
public class PracticalExamplesDemo {
    
    public static void demo() {
        System.out.println("\n----- PRACTICAL EXAMPLES -----");
        
        // Data transformation
        dataTransformationExample();
        
        // Data filtering and validation
        dataFilteringExample();
        
        // Statistical calculations
        statisticalCalculationsExample();
        
        // Text processing
        textProcessingExample();
        
        // Batch processing
        batchProcessingExample();
        
        System.out.println("----- END OF PRACTICAL EXAMPLES -----\n");
    }
    
    private static void dataTransformationExample() {
        System.out.println("\n# Data Transformation Example");
        
        // Sample data: List of products
        List<Product> products = createSampleProducts();
        
        System.out.println("Converting products to a formatted report:");
        
        // Transform products to formatted strings
        List<String> report = products.stream()
                .map(p -> String.format("%-15s %-12s $%7.2f", 
                        p.getName(), 
                        p.getCategory(), 
                        p.getPrice()))
                .collect(Collectors.toList());
        
        // Print report header
        System.out.println(String.format("%-15s %-12s %8s", "NAME", "CATEGORY", "PRICE"));
        System.out.println("-".repeat(40));
        
        // Print each line
        report.forEach(System.out::println);
        
        // Calculate and print totals by category
        System.out.println("\nTotal value by category:");
        Map<String, BigDecimal> totalByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Product::getPrice,
                                BigDecimal::add)));
        
        totalByCategory.forEach((category, total) -> {
            System.out.printf("%-12s $%7.2f%n", category, total);
        });
    }
    
    private static void dataFilteringExample() {
        System.out.println("\n# Data Filtering Example");
        
        // Sample data: List of products
        List<Product> products = createSampleProducts();
        
        // Filter products by price range and category
        BigDecimal minPrice = new BigDecimal("50");
        BigDecimal maxPrice = new BigDecimal("500");
        
        System.out.println("Finding electronics between $50 and $500:");
        
        List<Product> filteredProducts = products.stream()
                .filter(p -> "Electronics".equals(p.getCategory()))
                .filter(p -> p.getPrice().compareTo(minPrice) >= 0)
                .filter(p -> p.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
        
        filteredProducts.forEach(p -> {
            System.out.printf("%s: $%.2f%n", p.getName(), p.getPrice());
        });
        
        // Find products that match certain criteria
        System.out.println("\nProducts with names containing 'a' and price > $100:");
        
        products.stream()
                .filter(p -> p.getName().toLowerCase().contains("a"))
                .filter(p -> p.getPrice().compareTo(new BigDecimal("100")) > 0)
                .forEach(p -> {
                    System.out.printf("%s: $%.2f%n", p.getName(), p.getPrice());
                });
    }
    
    private static void statisticalCalculationsExample() {
        System.out.println("\n# Statistical Calculations Example");
        
        // Generate some random data
        Random random = new Random(42); // Seed for reproducibility
        List<Double> measurements = IntStream.range(0, 1000)
                .mapToDouble(i -> 100 + 15 * random.nextGaussian())
                .boxed()
                .collect(Collectors.toList());
        
        // Calculate statistics
        DoubleSummaryStatistics stats = measurements.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        
        System.out.println("Data statistics:");
        System.out.printf("Count: %d%n", stats.getCount());
        System.out.printf("Min: %.2f%n", stats.getMin());
        System.out.printf("Max: %.2f%n", stats.getMax());
        System.out.printf("Sum: %.2f%n", stats.getSum());
        System.out.printf("Average: %.2f%n", stats.getAverage());
        
        // Calculate median
        double median = measurements.stream()
                .sorted()
                .skip(measurements.size() / 2 - (measurements.size() % 2 == 0 ? 1 : 0))
                .limit(measurements.size() % 2 == 0 ? 2 : 1)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        
        System.out.printf("Median: %.2f%n", median);
        
        // Calculate standard deviation
        double mean = stats.getAverage();
        double variance = measurements.stream()
                .mapToDouble(x -> Math.pow(x - mean, 2))
                .average()
                .orElse(0);
        double stdDev = Math.sqrt(variance);
        
        System.out.printf("Standard Deviation: %.2f%n", stdDev);
    }
    
    private static void textProcessingExample() {
        System.out.println("\n# Text Processing Example");
        
        String text = "The quick brown fox jumps over the lazy dog. The dog barks, but the fox is already gone.";
        System.out.println("Original text: " + text);
        
        // Count words
        long wordCount = Arrays.stream(text.split("\\s+"))
                .count();
        System.out.println("Word count: " + wordCount);
        
        // Find word frequency
        Map<String, Long> wordFrequency = Arrays.stream(text.toLowerCase().split("[\\s.,]+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        System.out.println("\nWord frequency:");
        wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    System.out.printf("%-10s %2d%n", entry.getKey(), entry.getValue());
                });
        
        // Distinct words
        List<String> distinctWords = Arrays.stream(text.toLowerCase().split("[\\s.,]+"))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        System.out.println("\nDistinct words (alphabetical):");
        System.out.println(distinctWords);
    }
    
    private static void batchProcessingExample() {
        System.out.println("\n# Batch Processing Example");
        
        // Creating a large dataset
        List<Integer> largeDataset = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        
        System.out.println("Processing data in batches of 10:");
        
        // Process in batches using streams
        IntStream.range(0, (largeDataset.size() + 9) / 10) // Ceiling division by 10
                .mapToObj(i -> {
                    int start = i * 10;
                    int end = Math.min(start + 10, largeDataset.size());
                    return largeDataset.subList(start, end);
                })
                .forEach(batch -> {
                    int batchSum = batch.stream().mapToInt(Integer::intValue).sum();
                    System.out.printf("Batch: %s, Sum: %d%n", batch, batchSum);
                    
                    // Simulate batch processing time
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
    
    private static List<Product> createSampleProducts() {
        return Arrays.asList(
                new Product("1", "Laptop", "Electronics", new BigDecimal("1200.00")),
                new Product("2", "Smartphone", "Electronics", new BigDecimal("800.00")),
                new Product("3", "Java Programming", "Books", new BigDecimal("50.00")),
                new Product("4", "Headphones", "Electronics", new BigDecimal("150.00")),
                new Product("5", "Design Patterns", "Books", new BigDecimal("45.00")),
                new Product("6", "Coffee Maker", "Home Appliances", new BigDecimal("90.00")),
                new Product("7", "Desk Chair", "Furniture", new BigDecimal("180.00")),
                new Product("8", "Tablet", "Electronics", new BigDecimal("350.00")),
                new Product("9", "Printer", "Electronics", new BigDecimal("250.00")),
                new Product("10", "Refrigerator", "Home Appliances", new BigDecimal("950.00"))
        );
    }
}