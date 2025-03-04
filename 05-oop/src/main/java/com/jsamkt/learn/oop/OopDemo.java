package com.jsamkt.learn.oop;

import com.jsamkt.learn.oop.abstraction.AbstractionDemo;
import com.jsamkt.learn.oop.encapsulation.EncapsulationDemo;
import com.jsamkt.learn.oop.inheritance.InheritanceDemo;
import com.jsamkt.learn.oop.polymorphism.PolymorphismDemo;

/**
 * This tutorial demonstrates Object-Oriented Programming principles in Java.
 */
public class OopDemo {
    
    public static void main(String[] args) {
        System.out.println("===== Java OOP Tutorial =====");
        
        // Demonstrate OOP principles
        EncapsulationDemo.demo();
        InheritanceDemo.demo();
        PolymorphismDemo.demo();
        AbstractionDemo.demo();
    }
}