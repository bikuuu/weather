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
    long id;
    String temperature;
    String airPressure;
    String airHumidity;
    String windSpeed;
    String windDirection;
    Instant date;
}
