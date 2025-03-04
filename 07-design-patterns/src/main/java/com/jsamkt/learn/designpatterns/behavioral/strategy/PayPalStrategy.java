package com.jsamkt.learn.designpatterns.behavioral.strategy;

/**
 * Concrete implementation of the PaymentStrategy interface for PayPal payments.
 */
public class PayPalStrategy implements PaymentStrategy {
    
    private final String email;
    private final String password;
    
    public PayPalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println(amount + " paid using PayPal (email: " + email + ")");
        return true;
    }
}