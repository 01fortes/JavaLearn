package com.jsamkt.learn.reactive.basics;

/**
 * Interface for observers of the weather station.
 * Implements the Observer part of the Observer pattern.
 */
public interface WeatherObserver {
    
    /**
     * Called when the observed temperature changes.
     *
     * @param temperature The new temperature
     */
    void update(double temperature);
}