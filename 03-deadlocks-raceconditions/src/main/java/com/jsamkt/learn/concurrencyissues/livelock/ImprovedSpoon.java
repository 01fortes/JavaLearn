package com.jsamkt.learn.concurrencyissues.livelock;

import java.util.Random;

public class ImprovedSpoon {
    private String owner;
    private final Random random = new Random();

    public synchronized void use(String newOwner) throws InterruptedException {
        int attempts = 0;

        // If someone else has the spoon, try to get it
        while (owner != null && !owner.equals(newOwner)) {
            System.out.println(newOwner + " sees that " + owner + " has the spoon, attempt " + (++attempts));

            // Introduce randomness to break symmetry
            if (random.nextBoolean()) {
                System.out.println(newOwner + " decides to wait");
                Thread.sleep(random.nextInt(100));
            } else {
                // Offer the spoon to the other
                owner = null;
                System.out.println(newOwner + " offers the spoon to the other");
                Thread.sleep(10);
            }
        }

        // Take ownership of the spoon
        owner = newOwner;
        System.out.println(owner + " has the spoon now and is eating...");
        Thread.sleep(100);
        System.out.println(owner + " finished eating");
    }
}
