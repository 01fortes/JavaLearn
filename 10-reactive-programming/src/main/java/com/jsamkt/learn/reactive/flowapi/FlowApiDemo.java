package com.jsamkt.learn.reactive.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Demonstrates the use of Java's built-in Flow API for reactive programming.
 * The Flow API was introduced in Java 9 as part of JEP 266 and provides 
 * interfaces for Reactive Streams implementation.
 */
public class FlowApiDemo {
    
    public static void demo() {
        System.out.println("\n----- JAVA FLOW API DEMONSTRATION -----");
        
        // Basics of Flow API
        flowApiBasics();
        
        // Custom processor
        transformProcessor();
        
        System.out.println("----- END OF JAVA FLOW API DEMONSTRATION -----\n");
    }
    
    private static void flowApiBasics() {
        System.out.println("\n# Java Flow API Basics");
        
        // Create a publisher
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()) {
            
            // Create a subscriber
            SampleSubscriber<String> subscriber = new SampleSubscriber<>("Subscriber 1", 2);
            
            // Subscribe to the publisher
            publisher.subscribe(subscriber);
            
            System.out.println("Publishing items...");
            String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
            
            // Publish items
            for (String item : items) {
                System.out.println("Publishing: " + item);
                publisher.submit(item);
                
                // Simulate processing delay
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            
            // Wait for processing to complete
            while (publisher.estimateMaximumLag() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        } // Publisher is autoclosed here
        
        // Ensure all async operations complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
    
    private static void transformProcessor() {
        System.out.println("\n# Custom Processor Example");
        
        // Create a publisher
        try (SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()) {
            
            // Create a transforming processor that squares the items
            TransformProcessor<Integer, Integer> squareProcessor = 
                    new TransformProcessor<>(i -> i * i);
            
            // Create a subscriber
            SampleSubscriber<Integer> subscriber = new SampleSubscriber<>("Number Subscriber", 1);
            
            // Connect the flow: publisher -> processor -> subscriber
            publisher.subscribe(squareProcessor);
            squareProcessor.subscribe(subscriber);
            
            System.out.println("Publishing numbers...");
            Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            
            // Publish numbers
            for (Integer number : numbers) {
                System.out.println("Publishing: " + number);
                publisher.submit(number);
                
                // Simulate processing delay
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            
            // Wait for processing to complete
            while (publisher.estimateMaximumLag() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        } // Publisher is autoclosed here
        
        // Ensure all async operations complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
    
    /**
     * A sample Subscriber implementation that processes items with a configurable demand.
     *
     * @param <T> The type of items processed by the subscriber
     */
    private static class SampleSubscriber<T> implements Flow.Subscriber<T> {
        private Flow.Subscription subscription;
        private final String name;
        private final long requestSize;
        private long counter;
        
        public SampleSubscriber(String name, long requestSize) {
            this.name = name;
            this.requestSize = requestSize;
        }
        
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.println(name + ": Subscribed!");
            subscription.request(requestSize); // Request initial items
            counter = requestSize;
        }
        
        @Override
        public void onNext(T item) {
            System.out.println(name + ": Received - " + item + 
                    " on thread " + Thread.currentThread().getName());
            counter--;
            
            if (counter == 0) {
                counter = requestSize;
                subscription.request(requestSize); // Request more items
            }
            
            // Simulate processing time
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                subscription.cancel();
            }
        }
        
        @Override
        public void onError(Throwable throwable) {
            System.err.println(name + ": Error - " + throwable.getMessage());
        }
        
        @Override
        public void onComplete() {
            System.out.println(name + ": Completed!");
        }
    }
    
    /**
     * A processor that transforms items from one type to another.
     *
     * @param <T> The input type
     * @param <R> The output type
     */
    private static class TransformProcessor<T, R> extends SubmissionPublisher<R> 
            implements Flow.Processor<T, R> {
        
        private final Function<T, R> function;
        private Flow.Subscription subscription;
        
        public TransformProcessor(Function<T, R> function) {
            super();
            this.function = function;
        }
        
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1); // Request one item at a time
        }
        
        @Override
        public void onNext(T item) {
            // Transform and submit the item
            R result = function.apply(item);
            submit(result);
            
            // Request another item
            subscription.request(1);
        }
        
        @Override
        public void onError(Throwable throwable) {
            // Forward the error to subscribers
            closeExceptionally(throwable);
        }
        
        @Override
        public void onComplete() {
            // Signal completion to subscribers
            close();
        }
    }
}