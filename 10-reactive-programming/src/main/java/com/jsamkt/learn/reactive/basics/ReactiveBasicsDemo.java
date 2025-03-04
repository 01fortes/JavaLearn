package com.jsamkt.learn.reactive.basics;

/**
 * Demonstrates the basic concepts of reactive programming.
 * Reactive programming is a programming paradigm that deals with asynchronous data streams
 * and the propagation of changes through the use of a declarative programming model.
 */
public class ReactiveBasicsDemo {
    
    public static void demo() {
        System.out.println("\n----- REACTIVE PROGRAMMING BASICS -----");
        
        // Demonstrate the Observer pattern (foundation of reactive programming)
        observerPatternDemo();
        
        // Demonstrate the difference between imperative and reactive styles
        imperativeVsReactiveDemo();
        
        // Demonstrate key reactive concepts
        reactiveConceptsDemo();
        
        System.out.println("----- END OF REACTIVE PROGRAMMING BASICS -----\n");
    }
    
    private static void observerPatternDemo() {
        System.out.println("\n# Observer Pattern - Foundation of Reactive Programming");
        
        // Create a subject (observable)
        WeatherStation weatherStation = new WeatherStation();
        
        // Create observers
        WeatherDisplay phoneDisplay = new WeatherDisplay("Phone App");
        WeatherDisplay webDisplay = new WeatherDisplay("Web Interface");
        WeatherDisplay tvDisplay = new WeatherDisplay("TV Broadcast");
        
        // Register observers with the subject
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(webDisplay);
        weatherStation.addObserver(tvDisplay);
        
        System.out.println("\nChanging temperature to 25.0°C:");
        weatherStation.setTemperature(25.0);
        
        System.out.println("\nChanging temperature to 30.5°C:");
        weatherStation.setTemperature(30.5);
        
        System.out.println("\nRemoving TV display:");
        weatherStation.removeObserver(tvDisplay);
        
        System.out.println("\nChanging temperature to 18.2°C:");
        weatherStation.setTemperature(18.2);
    }
    
    private static void imperativeVsReactiveDemo() {
        System.out.println("\n# Imperative vs Reactive Programming");
        
        System.out.println("\nImperative approach (pull-based):");
        System.out.println("1. Explicitly request data");
        System.out.println("2. Process data synchronously");
        System.out.println("3. Handle errors with try-catch");
        System.out.println("4. Code example:");
        System.out.println("   try {");
        System.out.println("       Data data = database.fetchData();  // Blocking call");
        System.out.println("       List<Data> filtered = new ArrayList<>();");
        System.out.println("       for (Data item : data) {");
        System.out.println("           if (item.getValue() > 10) {");
        System.out.println("               filtered.add(item);");
        System.out.println("           }");
        System.out.println("       }");
        System.out.println("       for (Data item : filtered) {");
        System.out.println("           display(item);");
        System.out.println("       }");
        System.out.println("   } catch (Exception e) {");
        System.out.println("       handleError(e);");
        System.out.println("   }");
        
        System.out.println("\nReactive approach (push-based):");
        System.out.println("1. Subscribe to data streams");
        System.out.println("2. Process data asynchronously when it arrives");
        System.out.println("3. Handle errors as part of the stream");
        System.out.println("4. Code example:");
        System.out.println("   database.fetchData()  // Non-blocking");
        System.out.println("       .filter(item -> item.getValue() > 10)");
        System.out.println("       .subscribe(");
        System.out.println("           item -> display(item),  // onNext");
        System.out.println("           error -> handleError(error),  // onError");
        System.out.println("           () -> complete()  // onComplete");
        System.out.println("       );");
    }
    
    private static void reactiveConceptsDemo() {
        System.out.println("\n# Key Reactive Programming Concepts");
        
        System.out.println("\n1. Data Streams");
        System.out.println("   - Everything is a stream of data");
        System.out.println("   - Streams can emit: values, errors, or completion signals");
        System.out.println("   - Examples: user events, network responses, sensor readings");
        
        System.out.println("\n2. Asynchronous Processing");
        System.out.println("   - Non-blocking operations");
        System.out.println("   - Callback-based or compositional");
        System.out.println("   - Better resource utilization");
        
        System.out.println("\n3. Declarative Style");
        System.out.println("   - Describe what to do, not how to do it");
        System.out.println("   - Chain operations using functional operators");
        System.out.println("   - Compose transformations (map, filter, reduce, etc.)");
        
        System.out.println("\n4. Backpressure");
        System.out.println("   - Handling fast producers and slow consumers");
        System.out.println("   - Consumer can signal its processing capacity");
        System.out.println("   - Strategies: buffer, drop, throttle, etc.");
        
        System.out.println("\n5. Error Handling");
        System.out.println("   - Errors flow through the stream");
        System.out.println("   - Recovery operations (retry, fallback, etc.)");
        System.out.println("   - Graceful degradation");
        
        System.out.println("\n6. Reactive Manifesto Principles");
        System.out.println("   - Responsive: systems respond in a timely manner");
        System.out.println("   - Resilient: systems remain responsive during failures");
        System.out.println("   - Elastic: systems stay responsive under varying workload");
        System.out.println("   - Message-driven: systems rely on asynchronous message passing");
    }
}