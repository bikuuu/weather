package com.biku.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast) {
        return ForecastDto.builder()
                .id(newForecast.getId())
                .temperature(newForecast.getTemperature())
                .windDirection(newForecast.getWindDirection())
                .windSpeed(newForecast.getWindSpeed())
                .airHumidity(newForecast.getAirHumidity())
                .airPressure(newForecast.getAirPressure()).build();
    }
}
