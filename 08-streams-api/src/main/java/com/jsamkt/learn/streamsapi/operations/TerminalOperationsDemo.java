package com.jsamkt.learn.streamsapi.operations;

import com.jsamkt.learn.streamsapi.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Demonstrates terminal operations in the Streams API.
 * Terminal operations produce a result or a side-effect and end the stream pipeline.
 */
public class TerminalOperationsDemo {
    
    public static void demo() {
        System.out.println("\n----- TERMINAL OPERATIONS -----");
        
        // Sample data
        List<Product> products = createSampleProducts();
        
        // forEach
        forEachDemo(products);
        
        // count
        countDemo(products);
        
        // min, max
        minMaxDemo(products);
        
        // findFirst, findAny
        findDemo(products);
        
        // anyMatch, allMatch, noneMatch
        matchDemo(products);
        
        // reduce
        reduceDemo(products);
        
        // collect
        collectDemo(products);
        
        System.out.println("----- END OF TERMINAL OPERATIONS -----\n");
    }
    
    private static void forEachDemo(List<Product> products) {
        System.out.println("\n# forEach Operation");
        System.out.println("Print all products:");
        products.stream().forEach(System.out::println);
    }
    
    private static void countDemo(List<Product> products) {
        System.out.println("\n# count Operation");
        
        // Count all products
        long totalCount = products.stream().count();
        System.out.println("Total number of products: " + totalCount);
        
        // Count products in a specific category
        long electronicsCount = products.stream()
                .filter(p -> "Electronics".equals(p.getCategory()))
                .count();
        System.out.println("Number of electronic products: " + electronicsCount);
    }
    
    private static void minMaxDemo(List<Product> products) {
        System.out.println("\n# min/max Operations");
        
        // Find the cheapest product
        Optional<Product> cheapestProduct = products.stream()
                .min(Comparator.comparing(Product::getPrice));
        
        cheapestProduct.ifPresent(p -> System.out.println("Cheapest product: " + p));
        
        // Find the most expensive product
        Optional<Product> mostExpensiveProduct = products.stream()
                .max(Comparator.comparing(Product::getPrice));
        
        mostExpensiveProduct.ifPresent(p -> System.out.println("Most expensive product: " + p));
    }
    
    private static void findDemo(List<Product> products) {
        System.out.println("\n# findFirst/findAny Operations");
        
        // Find the first book
        Optional<Product> firstBook = products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .findFirst();
        
        System.out.println("First book: " + firstBook.orElse(null));
        
        // Find any electronic product
        Optional<Product> anyElectronic = products.stream()
                .filter(p -> "Electronics".equals(p.getCategory()))
                .findAny();
        
        System.out.println("Any electronic product: " + anyElectronic.orElse(null));
    }
    
    private static void matchDemo(List<Product> products) {
        System.out.println("\n# anyMatch/allMatch/noneMatch Operations");
        
        // Check if any product is expensive (> $1000)
        boolean anyExpensive = products.stream()
                .anyMatch(p -> p.getPrice().compareTo(new BigDecimal("1000")) > 0);
        
        System.out.println("Any expensive product? " + anyExpensive);
        
        // Check if all products have a name
        boolean allHaveName = products.stream()
                .allMatch(p -> p.getName() != null && !p.getName().isEmpty());
        
        System.out.println("All products have a name? " + allHaveName);
        
        // Check if no product is free
        boolean noneFree = products.stream()
                .noneMatch(p -> p.getPrice().compareTo(BigDecimal.ZERO) == 0);
        
        System.out.println("No free products? " + noneFree);
    }
    
    private static void reduceDemo(List<Product> products) {
        System.out.println("\n# reduce Operation");
        
        // Sum all prices
        Optional<BigDecimal> totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add);
        
        totalPrice.ifPresent(sum -> System.out.println("Total price of all products: $" + sum));
        
        // Calculate the total price with an identity value
        BigDecimal totalWithIdentity = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        System.out.println("Total price (with identity): $" + totalWithIdentity);
        
        // Find the most expensive product with reduce
        Optional<Product> mostExpensive = products.stream()
                .reduce((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()) > 0 ? p1 : p2);
        
        mostExpensive.ifPresent(p -> System.out.println("Most expensive product (via reduce): " + p));
    }
    
    private static void collectDemo(List<Product> products) {
        System.out.println("\n# collect Operation");
        
        // Collect to list
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        
        System.out.println("Product names: " + productNames);
        
        // Group by category
        var productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        
        System.out.println("Products by category:");
        productsByCategory.forEach((category, prods) -> {
            System.out.println(category + ": " + prods.size() + " products");
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