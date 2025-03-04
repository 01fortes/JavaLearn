package com.jsamkt.learn.concurrency.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolDemo {

    public static void demo() {
        demonstrateForkJoin();
    }

    private static void demonstrateForkJoin() {
        System.out.println("\n--- Fork/Join Framework ---");

        // Create a task to calculate the sum of numbers from 1 to 1000
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        SumTask task = new SumTask(1, 1000);
        long result = forkJoinPool.invoke(task);

        System.out.println("Sum of numbers from 1 to 1000: " + result);
        System.out.println("Theoretically: " + (1000 * 1001 / 2));
    }
}
