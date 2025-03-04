package com.jsamkt.learn.advancedmath;

import com.jsamkt.learn.advancedmath.linearalgebra.LinearAlgebraDemo;
import com.jsamkt.learn.advancedmath.calculus.CalculusDemo;
import com.jsamkt.learn.advancedmath.statistics.StatisticsDemo;
import com.jsamkt.learn.advancedmath.optimization.OptimizationDemo;
import com.jsamkt.learn.advancedmath.probability.ProbabilityDemo;
import com.jsamkt.learn.advancedmath.complexnumbers.ComplexNumberDemo;
import com.jsamkt.learn.advancedmath.bigdecimal.BigDecimalDemo;

/**
 * Main demonstration class for advanced mathematics in Java.
 * This module covers various mathematical concepts and their implementation
 * using both standard Java libraries and specialized math libraries.
 */
public class AdvancedMathDemo {
    
    public static void main(String[] args) {
        System.out.println("===== ADVANCED MATHEMATICS DEMONSTRATION =====");
        
        // Demonstrate linear algebra
        LinearAlgebraDemo.demo();
        
        // Demonstrate calculus
        CalculusDemo.demo();
        
        // Demonstrate statistics
        StatisticsDemo.demo();
        
        // Demonstrate optimization
        OptimizationDemo.demo();
        
        // Demonstrate probability
        ProbabilityDemo.demo();
        
        // Demonstrate complex numbers
        ComplexNumberDemo.demo();
        
        // Demonstrate BigDecimal operations
        BigDecimalDemo.demo();
        
        System.out.println("===== END OF ADVANCED MATHEMATICS DEMONSTRATION =====");
    }
}