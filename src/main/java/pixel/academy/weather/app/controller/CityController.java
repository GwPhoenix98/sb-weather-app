package pixel.academy.weather.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pixel.academy.weather.app.model.City;
import pixel.academy.weather.app.model.CityLink;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping
    public List<CityLink> getCities(HttpServletRequest request) {

        String baseUrl = getBaseUrl(request);

        return Arrays.stream(City.values())
                .map(city -> new CityLink(
                        city.getName(),
                        "/weather/" + city.getName()
                ))
                .collect(Collectors.toList());
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(scheme).append("://").append(serverName);

        if ((scheme.equals("http") && serverPort != 80) ||
                (scheme.equals("https") && serverPort != 443)) {
            baseUrl.append(":").append(serverPort);
        }
        return baseUrl.toString();
    }
}
