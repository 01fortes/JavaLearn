package com.jsamkt.learn.streamsapi.collectors;

import com.jsamkt.learn.streamsapi.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of Collectors in the Streams API.
 * Collectors are used with the collect() terminal operation to transform elements
 * into a different form, often a collection.
 */
public class CollectorsDemo {
    
    public static void demo() {
        System.out.println("\n----- COLLECTORS -----");
        
        List<Product> products = createSampleProducts();
        
        // toList, toSet
        collectToCollectionDemo(products);
        
        // joining
        joiningDemo(products);
        
        // groupingBy
        groupingByDemo(products);
        
        // partitioningBy
        partitioningByDemo(products);
        
        // mapping
        mappingDemo(products);
        
        // summarizing
        summarizingDemo(products);
        
        System.out.println("----- END OF COLLECTORS -----\n");
    }
    
    private static void collectToCollectionDemo(List<Product> products) {
        System.out.println("\n# toList, toSet, toCollection");
        
        // toList
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println("Product names as List: " + productNames);
        
        // toSet
        Set<String> categorySet = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
        System.out.println("Categories as Set: " + categorySet);
        
        // toCollection
        TreeSet<String> sortedNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Product names as sorted TreeSet: " + sortedNames);
    }
    
    private static void joiningDemo(List<Product> products) {
        System.out.println("\n# joining");
        
        // Simple joining
        String allNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining());
        System.out.println("All names joined: " + allNames);
        
        // Joining with delimiter
        String namesWithComma = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Names joined with comma: " + namesWithComma);
        
        // Joining with delimiter, prefix, and suffix
        String namesWithBrackets = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Names joined with brackets: " + namesWithBrackets);
    }
    
    private static void groupingByDemo(List<Product> products) {
        System.out.println("\n# groupingBy");
        
        // Simple groupingBy
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        
        System.out.println("Products grouped by category:");
        productsByCategory.forEach((category, prods) -> {
            System.out.println(category + ": " + prods.size() + " products");
            prods.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        });
        
        // groupingBy with downstream collector
        Map<String, Long> productCountByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        
        System.out.println("\nProduct count by category:");
        productCountByCategory.forEach((category, count) -> {
            System.out.println(category + ": " + count + " products");
        });
    }
    
    private static void partitioningByDemo(List<Product> products) {
        System.out.println("\n# partitioningBy");
        
        // Partition products by price > $100
        BigDecimal priceThreshold = new BigDecimal("100");
        Map<Boolean, List<Product>> partitionedByPrice = products.stream()
                .collect(Collectors.partitioningBy(
                        p -> p.getPrice().compareTo(priceThreshold) > 0));
        
        System.out.println("Products partitioned by price > $100:");
        System.out.println("Expensive products (>$100):");
        partitionedByPrice.get(true).forEach(p -> 
                System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        
        System.out.println("Affordable products (≤$100):");
        partitionedByPrice.get(false).forEach(p -> 
                System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
    }
    
    private static void mappingDemo(List<Product> products) {
        System.out.println("\n# mapping");
        
        // Mapping product names by category
        Map<String, List<String>> productNamesByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));
        
        System.out.println("Product names by category:");
        productNamesByCategory.forEach((category, names) -> {
            System.out.println(category + ": " + names);
        });
    }
    
    private static void summarizingDemo(List<Product> products) {
        System.out.println("\n# summarizing");
        
        // Find total price of all products
        BigDecimal totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        System.out.println("Total price of all products: $" + totalPrice);
        
        // Find average price by category
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(p -> p.getPrice().doubleValue())));
        
        System.out.println("\nAverage price by category:");
        averagePriceByCategory.forEach((category, avgPrice) -> {
            System.out.printf("%s: $%.2f%n", category, avgPrice);
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
                new Product("7", "Desk Chair", "Furniture", new BigDecimal("180.00"))
        );
    }
}