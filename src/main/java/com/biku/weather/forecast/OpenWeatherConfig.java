package com.biku.weather.forecast;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "com.biku.weather.openweathermap-api")
class OpenWeatherConfig {
    private String apiKey;
}
