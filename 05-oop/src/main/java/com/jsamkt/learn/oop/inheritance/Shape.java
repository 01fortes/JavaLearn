package com.jsamkt.learn.oop.inheritance;

public class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculateArea() {
        return 0.0; // Default implementation
    }

    public String getDescription() {
        return "A shape with name: " + name;
    }

    @Override
    public String toString() {
        return "Shape[name=" + name + "]";
    }
}
