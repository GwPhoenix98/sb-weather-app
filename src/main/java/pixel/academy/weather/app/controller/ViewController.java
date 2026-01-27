package pixel.academy.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.WeatherResponse;
import pixel.academy.weather.app.service.WeatherService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    private final WeatherService weatherService;

    @Autowired
    public ViewController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/cities")
    public String cities(Model model) {
        List<String> cities = Arrays.stream(City.values())
                .map(City::getName)
                .collect(Collectors.toList());

        model.addAttribute("cities", cities);
        return "index";
    }

    @GetMapping("/weather/{cityName}")
    public String weather(@PathVariable String cityName, Model model) {
        City city = City.fromName(cityName);
        WeatherResponse weather = weatherService.getWeather(city.getName());

        model.addAttribute("weather", weather);
        return "weather";
    }
}
