package com.jsamkt.learn.reactive.backpressure;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates backpressure handling in reactive programming.
 * Backpressure is a mechanism that allows a consumer to signal to a producer 
 * that it is ready to receive more data, preventing overwhelming the consumer.
 */
public class BackPressureDemo {
    
    public static void demo() {
        System.out.println("\n----- BACKPRESSURE HANDLING DEMONSTRATION -----");
        
        // Demonstrate backpressure problem
        backpressureProblemDemo();
        
        // Demonstrate different backpressure strategies
        backpressureStrategiesDemo();
        
        // Demonstrate manual request-based backpressure
        manualBackpressureDemo();
        
        System.out.println("----- END OF BACKPRESSURE HANDLING DEMONSTRATION -----\n");
    }
    
    private static void backpressureProblemDemo() {
        System.out.println("\n# The Backpressure Problem");
        System.out.println("When a fast producer overwhelms a slow consumer, the system can crash.");
        
        // Using Observable (which doesn't support backpressure) to demonstrate the problem
        System.out.println("\nWithout backpressure - A fast producer and slow consumer:");
        
        try {
            // Create a fast producer of many items
            Observable<Integer> fastProducer = Observable.range(1, 1_000_000)
                    .doOnNext(i -> {
                        if (i % 100_000 == 0) {
                            System.out.println("Producing item " + i);
                        }
                    });
            
            // Subscribe with a slow consumer
            fastProducer
                    .observeOn(Schedulers.io())  // Switch to IO thread
                    .subscribe(
                            i -> {
                                if (i % 100_000 == 0) {
                                    System.out.println("Consuming item " + i);
                                }
                                // Simulate slow processing
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            },
                            error -> System.err.println("Error: " + error.getMessage()),
                            () -> System.out.println("Processing completed")
                    );
            
            // Let it run for a short time
            Thread.sleep(500);
            
            System.out.println("The Observable version keeps emitting regardless of consumer speed.");
            System.out.println("This can lead to MissingBackpressureException or OutOfMemoryError in real applications.");
            
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
    
    private static void backpressureStrategiesDemo() {
        System.out.println("\n# Backpressure Strategies");
        System.out.println("Flowable supports different strategies to handle backpressure.");
        
        try {
            // Create a source that emits items faster than they can be consumed
            Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
                    .take(1000);
            
            // 1. BUFFER strategy - keeps all emissions in an internal buffer
            System.out.println("\nBUFFER strategy - buffers all items:");
            Flowable<Long> buffered = source.toFlowable(BackpressureStrategy.BUFFER);
            subscribeWithSlowConsumer(buffered, "BUFFER");
            
            Thread.sleep(200);
            
            // 2. DROP strategy - drops items when the consumer can't keep up
            System.out.println("\nDROP strategy - drops items when consumer is busy:");
            Flowable<Long> dropped = source.toFlowable(BackpressureStrategy.DROP);
            subscribeWithSlowConsumer(dropped, "DROP");
            
            Thread.sleep(200);
            
            // 3. LATEST strategy - keeps only the latest item
            System.out.println("\nLATEST strategy - keeps only the most recent item:");
            Flowable<Long> latest = source.toFlowable(BackpressureStrategy.LATEST);
            subscribeWithSlowConsumer(latest, "LATEST");
            
            Thread.sleep(200);
            
            // 4. ERROR strategy - signals an error when buffer is full
            System.out.println("\nERROR strategy - signals error when buffer overflows:");
            Flowable<Long> error = source.toFlowable(BackpressureStrategy.ERROR);
            subscribeWithSlowConsumer(error, "ERROR");
            
            Thread.sleep(200);
            
            // 5. MISSING strategy - doesn't do anything about backpressure
            System.out.println("\nMISSING strategy - does nothing about backpressure:");
            Flowable<Long> missing = source.toFlowable(BackpressureStrategy.MISSING);
            subscribeWithSlowConsumer(missing, "MISSING");
            
            Thread.sleep(200);
            
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
    
    private static void subscribeWithSlowConsumer(Flowable<Long> flowable, String strategyName) {
        final AtomicInteger receivedCount = new AtomicInteger(0);
        
        flowable
                .observeOn(Schedulers.io(), false, 8)  // Buffer size of 8
                .subscribe(
                        item -> {
                            int count = receivedCount.incrementAndGet();
                            if (count % 10 == 0) {
                                System.out.println(strategyName + " subscriber received: " + item + " (total: " + count + ")");
                            }
                            // Simulate slow consumer
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        },
                        error -> System.err.println(strategyName + " subscriber error: " + error.getMessage()),
                        () -> System.out.println(strategyName + " subscriber completed. Total received: " + receivedCount.get())
                );
    }
    
    private static void manualBackpressureDemo() {
        System.out.println("\n# Manual Request-Based Backpressure");
        System.out.println("Using request(n) to explicitly control the flow of items.");
        
        try {
            // Create a Flowable that emits integers
            Flowable<Integer> flowable = Flowable.range(1, 100)
                    .doOnNext(i -> System.out.println("Source emitting: " + i));
            
            // Subscribe with manual backpressure control
            flowable.observeOn(Schedulers.io())
                    .subscribe(new Subscriber<Integer>() {
                        private Subscription subscription;
                        private int count = 0;
                        
                        @Override
                        public void onSubscribe(Subscription s) {
                            System.out.println("Subscribed and requesting 5 items initially");
                            this.subscription = s;
                            // Initially request 5 items
                            subscription.request(5);
                        }
                        
                        @Override
                        public void onNext(Integer value) {
                            System.out.println("Received: " + value);
                            count++;
                            
                            // Simulate some processing time
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            
                            // After processing 5 items, request 5 more
                            if (count % 5 == 0) {
                                System.out.println("Requesting 5 more items");
                                subscription.request(5);
                            }
                        }
                        
                        @Override
                        public void onError(Throwable e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        
                        @Override
                        public void onComplete() {
                            System.out.println("Processing completed");
                        }
                    });
            
            // Let the processing run
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}