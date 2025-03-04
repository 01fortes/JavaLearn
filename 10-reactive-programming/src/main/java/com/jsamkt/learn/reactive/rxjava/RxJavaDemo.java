package com.jsamkt.learn.reactive.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates basic usage of RxJava library for reactive programming.
 * RxJava is a Java implementation of ReactiveX, a library for composing
 * asynchronous and event-based programs using observable sequences.
 */
public class RxJavaDemo {
    
    public static void demo() {
        System.out.println("\n----- RXJAVA DEMONSTRATION -----");
        
        // Creating Observables
        createObservablesDemo();
        
        // Basic operators
        basicOperatorsDemo();
        
        // Error handling
        errorHandlingDemo();
        
        // Concurrency with Schedulers
        schedulersDemo();
        
        System.out.println("----- END OF RXJAVA DEMONSTRATION -----\n");
    }
    
    private static void createObservablesDemo() {
        System.out.println("\n# Creating Observables");
        
        // Create from a set of values
        System.out.println("\nObservable.just():");
        Observable<String> observable1 = Observable.just("Apple", "Banana", "Cherry");
        observable1.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed!")
        );
        
        // Create from an array
        System.out.println("\nObservable.fromArray():");
        String[] fruits = {"Grape", "Orange", "Mango"};
        Observable<String> observable2 = Observable.fromArray(fruits);
        observable2.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed!")
        );
        
        // Create using a range
        System.out.println("\nObservable.range():");
        Observable<Integer> observable3 = Observable.range(5, 5);
        observable3.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed!")
        );
        
        // Create with the create() method
        System.out.println("\nObservable.create():");
        Observable<String> observable4 = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        
        observable4.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed!")
        );
    }
    
    private static void basicOperatorsDemo() {
        System.out.println("\n# Basic RxJava Operators");
        
        // Map operator
        System.out.println("\nmap() operator - transforms items:");
        Observable.fromArray("Alpha", "Beta", "Gamma")
                .map(String::toUpperCase)
                .subscribe(s -> System.out.println("Received: " + s));
        
        // Filter operator
        System.out.println("\nfilter() operator - filters items:");
        Observable.range(1, 10)
                .filter(n -> n % 2 == 0)
                .subscribe(n -> System.out.println("Received: " + n));
        
        // Reduce operator
        System.out.println("\nreduce() operator - aggregates items:");
        Observable.range(1, 5)
                .reduce((sum, item) -> sum + item)
                .subscribe(sum -> System.out.println("Sum: " + sum));
        
        // FlatMap operator
        System.out.println("\nflatMap() operator - transforms and flattens:");
        Observable.just("Hello", "World")
                .flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(s -> System.out.print(s + " "));
        System.out.println();
    }
    
    private static void errorHandlingDemo() {
        System.out.println("\n# Error Handling in RxJava");
        
        // Using onErrorReturn
        System.out.println("\nonErrorReturn() - replace error with a value:");
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorReturn(error -> {
                    System.err.println("Error caught: " + error.getMessage());
                    return -1; // Default value on error
                })
                .subscribe(
                        result -> System.out.println("Result: " + result),
                        error -> System.err.println("Uncaught error: " + error),
                        () -> System.out.println("Completed!")
                );
        
        // Using onErrorResumeNext
        System.out.println("\nonErrorResumeNext() - switch to a backup Observable:");
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorResumeNext(error -> {
                    System.err.println("Error caught: " + error.getMessage());
                    return Observable.just(-1, -2, -3); // Backup data
                })
                .subscribe(
                        result -> System.out.println("Result: " + result),
                        error -> System.err.println("Uncaught error: " + error),
                        () -> System.out.println("Completed!")
                );
        
        // Using retry
        System.out.println("\nretry() - retry on error:");
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> {
                    int result = 10 / i;
                    System.out.println("Calculating 10 / " + i + " = " + result);
                    return result;
                })
                .retry(1) // Retry once on error
                .onErrorReturn(error -> {
                    System.err.println("Error after retry: " + error.getMessage());
                    return -1;
                })
                .subscribe(
                        result -> System.out.println("Final result: " + result),
                        error -> System.err.println("Uncaught error: " + error),
                        () -> System.out.println("Completed!")
                );
    }
    
    private static void schedulersDemo() {
        System.out.println("\n# Concurrency with Schedulers");
        
        System.out.println("\nUsing different Schedulers:");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        
        Observable<String> observable = Observable.just("Task 1", "Task 2", "Task 3");
        
        // Subscribe on IO scheduler and observe on computation scheduler
        observable
                .doOnNext(task -> System.out.println("Processing " + task + " on thread: " 
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(String::toUpperCase)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed on thread: " + Thread.currentThread().getName());
                    }
                    
                    @Override
                    public void onNext(String value) {
                        System.out.println("Received " + value + " on thread: " 
                                + Thread.currentThread().getName());
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    
                    @Override
                    public void onComplete() {
                        System.out.println("Completed on thread: " + Thread.currentThread().getName());
                    }
                });
        
        // Wait for async operations to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Interval example
        System.out.println("\nInterval operator with timeout:");
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .blockingSubscribe(
                        i -> System.out.println("Received: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Interval completed")
                );
    }
}