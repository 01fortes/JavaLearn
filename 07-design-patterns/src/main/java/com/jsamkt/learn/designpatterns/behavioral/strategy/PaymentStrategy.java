package com.jsamkt.learn.designpatterns.behavioral.strategy;

/**
 * Interface for different payment strategies.
 * This is the Strategy interface that defines the behavior that can vary.
 */
public interface PaymentStrategy {
    
    /**
     * Processes a payment.
     * 
     * @param amount The amount to pay
     * @return true if the payment was successful, false otherwise
     */
    boolean pay(double amount);
}