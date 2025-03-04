package com.jsamkt.learn.oop.inheritance;

public class InheritanceDemo {

    public static void demo() {
        demonstrateInheritance();
    }

    /**
     * Inheritance: The mechanism of basing a class upon another class, retaining similar
     * implementation and allowing for extension of functionality.
     */
    private static void demonstrateInheritance() {
        System.out.println("\n--- Inheritance ---");

        // Create base class instance
        Shape shape = new Shape("Generic Shape");
        System.out.println(shape);

        // Create instances of derived classes
        Circle circle = new Circle("My Circle", 5.0);
        Rectangle rectangle = new Rectangle("My Rectangle", 4.0, 6.0);

        System.out.println(circle);
        System.out.println("Circle area: " + circle.calculateArea());

        System.out.println(rectangle);
        System.out.println("Rectangle area: " + rectangle.calculateArea());

        // Method overriding
        System.out.println("\nMethod overriding:");
        System.out.println("Shape description: " + shape.getDescription());
        System.out.println("Circle description: " + circle.getDescription());
        System.out.println("Rectangle description: " + rectangle.getDescription());

        // Constructors in inheritance
        System.out.println("\nConstructors in inheritance:");
        Square square = new Square("My Square", 5.0);
        System.out.println(square);
        System.out.println("Square area: " + square.calculateArea());
    }
}
