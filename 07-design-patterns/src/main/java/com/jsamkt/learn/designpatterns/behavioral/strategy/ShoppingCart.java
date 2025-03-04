package com.jsamkt.learn.designpatterns.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Context class that uses a payment strategy.
 * This class can work with any strategy without being tightly coupled to a specific one.
 */
public class ShoppingCart {
    
    private final List<Item> items;
    
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * Calculates the total price of all items in the cart.
     * 
     * @return The total price
     */
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }
    
    /**
     * Pays for the items in the cart using the provided payment strategy.
     * This is where the Strategy pattern is utilized - the client can choose
     * which payment strategy to use at runtime.
     * 
     * @param paymentStrategy The payment strategy to use
     * @return true if the payment was successful, false otherwise
     */
    public boolean pay(PaymentStrategy paymentStrategy) {
        double amount = calculateTotal();
        return paymentStrategy.pay(amount);
    }
}