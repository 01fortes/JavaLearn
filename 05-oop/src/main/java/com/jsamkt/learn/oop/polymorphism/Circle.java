package com.jsamkt.learn.oop.polymorphism;

public class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getDescription() {
        return "A circle with radius: " + radius;
    }

    @Override
    public String toString() {
        return "Circle[name=" + getName() + ", radius=" + radius + "]";
    }
}