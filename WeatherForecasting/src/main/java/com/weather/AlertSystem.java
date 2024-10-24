package com.weather;
public class AlertSystem {
    private double temperatureThreshold;

    public AlertSystem(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

    public void checkAlert(double currentTemp) {
        if (currentTemp > temperatureThreshold) {
            System.out.println("Alert! Temperature exceeded threshold: " + currentTemp);
        }
    }
}
