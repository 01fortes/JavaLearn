package com.jsamkt.learn.solid.oc.good;

import java.util.List;

public class AreaCalculator {
    public double calculateTotalArea(List<ShapeGood> shapes) {
        double total = 0;
        for (ShapeGood shape : shapes) {
            total += shape.calculateArea();
        }
        return total;
    }
}
