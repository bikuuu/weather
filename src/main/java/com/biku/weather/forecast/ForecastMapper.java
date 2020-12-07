package com.biku.weather.forecast;

import org.springframework.stereotype.Component;

@Component
public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast) {
        ForecastDto forecastDto = new ForecastDto();
        forecastDto.setId(newForecast.getId());                     // todo unnecessary - remove it
        forecastDto.setTemperature(newForecast.getTemperature());
        forecastDto.setAirPressure(newForecast.getAirPressure());
        forecastDto.setAirHumidity(newForecast.getAirHumidity());
        forecastDto.setWindSpeed(newForecast.getWindSpeed());
        forecastDto.setWindDirection(newForecast.getWindDirection()); // todo you can try calculate a direction (degrees -> North, S, W, E)
        forecastDto.setDate(newForecast.getDate());
        return forecastDto;
    }
}
