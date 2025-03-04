package com.jsamkt.learn.oop.abstraction;

public class Airplane implements Flyable {
    private String model;

    public Airplane(String model) {
        this.model = model;
    }

    @Override
    public void fly() {
        System.out.println("Airplane " + model + " flying by jet propulsion");
    }

    @Override
    public void land() {
        System.out.println("Airplane landing on runway");
    }
}