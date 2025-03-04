package com.jsamkt.learn.advancedmath.complexnumbers;

import org.apache.commons.math3.complex.Complex;

/**
 * Demonstrates the use of complex numbers in Java.
 * This demo shows both a custom implementation of complex numbers
 * and the use of Apache Commons Math library for complex number operations.
 */
public class ComplexNumberDemo {
    
    public static void demo() {
        System.out.println("\n----- COMPLEX NUMBERS -----");
        
        // Demonstrate custom complex number implementation
        customComplexNumberDemo();
        
        // Demonstrate Apache Commons Math complex number implementation
        apacheCommonsComplexDemo();
        
        // Demonstrate applications of complex numbers
        complexNumberApplicationsDemo();
        
        System.out.println("----- END OF COMPLEX NUMBERS -----\n");
    }
    
    private static void customComplexNumberDemo() {
        System.out.println("\n# Custom Complex Number Implementation");
        
        // Create complex numbers
        ComplexNumber z1 = new ComplexNumber(3, 4);
        ComplexNumber z2 = new ComplexNumber(1, -2);
        
        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);
        
        // Basic operations
        System.out.println("\nBasic operations:");
        System.out.println("z1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        System.out.println("z1 / z2 = " + z1.divide(z2));
        
        // Properties
        System.out.println("\nProperties of z1:");
        System.out.println("Modulus (|z1|) = " + z1.modulus());
        System.out.println("Argument (in radians) = " + z1.argument());
        System.out.println("Argument (in degrees) = " + Math.toDegrees(z1.argument()));
        System.out.println("Complex conjugate = " + z1.conjugate());
        System.out.println("Reciprocal = " + z1.reciprocal());
    }
    
    private static void apacheCommonsComplexDemo() {
        System.out.println("\n# Apache Commons Math Complex Number");
        
        // Create complex numbers
        Complex z1 = new Complex(3, 4);
        Complex z2 = new Complex(1, -2);
        
        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);
        
        // Basic operations
        System.out.println("\nBasic operations:");
        System.out.println("z1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        System.out.println("z1 / z2 = " + z1.divide(z2));
        
        // Properties
        System.out.println("\nProperties of z1:");
        System.out.println("Modulus (|z1|) = " + z1.abs());
        System.out.println("Argument (in radians) = " + z1.getArgument());
        System.out.println("Argument (in degrees) = " + Math.toDegrees(z1.getArgument()));
        System.out.println("Complex conjugate = " + z1.conjugate());
        System.out.println("Reciprocal = " + z1.reciprocal());
        
        // Advanced functions
        System.out.println("\nAdvanced functions:");
        System.out.println("e^z1 = " + z1.exp());
        System.out.println("ln(z1) = " + z1.log());
        System.out.println("sqrt(z1) = " + z1.sqrt());
        System.out.println("sin(z1) = " + z1.sin());
        System.out.println("cos(z1) = " + z1.cos());
    }
    
    private static void complexNumberApplicationsDemo() {
        System.out.println("\n# Applications of Complex Numbers");
        
        // 1. Solving quadratic equations with complex roots
        System.out.println("\nSolving the quadratic equation: x² + 2x + 5 = 0");
        
        // For ax² + bx + c = 0, the roots are (-b ± √(b² - 4ac)) / 2a
        double a = 1;
        double b = 2;
        double c = 5;
        
        double discriminant = b*b - 4*a*c;
        
        if (discriminant >= 0) {
            // Real roots
            double x1 = (-b + Math.sqrt(discriminant)) / (2*a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2*a);
            System.out.println("Root 1: " + x1);
            System.out.println("Root 2: " + x2);
        } else {
            // Complex roots
            double realPart = -b / (2*a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2*a);
            
            ComplexNumber root1 = new ComplexNumber(realPart, imaginaryPart);
            ComplexNumber root2 = new ComplexNumber(realPart, -imaginaryPart);
            
            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
            
            // Verify roots by substituting back into equation
            System.out.println("\nVerification:");
            
            // For root1: (root1)² + 2(root1) + 5 should be approximately 0
            ComplexNumber root1Squared = root1.multiply(root1);
            ComplexNumber twoTimesRoot1 = root1.multiply(new ComplexNumber(2, 0));
            ComplexNumber result1 = root1Squared.add(twoTimesRoot1).add(new ComplexNumber(5, 0));
            
            System.out.println("Substituting root 1 back into equation: " + result1);
            
            // For root2: (root2)² + 2(root2) + 5 should be approximately 0
            ComplexNumber root2Squared = root2.multiply(root2);
            ComplexNumber twoTimesRoot2 = root2.multiply(new ComplexNumber(2, 0));
            ComplexNumber result2 = root2Squared.add(twoTimesRoot2).add(new ComplexNumber(5, 0));
            
            System.out.println("Substituting root 2 back into equation: " + result2);
        }
        
        // 2. Complex number in polar form
        System.out.println("\nPolar form example:");
        ComplexNumber z = new ComplexNumber(3, 4);
        double r = z.modulus();
        double theta = z.argument();
        
        System.out.println("z = " + z + " in Cartesian form");
        System.out.println("z = " + r + " * e^(" + theta + "i) in Exponential form");
        System.out.println("z = " + r + " * (cos(" + theta + ") + i*sin(" + theta + ")) in Polar form");
        
        // Demonstrate De Moivre's formula: (r∠θ)^n = r^n ∠ nθ
        int n = 3;
        System.out.println("\nDe Moivre's formula: z^" + n);
        double resultR = Math.pow(r, n);
        double resultTheta = n * theta;
        
        // Using De Moivre's formula
        System.out.println("Using De Moivre's formula:");
        System.out.println("z^" + n + " = " + resultR + " * (cos(" + resultTheta + ") + i*sin(" + resultTheta + "))");
        double resultReal = resultR * Math.cos(resultTheta);
        double resultImag = resultR * Math.sin(resultTheta);
        System.out.println("z^" + n + " = " + new ComplexNumber(resultReal, resultImag));
        
        // Directly calculating z^n by repeated multiplication
        ComplexNumber result = z;
        for (int i = 1; i < n; i++) {
            result = result.multiply(z);
        }
        System.out.println("By direct calculation: z^" + n + " = " + result);
    }
}