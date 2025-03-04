package com.jsamkt.learn.oop.encapsulation;

public class EncapsulationDemo {

    public static void demo(){
        demonstrateEncapsulation();
    }

    /**
     * Encapsulation: Bundling the data (attributes) and methods (behavior) that operate
     * on the data into a single unit (class) and restricting direct access to some of the
     * object's components.
     */
    private static void demonstrateEncapsulation() {
        System.out.println("\n--- Encapsulation ---");

        BankAccount account = new BankAccount("12345", "John Doe", 1000.0);
        System.out.println(account);

        // Attempting to withdraw more than balance
        boolean success = account.withdraw(1500.0);
        System.out.println("Withdrawal successful? " + success);
        System.out.println(account);

        // Successful withdrawal
        success = account.withdraw(500.0);
        System.out.println("Withdrawal successful? " + success);
        System.out.println(account);

        // Deposit
        account.deposit(2000.0);
        System.out.println("After deposit: " + account);

        // Cannot directly access private fields
        // account.balance = -1000.0; // Compilation error
    }
}
