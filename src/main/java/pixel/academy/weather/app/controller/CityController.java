package pixel.academy.weather.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.CityLink;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping
    public List<CityLink> getCities() {
        return Arrays.stream(City.values())
                .map(city -> new CityLink(
                        city.getName(),
                        "/weather/" + city.getName()
                ))
                .collect(Collectors.toList());
    }
}
