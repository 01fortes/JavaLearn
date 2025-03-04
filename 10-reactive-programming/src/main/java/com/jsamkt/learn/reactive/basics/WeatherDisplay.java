package com.jsamkt.learn.reactive.basics;

/**
 * A concrete implementation of the WeatherObserver interface.
 * Displays the current temperature when notified of changes.
 */
public class WeatherDisplay implements WeatherObserver {
    private final String displayName;
    
    /**
     * Creates a new WeatherDisplay with the specified name.
     *
     * @param displayName The name of this display
     */
    public WeatherDisplay(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Updates the display with the new temperature.
     *
     * @param temperature The new temperature
     */
    @Override
    public void update(double temperature) {
        System.out.printf("%s: Temperature updated to %.1f°C%n", displayName, temperature);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}