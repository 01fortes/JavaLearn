package com.jsamkt.learn.reactive.patterns;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates common reactive programming patterns and best practices.
 * Reactive patterns are reusable solutions to common problems encountered
 * when developing reactive applications.
 */
public class ReactivePatternDemo {
    
    public static void demo() {
        System.out.println("\n----- REACTIVE PATTERNS DEMONSTRATION -----");
        
        // Pub-Sub pattern
        pubSubPatternDemo();
        
        // Replay/Caching pattern
        replayPatternDemo();
        
        // Event Bus pattern
        eventBusPatternDemo();
        
        // Retry with backoff pattern
        retryWithBackoffDemo();
        
        System.out.println("----- END OF REACTIVE PATTERNS DEMONSTRATION -----\n");
    }
    
    private static void pubSubPatternDemo() {
        System.out.println("\n# Publisher-Subscriber Pattern");
        System.out.println("The Pub-Sub pattern enables message publishers to broadcast messages to multiple subscribers.");
        
        // Create a subject which acts as both an Observable and an Observer
        PublishSubject<String> subject = PublishSubject.create();
        
        // Subscribe multiple observers
        subject.subscribe(
                message -> System.out.println("Subscriber 1 received: " + message),
                error -> System.err.println("Subscriber 1 error: " + error),
                () -> System.out.println("Subscriber 1 completed")
        );
        
        subject.subscribe(
                message -> System.out.println("Subscriber 2 received: " + message),
                error -> System.err.println("Subscriber 2 error: " + error),
                () -> System.out.println("Subscriber 2 completed")
        );
        
        // Publish messages to the subject
        System.out.println("\nPublishing messages to all subscribers:");
        subject.onNext("Hello");
        subject.onNext("World");
        
        // Add a late subscriber - note that they won't receive previous messages
        subject.subscribe(
                message -> System.out.println("Late Subscriber received: " + message),
                error -> System.err.println("Late Subscriber error: " + error),
                () -> System.out.println("Late Subscriber completed")
        );
        
        System.out.println("\nPublishing more messages:");
        subject.onNext("Third message");
        subject.onComplete();
        
        // Wait a bit to ensure all messages are processed
        sleep(100);
    }
    
    private static void replayPatternDemo() {
        System.out.println("\n# Replay/Caching Pattern");
        System.out.println("The Replay pattern caches emitted items and replays them to new subscribers.");
        
        // Create a ReplaySubject with a buffer size of 2
        ReplaySubject<String> replaySubject = ReplaySubject.createWithSize(2);
        
        // Emit some events
        System.out.println("\nEmitting events to ReplaySubject:");
        replaySubject.onNext("Event 1");
        replaySubject.onNext("Event 2");
        replaySubject.onNext("Event 3");  // This will replace Event 1 in the buffer
        
        // The first subscriber will get the last 2 events
        System.out.println("\nFirst subscriber joins:");
        replaySubject.subscribe(
                event -> System.out.println("Subscriber 1 received: " + event),
                error -> System.err.println("Subscriber 1 error: " + error),
                () -> System.out.println("Subscriber 1 completed")
        );
        
        // Emit one more event
        replaySubject.onNext("Event 4");  // This will replace Event 2 in the buffer
        
        // The second subscriber will get the last 2 events (Event 3 and 4)
        System.out.println("\nSecond subscriber joins:");
        replaySubject.subscribe(
                event -> System.out.println("Subscriber 2 received: " + event),
                error -> System.err.println("Subscriber 2 error: " + error),
                () -> System.out.println("Subscriber 2 completed")
        );
        
        // Complete the subject
        replaySubject.onComplete();
        
        // Alternative approach using the replay() operator
        System.out.println("\nUsing replay() operator to cache and replay values:");
        
        Observable<Long> source = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3);
        
        // Create a ConnectableObservable with replay
        Observable<Long> replayed = source.replay(2).autoConnect();
        
        // First subscriber starts receiving values right away
        replayed.subscribe(val -> System.out.println("First subscriber: " + val));
        
        // Wait for some values to be emitted
        sleep(250);
        
