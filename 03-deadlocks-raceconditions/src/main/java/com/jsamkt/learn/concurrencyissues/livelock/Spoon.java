package com.jsamkt.learn.concurrencyissues.livelock;

public class Spoon {
    private String owner;

    public synchronized void use(String newOwner) throws InterruptedException {
        // If someone else has the spoon, wait
        while (owner != null && !owner.equals(newOwner)) {
            System.out.println(newOwner + " sees that " + owner + " has the spoon, so " +
                    newOwner + " politely waits");

            // Be polite and let the other person use it
            owner = null;
            System.out.println(newOwner + " politely offers the spoon to the other");

            // Wait a bit
            Thread.sleep(10);
        }

        // Take ownership of the spoon
        owner = newOwner;
        System.out.println(owner + " has the spoon now");
    }
}
