package com.jsamkt.learn.oop.inheritance;

public class Square extends Rectangle {
    public Square(String name, double side) {
        super(name, side, side);
    }

    @Override
    public String getDescription() {
        return "A square with side: " + getWidth();
    }

    @Override
    public String toString() {
        return "Square[name=" + getName() + ", side=" + getWidth() + "]";
    }
}