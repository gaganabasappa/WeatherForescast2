package com.weather;


import java.util.HashMap;
import java.util.Map;

public class DailyWeatherSummary {
    private String city;
    private double totalTemp;
    private double maxTemp;
    private double minTemp;
    private int count;
    private Map<String, Integer> weatherCount;

    public DailyWeatherSummary(String city) {
        this.city = city;
        this.totalTemp = 0;
        this.maxTemp = Double.MIN_VALUE;
        this.minTemp = Double.MAX_VALUE;
        this.count = 0;
        this.weatherCount = new HashMap<>();
    }

    public void addTemperature(double temp, String weatherCondition) {
        totalTemp += temp;
        maxTemp = Math.max(maxTemp, temp);
        minTemp = Math.min(minTemp, temp);
        count++;
        
        weatherCount.put(weatherCondition, weatherCount.getOrDefault(weatherCondition, 0) + 1);
    }

    @Override
    public String toString() {
        double avgTemp = totalTemp / count;
        String dominantCondition = weatherCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        return String.format("Daily Summary for %s: Avg Temp: %.2f, Max Temp: %.2f, Min Temp: %.2f, Dominant Condition: %s",
                city, avgTemp, maxTemp, minTemp, dominantCondition);
    }
}
