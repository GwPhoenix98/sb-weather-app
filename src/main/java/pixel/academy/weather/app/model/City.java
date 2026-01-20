package pixel.academy.weather.app.model;

public enum City {
    CHISINAU( "Chisinau"),
    PARIS( "Paris"),
    LONDON( "London"),
    BERLIN("Berlin"),
    NEW_YORK("New York");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static City fromName(String name) {
        for (City city : values()) {
            if (city.name.equalsIgnoreCase(name)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Invalid city name: " + name);
    }
}

