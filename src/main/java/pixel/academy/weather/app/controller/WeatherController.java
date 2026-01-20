package pixel.academy.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.WeatherResponse;
import pixel.academy.weather.app.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WeatherResponse getWeatherJson(@PathVariable String cityName) {
        City city = City.fromName(cityName);
        return weatherService.getWeather(city.getName());
    }

    @GetMapping(value = "/{cityName}", produces = MediaType.TEXT_HTML_VALUE)
    public String getWeatherHtml(@PathVariable String cityName) {
        City city = City.fromName(cityName);
        WeatherResponse weather = weatherService.getWeather(city.getName());

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Weather - %s</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            max-width: 600px;
                            margin: 50px auto;
                            padding: 20px;
                            text-align: center;
                        }
                        h1 { color: #333; }
                        .weather {
                            background: #f0f8ff;
                            padding: 30px;
                            border-radius: 10px;
                            margin: 20px 0;
                        }
                        .temp {
                            font-size: 3em;
                            color: #007bff;
                            margin: 10px 0;
                        }
                        .desc {
                            font-size: 1.5em;
                            color: #666;
                        }
                        a {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 10px 20px;
                            background: #007bff;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                        a:hover { background: #0056b3; }
                    </style>
                </head>
                <body>
                    <h1>📍 %s</h1>
                    <div class="weather">
                        <div class="temp">🌡️ %.1f°C</div>
                        <div class="desc">☁️ %s</div>
                    </div>
                    <a href="/">← Back to Cities</a>
                </body>
                </html>
                """.formatted(
                weather.getCity(),
                weather.getCity(),
                weather.getTemperature(),
                weather.getDescription()
        );
    }
}