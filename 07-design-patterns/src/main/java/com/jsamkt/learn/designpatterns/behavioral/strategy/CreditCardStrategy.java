package com.jsamkt.learn.designpatterns.behavioral.strategy;

/**
 * Concrete implementation of the PaymentStrategy interface for credit card payments.
 */
public class CreditCardStrategy implements PaymentStrategy {
    
    private final String name;
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;
    
    public CreditCardStrategy(String name, String cardNumber, String cvv, String expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println(amount + " paid with credit card (name: " + name + 
                ", card number: " + maskCardNumber(cardNumber) + ")");
        return true;
    }
    
    private String maskCardNumber(String cardNumber) {
        // Show only last 4 digits of the card number
        return "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4);
    }
}