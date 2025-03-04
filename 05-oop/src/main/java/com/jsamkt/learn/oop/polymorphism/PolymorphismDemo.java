package com.jsamkt.learn.oop.polymorphism;


public class PolymorphismDemo {

    public static void demo() {
        demonstratePolymorphism();
    }

    /**
     * Polymorphism: The ability to present the same interface for different underlying
     * forms (data types) allowing for different implementations behind a common interface.
     */

    private static void demonstratePolymorphism() {
        System.out.println("\n--- Polymorphism ---");

        // Create an array of Shape references
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle("Polymorphic Circle", 3.0);
        shapes[1] = new Rectangle("Polymorphic Rectangle", 4.0, 5.0);
        shapes[2] = new Square("Polymorphic Square", 2.0);

        // Polymorphic behavior - same method call but different implementation
        System.out.println("Calculating areas polymorphically:");
        for (Shape shape : shapes) {
            System.out.println(shape.getName() + ": " + shape.calculateArea());
        }

        // Runtime polymorphism with method overriding
        System.out.println("\nMethod descriptions polymorphically:");
        for (Shape shape : shapes) {
            System.out.println(shape.getName() + ": " + shape.getDescription());
        }

        // instanceof operator and casting
        System.out.println("\nUsing instanceof and casting:");
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                System.out.println("Circle radius: " + circle.getRadius());
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                System.out.println("Rectangle dimensions: " + rectangle.getWidth() + "x" + rectangle.getHeight());
            }
        }
    }
}
