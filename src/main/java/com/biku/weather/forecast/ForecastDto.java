package com.biku.weather.forecast;

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
    String temperature;
    String airPressure;
    String airHumidity;
    String windSpeed;
    String windDirection;
    String date;
}
