package com.jsamkt.learn.reactive;

import com.jsamkt.learn.reactive.basics.ReactiveBasicsDemo;
import com.jsamkt.learn.reactive.rxjava.RxJavaDemo;
import com.jsamkt.learn.reactive.reactor.ReactorDemo;
import com.jsamkt.learn.reactive.flowapi.FlowApiDemo;
import com.jsamkt.learn.reactive.operators.OperatorsDemo;
import com.jsamkt.learn.reactive.backpressure.BackPressureDemo;
import com.jsamkt.learn.reactive.patterns.ReactivePatternDemo;

/**
 * Main demonstration class for reactive programming in Java.
 * Reactive programming is a programming paradigm oriented around data flows and the propagation of change,
 * which means it's possible to express static or dynamic data flows with ease in the programming languages.
 */
public class ReactiveProgrammingDemo {
    
    public static void main(String[] args) {
        System.out.println("===== REACTIVE PROGRAMMING DEMONSTRATION =====");
        
        // Demonstrate reactive programming basics
        ReactiveBasicsDemo.demo();
        
        // Demonstrate Flow API (Java 9+)
        FlowApiDemo.demo();
        
        // Demonstrate RxJava
        RxJavaDemo.demo();
        
        // Demonstrate Project Reactor
        ReactorDemo.demo();
        
        // Demonstrate reactive operators
        OperatorsDemo.demo();
        
        // Demonstrate backpressure handling
        BackPressureDemo.demo();
        
        // Demonstrate reactive patterns
        ReactivePatternDemo.demo();
        
        System.out.println("===== END OF REACTIVE PROGRAMMING DEMONSTRATION =====");
    }
}