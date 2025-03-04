package com.jsamkt.learn.advancedmath.calculus;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * Demonstrates calculus concepts and operations using Java libraries.
 * Calculus is a branch of mathematics that deals with derivatives, integrals,
 * limits, and infinite series.
 */
public class CalculusDemo {
    
    public static void demo() {
        System.out.println("\n----- CALCULUS -----");
        
        // Demonstrate numerical differentiation
        differentiationDemo();
        
        // Demonstrate numerical integration
        integrationDemo();
        
        // Demonstrate root finding
        rootFindingDemo();
        
        // Demonstrate interpolation
        interpolationDemo();
        
        System.out.println("----- END OF CALCULUS -----\n");
    }
    
    private static void differentiationDemo() {
        System.out.println("\n# Numerical Differentiation");
        
        // Define a function: f(x) = x^3 - 2x^2 + 3x - 1
        PolynomialFunction f = new PolynomialFunction(new double[] {-1, 3, -2, 1});
        
        System.out.println("Function: f(x) = x³ - 2x² + 3x - 1");
        
        // Compute the derivative at different points using finite differences
        FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(5, 0.01);
        UnivariateFunction derivative = differentiator.differentiate(f);
        
        // The theoretical derivative is f'(x) = 3x^2 - 4x + 3
        System.out.println("\nTheoretical derivative: f'(x) = 3x² - 4x + 3");
        
        System.out.println("\nNumerical derivatives at various points:");
        double[] points = {-2, -1, 0, 1, 2, 3};
        for (double x : points) {
            double exactDerivative = 3 * x * x - 4 * x + 3;
            double numericalDerivative = derivative.value(x);
            System.out.printf("f'(%.1f) ≈ %.4f (exact: %.4f, error: %.4e)%n", 
                    x, numericalDerivative, exactDerivative, 
                    Math.abs(numericalDerivative - exactDerivative));
        }
        
        // Using DerivativeStructure for higher-order derivatives
        System.out.println("\nHigher-order derivatives using DerivativeStructure:");
        
        // Compute up to 3rd derivative at x = 2
        double x = 2.0;
        int params = 1; // Number of parameters (just x in this case)
        int order = 3;  // Maximum derivation order
        
        // Create a DerivativeStructure for the point x = 2
        DerivativeStructure xDS = new DerivativeStructure(params, order, 0, x);
        
        // Compute the function value and derivatives
        DerivativeStructure resultDS = xDS.pow(3).subtract(xDS.pow(2).multiply(2))
                .add(xDS.multiply(3)).subtract(1);
        
        System.out.println("Evaluating derivatives at x = " + x);
        System.out.printf("f(%.1f) = %.4f%n", x, resultDS.getValue());
        System.out.printf("f'(%.1f) = %.4f%n", x, resultDS.getPartialDerivative(1));
        System.out.printf("f''(%.1f) = %.4f%n", x, resultDS.getPartialDerivative(2));
        System.out.printf("f'''(%.1f) = %.4f%n", x, resultDS.getPartialDerivative(3));
    }
    
    private static void integrationDemo() {
        System.out.println("\n# Numerical Integration");
        
        // Define a function: f(x) = x^2 + 2x + 1
        PolynomialFunction f = new PolynomialFunction(new double[] {1, 2, 1});
        
        System.out.println("Function: f(x) = x² + 2x + 1");
        System.out.println("Computing the definite integral of f(x) from a to b");
        
        // Define integration bounds
        double a = 0;
        double b = 2;
        
        // Exact integral
        // Antiderivative of x^2 + 2x + 1 is (x^3)/3 + x^2 + x
        double exactIntegral = (Math.pow(b, 3) / 3 + Math.pow(b, 2) + b) - 
                               (Math.pow(a, 3) / 3 + Math.pow(a, 2) + a);
        
        System.out.printf("Integration bounds: [%.1f, %.1f]%n", a, b);
        System.out.printf("Exact integral: %.4f%n", exactIntegral);
        
        // Manual implementation of the trapezoidal rule
        System.out.println("\nManual implementation of trapezoidal rule:");
        int n = 1000; // Number of intervals
        double h = (b - a) / n; // Width of each interval
        
        double sum = 0.5 * (f.value(a) + f.value(b)); // First and last terms
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f.value(x);
        }
        
        double trapezoidResult = h * sum;
        System.out.printf("Trapezoidal rule (n=%d): %.4f (error: %.4e)%n", 
                n, trapezoidResult, Math.abs(trapezoidResult - exactIntegral));
        
