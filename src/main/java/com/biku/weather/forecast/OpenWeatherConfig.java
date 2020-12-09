package com.biku.weather.forecast;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "com.biku.weather.openweathermap-api")
public class OpenWeatherConfig {
    private String apiKey;

    String openWeatherUrl(String cityName) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", getApiKey())
                .build().toUriString();
    }
}
