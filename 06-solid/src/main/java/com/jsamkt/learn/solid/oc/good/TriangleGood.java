package com.jsamkt.learn.solid.oc.good;

public class TriangleGood implements ShapeGood {
    private double base;
    private double height;

    public TriangleGood(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}