package com.biku.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDto {
    long id;
    double temperature;
    int airPressure;
    int airHumidity;
    double windSpeed;
    String windDirection;
}
