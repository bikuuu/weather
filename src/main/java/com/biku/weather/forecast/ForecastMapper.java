package com.biku.weather.forecast;

public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast) {
        ForecastDto forecastDto = new ForecastDto();
        forecastDto.setId(newForecast.getId());
        forecastDto.setTemperature(newForecast.getTemperature());
        forecastDto.setAirPressure(newForecast.getAirPressure());
        forecastDto.setAirHumidity(newForecast.getAirHumidity());
        forecastDto.setWindSpeed(newForecast.getWindSpeed());
        forecastDto.setWindDirection(newForecast.getWindDirection());
        forecastDto.setDate(newForecast.getDate());
        return forecastDto;
    }
}
