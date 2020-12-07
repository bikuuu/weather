package com.biku.weather.forecast;

import com.biku.weather.localization.Localization;
import org.springframework.stereotype.Component;

@Component
public class ForecastCreator {  // todo rename or move createNewForecast method - it's a mapper

    Forecast createNewForecast(ForecastOpenWeatherResponse.SingleForecast forecast,
                               Localization localization) {
        Forecast newForecast = new Forecast();
        newForecast.setLocalization(localization);
        newForecast.setTemperature(forecast.getMain().getTemperatur());
        newForecast.setAirHumidity(forecast.getAirHumidity());
        newForecast.setAirPressure(forecast.getAirPressure());
        newForecast.setWindSpeed(forecast.getWind().getWindSpeed());
        newForecast.setWindDirection(forecast.getWind().getWindDirection());
        newForecast.setDate(forecast.getDate());
        return newForecast;
    }
}
