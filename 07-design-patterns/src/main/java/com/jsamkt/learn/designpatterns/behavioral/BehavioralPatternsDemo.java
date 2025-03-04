package com.jsamkt.learn.designpatterns.behavioral;

import com.jsamkt.learn.designpatterns.behavioral.chain.ChainOfResponsibilityDemo;
import com.jsamkt.learn.designpatterns.behavioral.command.CommandDemo;
import com.jsamkt.learn.designpatterns.behavioral.iterator.IteratorDemo;
import com.jsamkt.learn.designpatterns.behavioral.mediator.MediatorDemo;
import com.jsamkt.learn.designpatterns.behavioral.memento.MementoDemo;
import com.jsamkt.learn.designpatterns.behavioral.observer.ObserverDemo;
import com.jsamkt.learn.designpatterns.behavioral.state.StateDemo;
import com.jsamkt.learn.designpatterns.behavioral.strategy.StrategyDemo;
import com.jsamkt.learn.designpatterns.behavioral.template.TemplateMethodDemo;
import com.jsamkt.learn.designpatterns.behavioral.visitor.VisitorDemo;

/**
 * Demonstrates various behavioral design patterns.
 * Behavioral patterns are concerned with algorithms and the assignment of responsibilities
 * between objects. They describe not just patterns of objects or classes but also the
 * patterns of communication between them.
 */
public class BehavioralPatternsDemo {

    public static void demo() {
        System.out.println("\n----- BEHAVIORAL DESIGN PATTERNS -----");
        
        // Chain of Responsibility Pattern
        ChainOfResponsibilityDemo.demo();
        
        // Command Pattern
        CommandDemo.demo();
        
        // Iterator Pattern
        IteratorDemo.demo();
        
        // Mediator Pattern
        MediatorDemo.demo();
        
        // Memento Pattern
        MementoDemo.demo();
        
        // Observer Pattern
        ObserverDemo.demo();
        
        // State Pattern
        StateDemo.demo();
        
        // Strategy Pattern
        StrategyDemo.demo();
        
        // Template Method Pattern
        TemplateMethodDemo.demo();
        
        // Visitor Pattern
        VisitorDemo.demo();
        
        System.out.println("----- END OF BEHAVIORAL PATTERNS -----\n");
    }
}