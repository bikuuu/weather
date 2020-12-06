package com.biku.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeatherResponse {
    private String cod;
    private int cnt;
    private CityResponse city;
    private List<SingleForecast> list;


    @Data
    public static class CityResponse {
        private String name;
        private String population;
    }

    @Data
    public static class SingleForecast {
        @JsonProperty("dt_txt")
        private String date;
        @JsonProperty("pressure")
        private String airPressure;
        @JsonProperty("humidity")
        private String airHumidity;
        private Main main;
        private Wind wind;
    }

    @Data
    public static class Main {
        @JsonProperty("temp")
        private String temperatur;
    }

    @Data
    public static class Wind {
        @JsonProperty("speed")
        private String windDirection;
        @JsonProperty("deg")
        private String windSpeed;
    }

}
