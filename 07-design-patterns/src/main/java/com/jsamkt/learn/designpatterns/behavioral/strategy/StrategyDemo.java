package com.jsamkt.learn.designpatterns.behavioral.strategy;

/**
 * Demonstrates the Strategy pattern.
 * The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them
 * interchangeable. Strategy lets the algorithm vary independently from clients that use it.
 */
public class StrategyDemo {
    
    public static void demo() {
        System.out.println("\n# Strategy Pattern");
        System.out.println("The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.");
        
        // Create some items
        Item item1 = new Item("Book", 49.99);
        Item item2 = new Item("Headphones", 149.99);
        
        // Create a shopping cart and add items
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(item1);
        cart.addItem(item2);
        
        // Calculate total
        System.out.println("Shopping cart total: $" + cart.calculateTotal());
        
        // Pay with credit card strategy
        System.out.println("\nPaying with credit card:");
        PaymentStrategy creditCardStrategy = new CreditCardStrategy(
                "John Doe", "1234567890123456", "123", "12/2025");
        cart.pay(creditCardStrategy);
        
        // Pay with PayPal strategy
        System.out.println("\nPaying with PayPal:");
        PaymentStrategy payPalStrategy = new PayPalStrategy("john.doe@example.com", "password");
        cart.pay(payPalStrategy);
    }
}