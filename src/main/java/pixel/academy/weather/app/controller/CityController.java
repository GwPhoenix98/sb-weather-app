package pixel.academy.weather.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pixel.academy.weather.app.model.City;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping
    public List<String> getCities() {
        return Arrays.stream(City.values())
                .map(City::getName)
                .collect(Collectors.toList());
    }
}
