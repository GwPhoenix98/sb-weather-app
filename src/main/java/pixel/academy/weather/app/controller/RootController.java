package pixel.academy.weather.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> welcome(HttpServletRequest request) {
        String baseUrl = getBaseUrl(request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Weather API");
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
                "cities", baseUrl + "/cities",
                "weather", baseUrl + "/weather/{cityName}"
        ));
        response.put("availableCities", new String[]{
                "Chisinau", "Paris", "London", "Berlin", "New York"
        });
        response.put("examples", Map.of(
                "Get all cities", baseUrl + "/cities",
                "Get weather for Paris", baseUrl + "/weather/Paris",
                "Get weather for New York", baseUrl + "/weather/New%20York"
        ));

        return response;
    }

    private String getBaseUrl(HttpServletRequest request) {
        return getString(request);
    }

    @NonNull
    static String getString(HttpServletRequest request) {
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
