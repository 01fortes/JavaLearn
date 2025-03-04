package com.jsamkt.learn.solid.lsp;

import com.jsamkt.learn.solid.lsp.bad.RectangleBad;
import com.jsamkt.learn.solid.lsp.bad.SquareBad;
import com.jsamkt.learn.solid.lsp.good.RectangleGood;
import com.jsamkt.learn.solid.lsp.good.ShapeGood;
import com.jsamkt.learn.solid.lsp.good.SquareGood;

public class LSPDemo {

    public static void demo(){
        demonstrateLiskovSubstitution();
    }

    /**
     * Liskov Substitution Principle: Subtypes must be substitutable for their base types
     * without altering the correctness of the program.
     */
    private static void demonstrateLiskovSubstitution() {
        System.out.println("\n--- Liskov Substitution Principle ---");

        demonstrateLiskovSubstitutionBad();
        demonstrateLiskovSubstitutionGood();

        // Benefits of following LSP
        System.out.println("\nBenefits of LSP:");
        System.out.println("1. Ensures behavior consistency");
        System.out.println("2. Allows for safe polymorphic substitution");
        System.out.println("3. Prevents unexpected bugs in derived classes");
    }

    private static void demonstrateLiskovSubstitutionBad(){
        // Bad example - violates LSP
        System.out.println("Bad example (violates LSP):");

        RectangleBad rectangle = new RectangleBad(5, 10);
        System.out.println("Rectangle width: " + rectangle.getWidth() + ", height: " +
                rectangle.getHeight() + ", area: " + rectangle.calculateArea());

        // SquareLSP extends RectangleLSP but breaks its behavior
        SquareBad square = new SquareBad(5);
        System.out.println("Square width: " + square.getWidth() + ", height: " +
                square.getHeight() + ", area: " + square.calculateArea());

        System.out.println("\nModifying width to demonstrate LSP violation:");
        modifyRectangleWidth(rectangle);
        modifyRectangleWidth(square); // This behaves differently than expected!
    }

    private static void demonstrateLiskovSubstitutionGood(){
        // Good example - follows LSP
        System.out.println("\nGood example (follows LSP):");

        ShapeGood rectangleShape = new RectangleGood(5, 10);
        ShapeGood squareShape = new SquareGood(5);

        System.out.println("Rectangle area: " + rectangleShape.calculateArea());
        System.out.println("Square area: " + squareShape.calculateArea());

    }

    // Helper method to demonstrate LSP violation
    private static void modifyRectangleWidth(RectangleBad rectangle) {
        int originalHeight = rectangle.getHeight();
        rectangle.setWidth(10);
        System.out.println("Width set to 10, height should still be " + originalHeight);
        System.out.println("Width: " + rectangle.getWidth() + ", Height: " + rectangle.getHeight());
        System.out.println("Area: " + rectangle.calculateArea());
    }
}
