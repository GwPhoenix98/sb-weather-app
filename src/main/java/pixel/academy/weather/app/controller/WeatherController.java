package pixel.academy.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.WeatherResponse;
import pixel.academy.weather.app.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/{cityName}")
    public WeatherResponse getWeather(@PathVariable String cityName) {
        City city = City.fromName(cityName);
        return weatherService.getWeather(city.getName());
    }
}