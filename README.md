# ☁️ Weather API - Spring Boot

A **Spring Boot REST API** that provides real-time weather data for predefined cities using the [OpenWeatherMap API](https://openweathermap.org). The application features both a simple web interface and RESTful JSON endpoints.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?style=flat&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue?style=flat&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-green?style=flat)

---

## ✨ Features

- 🌍 **Real-time weather data** from OpenWeatherMap API
- 🏙️ **Predefined cities**: Chisinau, Paris, London, Berlin, New York
- 🌐 **Dual interface**: Web UI and REST API
- 📱 **Content negotiation**: Returns HTML for browsers, JSON for API clients
- ⚠️ **Global exception handling** with custom error responses
- 🔒 **Secure configuration** with environment variables
- 🧹 **Clean architecture** following SOLID principles

---

## 🏗️ Project Structure

```
weather-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── weather/app/
│   │   │       ├── WeatherApiApplication.java        // Main class
│   │   │       ├── controller/
│   │   │       │   ├── CityController.java           // /cities endpoint
│   │   │       │   └── WeatherController.java        // /weather/{city} endpoint
│   │   │       ├── model/
│   │   │       │   ├── City.java                     // Cities enum
│   │   │       │   └── WeatherResponse.java          // Weather DTO
│   │   │       ├── service/
│   │   │       │   └── WeatherService.java           // OpenWeatherMap integration
│   │   │       └── exception/
│   │   │           ├── WeatherException.java         // Custom exception
│   │   │           └── GlobalExceptionHandler.java   // REST error handler
│   │   └── resources/
│   │       ├── application.properties                // Configuration
│   │       └── static/
│   │           └── index.html                        // Web interface
│   └── test/
└── pom.xml                                           // Maven dependencies
```

---

## 🛠️ Technologies

- **Java 17** - Programming language
- **Spring Boot 3.2.1** - Framework
- **Maven** - Build tool
- **OpenWeatherMap API** - Weather data provider
- **org.json** - JSON parsing
- **HttpURLConnection** - HTTP client
- **SLF4J** - Logging

---

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8+
- OpenWeatherMap API key (free)
- Internet connection

---

## 🔑 OpenWeatherMap API Key Setup

### 1. Get your API key

1. Create a free account at [OpenWeatherMap](https://openweathermap.org)
2. Navigate to **API Keys** section
3. Generate a new API key
4. Copy your API key

### 2. Set environment variable

**Windows (Command Prompt):**
```cmd
setx WEATHER_API_KEY "your_api_key_here"
```

**Windows (PowerShell):**
```powershell
[System.Environment]::SetEnvironmentVariable('WEATHER_API_KEY','your_api_key_here','User')
```

**Linux / macOS:**
```bash
export WEATHER_API_KEY="your_api_key_here"
```

**Permanent (Linux/macOS):**
```bash
echo 'export WEATHER_API_KEY="your_api_key_here"' >> ~/.bashrc
source ~/.bashrc
```

> ⚠️ **Important:** Restart your IDE/terminal after setting the environment variable!

---

## 🚀 How to Run

### Option 1: Using Maven

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/weather-api.git

# Navigate to project directory
cd weather-api

# Run with Maven
mvn clean spring-boot:run
```

### Option 2: Using IDE (IntelliJ IDEA)

1. Open the project in IntelliJ IDEA
2. Wait for Maven to download dependencies
3. Set environment variable `WEATHER_API_KEY`
4. Run `WeatherApiApplication.java` (right-click → Run)

### Option 3: Build JAR and run

```bash
# Build the project
mvn clean package

# Run the JAR
java -jar target/weather-api-1.0.0.jar
```

---

## 📱 Usage

### Web Interface (Browser)

Open your browser and navigate to:

```
http://localhost:8080
```

You'll see a list of cities. Click on any city to view its current weather.

### REST API Endpoints

#### 1. Get all cities

**Request:**
```http
GET http://localhost:8080/cities
```

**Response:**
```json
["Chisinau", "Paris", "London", "Berlin", "New York"]
```

#### 2. Get weather for a city

**Request:**
```http
GET http://localhost:8080/weather/Paris
```

**Response (JSON - from Postman/cURL):**
```json
{
  "city": "Paris",
  "temperature": 12.4,
  "description": "clear sky"
}
```

**Response (HTML - from Browser):**
```
Displays a formatted HTML page with temperature and description
```

#### 3. Examples

```bash
# Get all cities
curl http://localhost:8080/cities

# Get weather for Chisinau
curl http://localhost:8080/weather/Chisinau

# Get weather for New York (URL encoded)
curl http://localhost:8080/weather/New%20York

# Get JSON in browser (force JSON response)
curl -H "Accept: application/json" http://localhost:8080/weather/Paris
```

---

## 🧪 Testing

### Manual Testing

1. **Web Interface:**
   - Visit `http://localhost:8080`
   - Click on cities
   - Verify weather data displays correctly

2. **API Endpoints:**
   - Use Postman or cURL
   - Test all cities
   - Verify JSON responses

### Example cURL Commands

```bash
# Test cities endpoint
curl http://localhost:8080/cities

# Test weather endpoints
curl http://localhost:8080/weather/Chisinau
curl http://localhost:8080/weather/Paris
curl http://localhost:8080/weather/London
curl http://localhost:8080/weather/Berlin
curl "http://localhost:8080/weather/New%20York"
```

---

## 🏛️ Architecture & Design

### Applied Principles

- ✅ **Single Responsibility Principle** - Each class has one clear purpose
- ✅ **Dependency Injection** - Loose coupling via constructor injection
- ✅ **Separation of Concerns** - Controllers, services, and models are separate
- ✅ **DTO Pattern** - `WeatherResponse` for data transfer
- ✅ **Enum Pattern** - Type-safe city selection
- ✅ **Exception Handling** - Custom exceptions with global handler
- ✅ **Content Negotiation** - Returns HTML or JSON based on request

### Class Responsibilities

| Class | Responsibility |
|-------|---------------|
| `WeatherApiApplication` | Application entry point |
| `CityController` | `/cities` endpoint - returns available cities |
| `WeatherController` | `/weather/{city}` endpoint - returns weather data |
| `WeatherService` | Handles API communication with OpenWeatherMap |
| `City` | Enum of available cities |
| `WeatherResponse` | DTO for weather data |
| `WeatherException` | Custom exception for weather-related errors |
| `GlobalExceptionHandler` | Centralized REST error handling |

---

## 🔒 Security

- ✅ API keys stored in environment variables (not in code)
- ✅ No sensitive data in version control
- ✅ Input validation for city names
- ✅ Proper exception handling for API errors
- ✅ HTTP timeouts to prevent hanging requests

---

## ⚠️ Error Handling

The API handles various error scenarios:

- ❌ **Missing API key** → Clear setup instructions
- ❌ **Invalid city name** → 400 Bad Request with error message
- ❌ **Network errors** → 400 Bad Request with error details
- ❌ **API errors** → HTTP status code from OpenWeatherMap
- ❌ **Timeout** → 5-second timeout on requests

**Example Error Response:**
```json
{
  "timestamp": "2026-01-18T19:30:00",
  "status": 400,
  "error": "Weather Service Error",
  "message": "Failed to fetch weather data. HTTP code: 404"
}
```

---

## 📝 Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Application Name
spring.application.name=Weather API

# Weather API Configuration
weather.api.key=${WEATHER_API_KEY}

# Logging
logging.level.weather.app=INFO

# JSON formatting
spring.jackson.serialization.indent-output=true
```

---

## 🚀 Future Improvements

- [ ] Add more weather details (humidity, wind speed, pressure)
- [ ] Implement 5-day weather forecast
- [ ] Add caching to reduce API calls
- [ ] Support custom city input
- [ ] Add database to store weather history
- [ ] Implement rate limiting
- [ ] Add Swagger/OpenAPI documentation
- [ ] Add unit and integration tests
- [ ] Docker containerization
- [ ] CI/CD pipeline

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 👤 Author

Schimbător Ion
- GitHub: (https://github.com/GwPhoenix98)

---

## 🙏 Acknowledgments

- [OpenWeatherMap](https://openweathermap.org) for providing the weather API
- [Spring Boot](https://spring.io/projects/spring-boot) for the excellent framework
- Java community for documentation and support

---

## 📊 API Documentation

### Endpoints Summary

| Method | Endpoint | Description | Response Type |
|--------|----------|-------------|---------------|
| GET | `/` | Web interface | HTML |
| GET | `/cities` | List of available cities | JSON |
| GET | `/weather/{city}` | Weather data for city | JSON/HTML |

### Response Formats

The `/weather/{city}` endpoint supports content negotiation:

- **Browser request** → Returns HTML page
- **API request (Accept: application/json)** → Returns JSON

---

## 🔧 Troubleshooting

### Issue: "API KEY missing" error

**Solution:** Ensure `WEATHER_API_KEY` environment variable is set and restart your IDE.

```bash
# Verify it's set
echo $WEATHER_API_KEY  # Linux/Mac
echo %WEATHER_API_KEY% # Windows
```

### Issue: HTTP 400 errors

**Solution:** Check that city names are properly URL encoded (spaces as `%20`).

### Issue: Connection timeout

**Solution:** Check your internet connection and firewall settings.

---

⭐ **If you found this project helpful, please consider giving it a star!**

---

**Made with ☕ and ❤️ using Spring Boot**
