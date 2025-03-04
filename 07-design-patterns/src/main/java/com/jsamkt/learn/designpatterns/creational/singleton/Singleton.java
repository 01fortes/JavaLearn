package com.jsamkt.learn.designpatterns.creational.singleton;

/**
 * Thread-safe Singleton implementation using the lazy initialization holder class idiom.
 * This is a thread-safe implementation that uses class initialization to create the singleton
 * instance when it's first needed.
 */
public class Singleton {
    
    // Private constructor prevents instantiation from other classes
    private Singleton() {
        System.out.println("Singleton instance created");
    }
    
    /**
     * Private static inner class that holds the singleton instance.
     * The inner class is only loaded when the getInstance() method is called.
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    /**
     * Returns the singleton instance.
     * When this method is called for the first time, the SingletonHolder class is loaded,
     * which creates the singleton instance.
     *
     * @return The singleton instance
     */
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void doSomething() {
        System.out.println("Singleton is doing something...");
    }
}