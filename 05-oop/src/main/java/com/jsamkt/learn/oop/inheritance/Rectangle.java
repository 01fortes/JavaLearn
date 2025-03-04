package com.jsamkt.learn.oop.inheritance;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public String getDescription() {
        return "A rectangle with width: " + width + " and height: " + height;
    }

    @Override
    public String toString() {
        return "Rectangle[name=" + getName() + ", width=" + width + ", height=" + height + "]";
    }
}