package com.jsamkt.learn.concurrency;

import com.jsamkt.learn.concurrency.atomic.AtomicVariableDemo;
import com.jsamkt.learn.concurrency.completablefuture.CompletableFutureDemo;
import com.jsamkt.learn.concurrency.concurrentcollection.ConcurrentCollectionDemo;
import com.jsamkt.learn.concurrency.executor.ExecutorServiceDemo;
import com.jsamkt.learn.concurrency.forkjoinpool.ForkJoinPoolDemo;
import com.jsamkt.learn.concurrency.lock.LockApiDemo;
import com.jsamkt.learn.concurrency.parallelstream.ParallelStreamDemo;
import com.jsamkt.learn.concurrency.synchronization.SynchronizationDemo;
import com.jsamkt.learn.concurrency.thread.ThreadBasicDemo;

/**
 * This tutorial demonstrates various concurrency features in Java.
 */
public class ConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java Concurrency Tutorial =====");
        
        ThreadBasicDemo.demo();
        SynchronizationDemo.demo();
        LockApiDemo.demo();
        AtomicVariableDemo.demo();
        ExecutorServiceDemo.demo();
        CompletableFutureDemo.demo();
        ForkJoinPoolDemo.demo();
        ConcurrentCollectionDemo.demo();
        ParallelStreamDemo.demo();
    }

}