package com.jsamkt.learn.oop.abstraction;

abstract public class Vehicle {

    private String model;

    public Vehicle(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public abstract void start();
    public abstract void accelerate();
    public abstract void brake();
    public abstract void stop();

    public String getDescription() {
        return "Vehicle: " + model;
    }
}
