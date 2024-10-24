package com.weather;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class WeatherDataProcessor {
    private Map<String, DailyWeatherSummary> dailySummaries;

    public WeatherDataProcessor() {
        dailySummaries = new HashMap<>();
    }

    public void processWeatherData(String jsonData, String city) {
        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        double tempKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        double tempCelsius = kelvinToCelsius(tempKelvin);
        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

        DailyWeatherSummary summary = dailySummaries.computeIfAbsent(city, k -> new DailyWeatherSummary(city));
        summary.addTemperature(tempCelsius, weatherCondition);
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public void printDailySummaries() {
        for (DailyWeatherSummary summary : dailySummaries.values()) {
            System.out.println(summary);
        }
    }
}
