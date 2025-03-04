package com.jsamkt.learn.designpatterns.structural;

import com.jsamkt.learn.designpatterns.structural.adapter.AdapterDemo;
import com.jsamkt.learn.designpatterns.structural.bridge.BridgeDemo;
import com.jsamkt.learn.designpatterns.structural.composite.CompositeDemo;
import com.jsamkt.learn.designpatterns.structural.decorator.DecoratorDemo;
import com.jsamkt.learn.designpatterns.structural.facade.FacadeDemo;
import com.jsamkt.learn.designpatterns.structural.flyweight.FlyweightDemo;
import com.jsamkt.learn.designpatterns.structural.proxy.ProxyDemo;

/**
 * Demonstrates various structural design patterns.
 * Structural patterns explain how to assemble objects and classes into larger structures,
 * while keeping these structures flexible and efficient.
 */
public class StructuralPatternsDemo {

    public static void demo() {
        System.out.println("\n----- STRUCTURAL DESIGN PATTERNS -----");
        
        // Adapter Pattern
        AdapterDemo.demo();
        
        // Bridge Pattern
        BridgeDemo.demo();
        
        // Composite Pattern
        CompositeDemo.demo();
        
        // Decorator Pattern
        DecoratorDemo.demo();
        
        // Facade Pattern
        FacadeDemo.demo();
        
        // Flyweight Pattern
        FlyweightDemo.demo();
        
        // Proxy Pattern
        ProxyDemo.demo();
        
        System.out.println("----- END OF STRUCTURAL PATTERNS -----\n");
    }
}