package com.jsamkt.learn.solid.oc.good;

public class RectangleGood implements ShapeGood {
    private double width;
    private double height;

    public RectangleGood(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}