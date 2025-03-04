package com.jsamkt.learn.oop.abstraction;

public class Car extends Vehicle {

    public Car(String model) {
        super(model);
    }

    @Override
    public void start() {
        System.out.println("Car " + getModel() + " starting: Insert key and turn ignition");
    }

    @Override
    public void accelerate() {
        System.out.println("Car accelerating: Press gas pedal");
    }

    @Override
    public void brake() {
        System.out.println("Car braking: Press brake pedal");
    }

    @Override
    public void stop() {
        System.out.println("Car stopping: Turn off ignition");
    }

    @Override
    public String getDescription() {
        return "Car: " + getModel();
    }
}
