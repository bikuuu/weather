package com.biku.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDto {

    private long id;
    private String temperature;
    private String airPressure;
    private String airHumidity;
    private String windSpeed;
    private String windDirection;
    private Instant date;
}
