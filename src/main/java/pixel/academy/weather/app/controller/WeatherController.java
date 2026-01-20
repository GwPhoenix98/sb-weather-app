package pixel.academy.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.WeatherResponse;
import pixel.academy.weather.app.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private  final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityName}")
    public WeatherResponse getWeather(@PathVariable String cityName) {
        City city = City.fromName(cityName);
        return weatherService.getWeather(city.getName());
    }
}
