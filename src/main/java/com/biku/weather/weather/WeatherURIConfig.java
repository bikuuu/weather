package com.biku.weather.weather;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("com.biku.weather.weather-api.api")
public class WeatherURIConfig {
    private String apiKey;
    private String uri;
    private String units;
    private String lang;
}
