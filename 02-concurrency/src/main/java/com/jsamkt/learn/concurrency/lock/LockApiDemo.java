package com.jsamkt.learn.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockApiDemo {
    private static final List<String> sharedList = new ArrayList<>();
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    public static void demo()  {
        try {
            demonstrateLockAPI();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateLockAPI() throws InterruptedException {
        System.out.println("\n--- Lock API ---");

        // Reset shared list
        sharedList.clear();

        // Create reader threads
        Thread[] readers = new Thread[3];
        for (int i = 0; i < readers.length; i++) {
            final int readerId = i;
            readers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    readData(readerId);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // Create writer threads
        Thread[] writers = new Thread[2];
        for (int i = 0; i < writers.length; i++) {
            final int writerId = i;
            writers[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    writeData(writerId, "Data " + writerId + "-" + j);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // Start all threads
        for (Thread writer : writers) {
            writer.start();
        }
        for (Thread reader : readers) {
            reader.start();
        }

        // Wait for all threads to complete
        for (Thread writer : writers) {
            writer.join();
        }
        for (Thread reader : readers) {
            reader.join();
        }

        System.out.println("Final shared list: " + sharedList);
    }

    private static void readData(int readerId) {
        readLock.lock();
        try {
            System.out.println("Reader " + readerId + " is reading: " + sharedList);
        } finally {
            readLock.unlock();
        }
    }

    private static void writeData(int writerId, String data) {
        writeLock.lock();
        try {
            System.out.println("Writer " + writerId + " is writing: " + data);
            sharedList.add(data);
            // Simulate some processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } finally {
            writeLock.unlock();
        }
    }
}
