package com.weather;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class WeatherMonitor {
    private static final String API_KEY = "your_api_key_here";
    private static final String[] CITIES = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    private WeatherDataProcessor dataProcessor;
    private Timer timer;

    public WeatherMonitor() {
        dataProcessor = new WeatherDataProcessor();
        timer = new Timer();
    }

    public void startMonitoring() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (String city : CITIES) {
                    fetchWeatherData(city);
                }
            }
        }, 0, 300000); // Fetch every 5 minutes
    }

    private void fetchWeatherData(String city) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("%s?q=%s&appid=%s", BASE_URL, city, API_KEY);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                dataProcessor.processWeatherData(jsonData, city);
            } else {
                System.out.println("Failed to fetch data for " + city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WeatherMonitor monitor = new WeatherMonitor();
        monitor.startMonitoring();
    }
}
