package com.jsamkt.learn.functional.practical;

public class Transaction {
    private final String customer;
    private final double amount;
    private final String category;

    public Transaction(String customer, double amount, String category) {
        this.customer = customer;
        this.amount = amount;
        this.category = category;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return customer + ": $" + amount + " (" + category + ")";
    }
}
