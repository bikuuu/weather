package com.biku.weather.forecast;

import com.biku.weather.localization.Localization;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class ForecastMapper {   // todo add @RequiredArgsConstructor

    private DateManager dateManager;    // todo mark as a final
    private DirectionCalculator directionCalculator; // todo mark as a final

    // todo only then Spring injects these properties through the constructor

    ForecastDto mapToForecastDto(Forecast newForecast) {
        ForecastDto forecastDto = new ForecastDto();
        forecastDto.setTemperature(newForecast.getTemperature());
        forecastDto.setAirPressure(newForecast.getAirPressure());
        forecastDto.setAirHumidity(newForecast.getAirHumidity());
        forecastDto.setWindSpeed(newForecast.getWindSpeed());
        forecastDto.setWindDirection(directionCalculator.directCalculate(newForecast.getWindDirection()));  // todo directionCalculator is not managed by Spring (@Component or @Bean)
        forecastDto.setDate(newForecast.getDate());
        return forecastDto;
    }

    Forecast mapToNewForecast(ForecastOpenWeatherResponse.SingleForecast forecast,
                              Localization localization) {
        Forecast newForecast = new Forecast();
        newForecast.setLocalization(localization);
        newForecast.setTemperature(forecast.getMain().getTemperature());
        newForecast.setAirHumidity(forecast.getMain().getAirHumidity());
        newForecast.setAirPressure(forecast.getMain().getAirPressure());
        newForecast.setWindSpeed(forecast.getWind().getWindSpeed());
        newForecast.setWindDirection(forecast.getWind().getWindDirection());
        System.out.println("Date: " + forecast.getDate());
        LocalDateTime localDateTime = dateManager.localDateTimeConverter(forecast.getDate());       // todo dateManager is null - it is not injected by Spring
        Instant instant = dateManager.instatntDateConverter(localDateTime);
        newForecast.setDate(instant);
        newForecast.setDate(dateManager.instatntDateConverter(dateManager.localDateTimeConverter(forecast.getDate())));

        return newForecast;
    }
}
