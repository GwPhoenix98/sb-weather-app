package pixel.academy.weather.app.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pixel.academy.weather.app.exception.WeatherException;
import pixel.academy.weather.app.model.WeatherResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherResponse getWeather(String cityName) {
        validateApiKey();

        String encodedCity = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
        String urlString = String.format("%s?q=%s&units=metric&appid=%s",
                BASE_URL, encodedCity, apiKey);

        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new WeatherException(
                        "Failed to fetch weather data. HTTP code: " + responseCode
                );
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return parseWeatherResponse(cityName, response.toString());
            }

        } catch (IOException e) {
            throw new WeatherException("Network error: " + e.getMessage());
        } catch (Exception e) {
            throw new WeatherException("Error processing weather data: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private void validateApiKey() {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new WeatherException(
                    "API KEY missing! Please set weather.api.key in aplicatin.propreties"
            );
        }
    }

    private WeatherResponse parseWeatherResponse(String cityName, String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);

            double temp = json.getJSONObject("main").getDouble("temp");
            String description = json.getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");

            return new WeatherResponse(cityName, temp, description);
        } catch (Exception e) {
            throw new WeatherException("Failed to parse weather data: " + e.getMessage());
        }
    }
}