        // Second subscriber joins late but will receive the last 2 values
        replayed.subscribe(val -> System.out.println("Second subscriber: " + val));
        
        // Wait for all events to be processed
        sleep(300);
    }
    
    private static void eventBusPatternDemo() {
        System.out.println("\n# Event Bus Pattern");
        System.out.println("The Event Bus pattern creates a central hub for publishing events and subscribing to them.");
        
        // Create a simple EventBus using a Subject
        EventBus eventBus = new EventBus();
        
        // Register event handlers
        System.out.println("\nRegistering event handlers:");
        eventBus.register(UserEvent.class, event -> 
                System.out.println("User event handler 1: " + event.getUsername() + " - " + event.getAction()));
        
        eventBus.register(UserEvent.class, event -> 
                System.out.println("User event handler 2: " + event.getAction() + " by " + event.getUsername()));
        
        eventBus.register(SystemEvent.class, event -> 
                System.out.println("System event handler: " + event.getMessage() + " (Level: " + event.getLevel() + ")"));
        
        // Post events to the bus
        System.out.println("\nPosting events to the bus:");
        eventBus.post(new UserEvent("alice", "login"));
        eventBus.post(new SystemEvent("System starting up", "INFO"));
        eventBus.post(new UserEvent("bob", "logout"));
        eventBus.post(new SystemEvent("Memory usage high", "WARNING"));
        
        // Clean up
        eventBus.shutdown();
    }
    
    private static void retryWithBackoffDemo() {
        System.out.println("\n# Retry with Backoff Pattern");
        System.out.println("The Retry with Backoff pattern implements resilient error handling with exponential backoff.");
        
        // Simulate a flaky service that fails a few times before succeeding
        AtomicInteger counter = new AtomicInteger(0);
        Observable<String> flakyService = Observable.fromCallable(() -> {
            int attemptNumber = counter.incrementAndGet();
            if (attemptNumber <= 3) {
                System.out.println("Attempt " + attemptNumber + ": Service call failed");
                throw new RuntimeException("Service temporarily unavailable");
            }
            return "Success on attempt " + attemptNumber;
        });
        
        // Function to calculate delay for each retry attempt
        // Uses exponential backoff: 100ms, 200ms, 400ms, etc.
        final long initialDelay = 100;
        
        System.out.println("\nCalling flaky service with retry and backoff:");
        flakyService
                .subscribeOn(Schedulers.io())
                .retryWhen(errors -> errors
                        .zipWith(
                            Observable.range(1, 5), 
                            (error, attempt) -> attempt
                        )
                        .flatMap(attempt -> {
                            long delay = initialDelay * (long)Math.pow(2, attempt - 1);
                            System.out.println("Retrying after " + delay + "ms delay (attempt " + attempt + ")");
                            return Observable.timer(delay, TimeUnit.MILLISECONDS);
                        })
                )
                .blockingSubscribe(
                        result -> System.out.println("Final result: " + result),
                        error -> System.err.println("All retries failed: " + error.getMessage())
                );
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * A simple event bus implementation using RxJava.
     */
    static class EventBus {
        private final PublishSubject<Object> bus = PublishSubject.create();
        
        public <T> void register(Class<T> eventClass, EventHandler<T> handler) {
            bus.ofType(eventClass)
                .subscribe(event -> handler.handle(eventClass.cast(event)));
        }
        
        public void post(Object event) {
            bus.onNext(event);
        }
        
        public void shutdown() {
            bus.onComplete();
        }
    }
    
    /**
     * Event handler interface.
     */
    interface EventHandler<T> {
        void handle(T event);
    }
    
    /**
     * A user-related event.
     */
    static class UserEvent {
        private final String username;
        private final String action;
        
        public UserEvent(String username, String action) {
            this.username = username;
            this.action = action;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getAction() {
            return action;
        }
    }
    
    /**
     * A system-related event.
     */
    static class SystemEvent {
        private final String message;
        private final String level;
        
        public SystemEvent(String message, String level) {
            this.message = message;
            this.level = level;
        }
        
        public String getMessage() {
            return message;
        }
        
        public String getLevel() {
            return level;
        }
    }
}