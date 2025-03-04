package com.jsamkt.learn.oop.abstraction;



public class AbstractionDemo {

    public static void demo(){
        demonstrateAbstraction();
    }

    /**
     * Abstraction: Hiding the complex implementation details and showing only the necessary
     * features of an object, reducing complexity and increasing efficiency.
     */
    private static void demonstrateAbstraction() {
        System.out.println("\n--- Abstraction ---");

        // Create instances of concrete subclasses
        Vehicle car = new Car("Toyota Camry");
        Vehicle motorcycle = new Motorcycle("Harley Davidson");

        // Demonstrate abstraction through abstract class methods
        System.out.println(car.getDescription());
        car.start();
        car.accelerate();
        car.brake();
        car.stop();

        System.out.println();

        System.out.println(motorcycle.getDescription());
        motorcycle.start();
        motorcycle.accelerate();
        motorcycle.brake();
        motorcycle.stop();

        // Cannot instantiate an abstract class
        // Vehicle v = new Vehicle("Generic"); // Compilation error

        // Interface example
        System.out.println("\nInterface abstraction:");
        Flyable plane = new Airplane("Boeing 737");
        Flyable bird = new Bird("Eagle");

        plane.fly();
        plane.land();

        bird.fly();
        bird.land();

        // Multiple interfaces
        System.out.println("\nMultiple interfaces:");
        Drone drone = new Drone("DJI Mavic");
        drone.fly();
        drone.land();
        drone.takePhoto();
        drone.recordVideo();
    }

}