        // Manual implementation of Simpson's rule
        System.out.println("\nManual implementation of Simpson's rule:");
        n = 1000; // Number of intervals (must be even)
        h = (b - a) / n;
        
        double sumEven = 0;
        double sumOdd = 0;
        
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sumEven += f.value(x);
            } else {
                sumOdd += f.value(x);
            }
        }
        
        double simpsonResult = (h / 3) * (f.value(a) + f.value(b) + 4 * sumOdd + 2 * sumEven);
        System.out.printf("Simpson's rule (n=%d): %.4f (error: %.4e)%n", 
                n, simpsonResult, Math.abs(simpsonResult - exactIntegral));
        
        // Example: Computing the area under a curve that doesn't have a simple antiderivative
        System.out.println("\nIntegrating a function without a simple antiderivative:");
        UnivariateFunction g = x -> Math.sin(x * x);
        System.out.println("Function: g(x) = sin(x²)");
        
        // Integrate sin(x^2) from 0 to pi using Simpson's rule
        a = 0;
        b = Math.PI;
        n = 1000;
        h = (b - a) / n;
        
        sumEven = 0;
        sumOdd = 0;
        
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sumEven += g.value(x);
            } else {
                sumOdd += g.value(x);
            }
        }
        
        double gResult = (h / 3) * (g.value(a) + g.value(b) + 4 * sumOdd + 2 * sumEven);
        System.out.printf("Approximate value of ∫sin(x²)dx from %.1f to π ≈ %.8f%n", a, gResult);
    }
    
    private static void rootFindingDemo() {
        System.out.println("\n# Root Finding Methods");
        
        // Define a function: f(x) = x^3 - 2x^2 - 5x + 6
        PolynomialFunction f = new PolynomialFunction(new double[] {6, -5, -2, 1});
        
        System.out.println("Function: f(x) = x³ - 2x² - 5x + 6");
        System.out.println("Finding roots of the function (values of x where f(x) = 0)");
        
        // Bisection method
        BisectionSolver bisectionSolver = new BisectionSolver();
        
        try {
            // Try to find a root in the interval [-5, 0]
            double root1 = bisectionSolver.solve(100, f, -5, 0);
            System.out.printf("Root found using bisection in [-5, 0]: %.8f, f(x) = %.8e%n", 
                    root1, f.value(root1));
            
            // Try to find a root in the interval [0, 5]
            double root2 = bisectionSolver.solve(100, f, 0, 5);
            System.out.printf("Root found using bisection in [0, 5]: %.8f, f(x) = %.8e%n", 
                    root2, f.value(root2));
            
            // Try to find another root
            double root3 = bisectionSolver.solve(100, f, -1, 3);
            System.out.printf("Root found using bisection in [-1, 3]: %.8f, f(x) = %.8e%n", 
                    root3, f.value(root3));
        } catch (TooManyEvaluationsException e) {
            System.out.println("Root finding exceeded maximum iterations.");
        }
        
        // Analytical solution for cubic equation x^3 - 2x^2 - 5x + 6 = 0
        System.out.println("\nThese roots correspond to the factors of the polynomial:");
        System.out.println("x³ - 2x² - 5x + 6 = (x - 3)(x - 1)(x + 2)");
        System.out.println("So the exact roots are x = 3, x = 1, and x = -2");
    }
    
    private static void interpolationDemo() {
        System.out.println("\n# Function Interpolation");
        
        // Define some data points
        double[] x = {0, 1, 2, 3, 4, 5};
        double[] y = {1, 3, 6, 8, 7, 9};
        
        System.out.println("Data points (x, y):");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("(%.1f, %.1f) ", x[i], y[i]);
        }
        System.out.println();
        
        // Create a cubic spline interpolation function
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction spline = interpolator.interpolate(x, y);
        
        System.out.println("\nCubic spline interpolation:");
        
        // Evaluate the spline at various points
        double[] testPoints = {0.5, 1.5, 2.5, 3.5, 4.5};
        for (double t : testPoints) {
            double interpolatedValue = spline.value(t);
            System.out.printf("f(%.1f) ≈ %.4f%n", t, interpolatedValue);
        }
        
        // Using interpolation to approximate a derivative at a point
        System.out.println("\nApproximating the derivative at x = 2.5 using interpolation:");
        
        // Get the spline polynomials
        double derivativeAt2_5 = spline.derivative().value(2.5);
        System.out.printf("Approximated derivative at x = 2.5: %.4f%n", derivativeAt2_5);
    }
}