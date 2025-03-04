package com.jsamkt.learn.reactive.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the Observer pattern.
 * The WeatherStation acts as the Subject (Observable) that notifies
 * registered observers when the temperature changes.
 */
public class WeatherStation {
    private double temperature;
    private final List<WeatherObserver> observers = new ArrayList<>();
    
    /**
     * Registers an observer to receive temperature updates.
     *
     * @param observer The observer to register
     */
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
        System.out.println("Observer added: " + observer);
    }
    
    /**
     * Removes an observer from receiving temperature updates.
     *
     * @param observer The observer to remove
     */
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer);
    }
    
    /**
     * Notifies all registered observers of the current temperature.
     */
    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }
    
    /**
     * Sets a new temperature value and notifies all observers.
     *
     * @param temperature The new temperature value
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
    
    /**
     * Returns the current temperature.
     *
     * @return The current temperature
     */
    public double getTemperature() {
        return temperature;
    }
}