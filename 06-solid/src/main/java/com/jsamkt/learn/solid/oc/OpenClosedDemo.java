package com.jsamkt.learn.solid.oc;

import com.jsamkt.learn.solid.oc.bad.CircleBad;
import com.jsamkt.learn.solid.oc.bad.RectangleBad;
import com.jsamkt.learn.solid.oc.bad.ShapeBad;
import com.jsamkt.learn.solid.oc.good.*;

import java.util.ArrayList;
import java.util.List;

public class OpenClosedDemo {

    public static void demo(){
        demonstrateOpenClosed();
    }

    /**
     * Open/Closed Principle: Software entities should be open for extension
     * but closed for modification.
     */
    private static void demonstrateOpenClosed() {
        System.out.println("\n--- Open/Closed Principle ---");

        demonstrateOpenClosedBad();
        demonstrateOpenClosedGood();

        // Benefit: can add new shapes without modifying existing code
        System.out.println("\nBenefits of OCP:");
        System.out.println("1. Easier to extend functionality");
        System.out.println("2. Minimizes the risk of breaking existing code");
        System.out.println("3. Promotes loose coupling");
    }

    private static void demonstrateOpenClosedBad(){
        // Bad example - violates OCP
        System.out.println("Bad example (violates OCP):");

        List<ShapeBad> shapesBad = new ArrayList<>();
        shapesBad.add(new CircleBad(5.0));
        shapesBad.add(new RectangleBad(4.0, 6.0));

        double totalAreaBad = 0;
        for (ShapeBad shape : shapesBad) {
            if (shape instanceof CircleBad) {
                CircleBad circle = (CircleBad) shape;
                totalAreaBad += Math.PI * circle.getRadius() * circle.getRadius();
            } else if (shape instanceof RectangleBad) {
                RectangleBad rectangle = (RectangleBad) shape;
                totalAreaBad += rectangle.getWidth() * rectangle.getHeight();
            }
            // Adding a new shape requires modifying this code!
        }
        System.out.println("Total area: " + totalAreaBad);
    }

    private static void demonstrateOpenClosedGood(){
        // Good example - follows OCP
        System.out.println("\nGood example (follows OCP):");

        List<ShapeGood> shapesOCP = new ArrayList<>();
        shapesOCP.add(new CircleGood(5.0));
        shapesOCP.add(new RectangleGood(4.0, 6.0));
        shapesOCP.add(new TriangleGood(3.0, 4.0)); // Can add new shapes without changing AreaCalculator

        AreaCalculator calculator = new AreaCalculator();
        double totalAreaOCP = calculator.calculateTotalArea(shapesOCP);
        System.out.println("Total area: " + totalAreaOCP);
    }

}
