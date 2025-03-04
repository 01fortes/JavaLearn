package com.jsamkt.learn.designpatterns.creational;

import com.jsamkt.learn.designpatterns.creational.singleton.SingletonDemo;
import com.jsamkt.learn.designpatterns.creational.factory.FactoryMethodDemo;
import com.jsamkt.learn.designpatterns.creational.builder.BuilderPatternDemo;
import com.jsamkt.learn.designpatterns.creational.prototype.PrototypeDemo;
import com.jsamkt.learn.designpatterns.creational.abstractfactory.AbstractFactoryDemo;

/**
 * Demonstrates various creational design patterns.
 * Creational patterns provide various object creation mechanisms that increase flexibility 
 * and reuse of existing code.
 */
public class CreationalPatternsDemo {

    public static void demo() {
        System.out.println("\n----- CREATIONAL DESIGN PATTERNS -----");
        
        // Singleton Pattern
        SingletonDemo.demo();
        
        // Factory Method Pattern
        FactoryMethodDemo.demo();
        
        // Builder Pattern
        BuilderPatternDemo.demo();
        
        // Prototype Pattern
        PrototypeDemo.demo();
        
        // Abstract Factory Pattern
        AbstractFactoryDemo.demo();
        
        System.out.println("----- END OF CREATIONAL PATTERNS -----\n");
    }
}