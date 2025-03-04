package com.jsamkt.learn.oop.abstraction;

import com.jsamkt.learn.oop.solid.Photographer;

public class Drone  implements Flyable, Photographer {
    private String model;

    public Drone(String model) {
        this.model = model;
    }

    @Override
    public void fly() {
        System.out.println("Drone " + model + " flying with propellers");
    }

    @Override
    public void land() {
        System.out.println("Drone landing on ground");
    }

    @Override
    public void takePhoto() {
        System.out.println("Drone taking aerial photo");
    }

    @Override
    public void recordVideo() {
        System.out.println("Drone recording aerial video");
    }
}