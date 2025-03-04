package com.jsamkt.learn.solid.oc.good;

public class CircleGood implements ShapeGood {
    private double radius;

    public CircleGood(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}