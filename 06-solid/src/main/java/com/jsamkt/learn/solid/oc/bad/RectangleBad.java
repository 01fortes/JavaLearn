package com.jsamkt.learn.solid.oc.bad;

public class RectangleBad extends ShapeBad {
    private double width;
    private double height;

    public RectangleBad(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}