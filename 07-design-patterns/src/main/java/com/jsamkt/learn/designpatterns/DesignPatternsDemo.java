package com.jsamkt.learn.designpatterns;

import com.jsamkt.learn.designpatterns.creational.CreationalPatternsDemo;
import com.jsamkt.learn.designpatterns.structural.StructuralPatternsDemo;
import com.jsamkt.learn.designpatterns.behavioral.BehavioralPatternsDemo;

/**
 * Main demonstration class for design patterns.
 * Design patterns are typical solutions to common problems in software design.
 * They are categorized into three groups: Creational, Structural, and Behavioral.
 */
public class DesignPatternsDemo {
    
    public static void main(String[] args) {
        System.out.println("===== DESIGN PATTERNS DEMONSTRATION =====");
        
        // Demonstrate creational design patterns
        CreationalPatternsDemo.demo();
        
        // Demonstrate structural design patterns
        StructuralPatternsDemo.demo();
        
        // Demonstrate behavioral design patterns
        BehavioralPatternsDemo.demo();
        
        System.out.println("===== END OF DESIGN PATTERNS DEMONSTRATION =====");
    }
}