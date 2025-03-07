package com.jsamkt.learn.designpatterns.behavioral.strategy;

/**
 * Simple class representing an item that can be added to a shopping cart.
 */
public class Item {
    
    private final String name;
    private final double price;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
}