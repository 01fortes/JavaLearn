package com.jsamkt.learn.oop.abstraction;

public class Motorcycle extends Vehicle{

    public Motorcycle(String model) {
        super(model);
    }

    @Override
    public void start() {
        System.out.println("Motorcycle " + getModel() + " starting: Press starter button");
    }

    @Override
    public void accelerate() {
        System.out.println("Motorcycle accelerating: Twist throttle");
    }

    @Override
    public void brake() {
        System.out.println("Motorcycle braking: Squeeze brake lever");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle stopping: Turn off key");
    }

    @Override
    public String getDescription() {
        return "Motorcycle: " + getModel();
    }
}
