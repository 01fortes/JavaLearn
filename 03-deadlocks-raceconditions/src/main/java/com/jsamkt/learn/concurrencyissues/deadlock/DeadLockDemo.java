package com.jsamkt.learn.concurrencyissues.deadlock;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

    public static void demo(){
        try {
            demonstrateDeadlock();
            demonstrateDeadlockSolution();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void demonstrateDeadlock() throws InterruptedException {
        System.out.println("\n--- Deadlock ---");

        final Object resource1 = new Object();
        final Object resource2 = new Object();

        // Thread 1: Tries to lock resource1 then resource2
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Attempting to lock resource 1");
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");

                // Introduce delay to increase chance of deadlock
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Attempting to lock resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2: Tries to lock resource2 then resource1 (opposite order)
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Attempting to lock resource 2");
            synchronized (resource2) {
                System.out.println("Thread 2: Locked resource 2");

                // Introduce delay to increase chance of deadlock
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Attempting to lock resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait a bit to observe deadlock
        Thread.sleep(500);

        // This is how you would detect deadlock in a real scenario
        System.out.println("\nDetecting deadlock...");
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();

        if (threadIds != null) {
            System.out.println("Deadlock detected between threads:");
            ThreadInfo[] infos = bean.getThreadInfo(threadIds, true, true);
            for (ThreadInfo info : infos) {
                System.out.println(info.getThreadName() + " waiting for lock on " +
                        info.getLockName() + " held by " + info.getLockOwnerName());
            }
        } else {
            System.out.println("No deadlock detected (unusual - might need to run again)");
        }

        // Forcibly stop threads to continue the demo (don't do this in production!)
        thread1.interrupt();
        thread2.interrupt();

        System.out.println("\nDeadlock Prevention Strategies:");
        System.out.println("1. Always acquire locks in the same order");
        System.out.println("2. Use tryLock() with timeout from the Lock interface");
        System.out.println("3. Use higher-level constructs that handle synchronization");
        System.out.println("4. Avoid nested locks when possible");
    }

    private static void demonstrateDeadlockSolution() throws InterruptedException {
        System.out.println("\n2. Solution to Deadlocks: Lock Ordering and tryLock");

        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean locked1 = false;
            boolean locked2 = false;

            try {
                // Try to acquire locks with timeout
                locked1 = lock1.tryLock(100, TimeUnit.MILLISECONDS);
                if (locked1) {
                    System.out.println("Thread 1: Acquired lock1");

                    // Small delay to simulate work
                    Thread.sleep(10);

                    locked2 = lock2.tryLock(100, TimeUnit.MILLISECONDS);
                    if (locked2) {
                        System.out.println("Thread 1: Acquired lock2");
                        // Critical section with both locks
                        System.out.println("Thread 1: Working in critical section");
                    } else {
                        System.out.println("Thread 1: Could not acquire lock2, backing off");
                    }
                } else {
                    System.out.println("Thread 1: Could not acquire lock1, backing off");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release locks in reverse order of acquisition
                if (locked2) {
                    lock2.unlock();
                    System.out.println("Thread 1: Released lock2");
                }
                if (locked1) {
                    lock1.unlock();
                    System.out.println("Thread 1: Released lock1");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean locked1 = false;
            boolean locked2 = false;

            try {
                // Always acquire locks in the same order as thread1!
                locked1 = lock1.tryLock(100, TimeUnit.MILLISECONDS);
                if (locked1) {
                    System.out.println("Thread 2: Acquired lock1");

                    // Small delay to simulate work
                    Thread.sleep(10);

                    locked2 = lock2.tryLock(100, TimeUnit.MILLISECONDS);
                    if (locked2) {
                        System.out.println("Thread 2: Acquired lock2");
                        // Critical section with both locks
                        System.out.println("Thread 2: Working in critical section");
                    } else {
                        System.out.println("Thread 2: Could not acquire lock2, backing off");
                    }
                } else {
                    System.out.println("Thread 2: Could not acquire lock1, backing off");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release locks in reverse order of acquisition
                if (locked2) {
                    lock2.unlock();
                    System.out.println("Thread 2: Released lock2");
                }
                if (locked1) {
                    lock1.unlock();
                    System.out.println("Thread 2: Released lock1");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
