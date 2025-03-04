package com.jsamkt.learn.oop.abstraction;

public class Bird implements Flyable {
    private String species;

    public Bird(String species) {
        this.species = species;
    }

    @Override
    public void fly() {
        System.out.println(species + " flying by flapping wings");
    }

    @Override
    public void land() {
        System.out.println(species + " landing on a branch");
    }
}