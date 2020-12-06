package com.biku.weather.forecast;

import com.biku.weather.exceptions.CriticalError;
import com.biku.weather.exceptions.NotFoundComponentException;
import com.biku.weather.localization.Localization;
import com.biku.weather.localization.LocalizationFetchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
    private final ForecastRepository forecastRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Forecast getForecast(Long id, Integer period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCityName();
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", "4cf8ef85b5f5a71c0346f89e6a9eb25f")
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();
        LocalDateTime forecastDateTime = LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ForecastOpenWeatherResponse forecast = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
            ForecastOpenWeatherResponse.SingleForecast forecastFromSingleForecast = forecast.getList().stream().filter(singleForecast -> forecast.toString().contains(cityName)
                    && LocalDateTime.parse(singleForecast.getDate(), formatter).equals(forecastDateTime))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundComponentException("Cannot find forecast for " + cityName));

            Forecast newForecast = new Forecast();
            newForecast.setLocalization(localization);
            newForecast.setTemperature(forecastFromSingleForecast.getMain().getTemperatur());
            newForecast.setAirHumidity(forecastFromSingleForecast.getAirHumidity());
            newForecast.setAirPressure(forecastFromSingleForecast.getAirPressure());
            newForecast.setWindSpeed(forecastFromSingleForecast.getWind().getWindSpeed());
            newForecast.setWindDirection(forecastFromSingleForecast.getWind().getWindDirection());
            newForecast.setDate(forecastFromSingleForecast.getDate());
            forecastRepository.save(newForecast);
            return newForecast;


        } catch (JsonProcessingException e) {
            throw new CriticalError("Critical error: " + e.getMessage());
        }
    }
}