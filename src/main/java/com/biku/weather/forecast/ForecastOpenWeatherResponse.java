package com.biku.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeatherResponse {

    private String cod;             // todo unnecessary
    private int cnt;                // todo unnecessary
    private CityResponse city;      // todo unnecessary
    private List<SingleForecast> list;

    @Data
    public static class CityResponse {      // todo unnecessary
        private String name;
        private String population;
    }

    @Data
    public static class SingleForecast {
        @JsonProperty("dt_txt")
        private String date;
        @JsonProperty("pressure")
        private String airPressure;     // todo wrong place
        @JsonProperty("humidity")
        private String airHumidity;     // todo wrong place
        private Main main;
        private Wind wind;
    }

    @Data
    public static class Main {
        @JsonProperty("temp")
        private String temperatur;  // todo typo - temperature
    }

    @Data
    public static class Wind {
        @JsonProperty("speed")
        private String windDirection;
        @JsonProperty("deg")
        private String windSpeed;
    }
}
