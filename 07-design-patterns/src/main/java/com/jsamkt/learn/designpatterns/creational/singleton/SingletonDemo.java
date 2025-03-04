package com.jsamkt.learn.designpatterns.creational.singleton;

/**
 * Demonstrates the Singleton pattern.
 * The Singleton pattern ensures a class has only one instance and provides a global point
 * of access to it. This is useful when exactly one object is needed to coordinate actions
 * across the system.
 */
public class SingletonDemo {
    
    public static void demo() {
        System.out.println("\n# Singleton Pattern");
        System.out.println("The Singleton pattern ensures a class has only one instance and provides a global point of access to it.");
        
        // Get the singleton instance
        System.out.println("Getting singleton instance for the first time:");
        Singleton singleton1 = Singleton.getInstance();
        singleton1.doSomething();
        
        // Get the singleton instance again - should be the same instance
        System.out.println("\nGetting singleton instance for the second time:");
        Singleton singleton2 = Singleton.getInstance();
        singleton2.doSomething();
        
        // Verify both references point to the same object
        System.out.println("\nVerifying both references point to the same object:");
        System.out.println("Are they the same instance? " + (singleton1 == singleton2));
    }
}