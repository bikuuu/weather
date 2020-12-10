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

@Component
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
    private final ForecastRepository forecastRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateManager dateManager;
    private final OpenWeatherConfig config;
    private final ForecastMapper forecastMapper;

    Forecast getForecast(Long id, Integer period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCityName();
        String url = config.openWeatherUrl(cityName);

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            ForecastOpenWeatherResponse forecast = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
            ForecastOpenWeatherResponse.SingleForecast forecastFromSingleForecast = forecast
                    .getList()
                    .stream()
                    .filter(singleForecast -> dateManager.localDateTimeConverter(singleForecast.getDate())
                            .equals(dateManager.nowDatePlusPeriod(period)))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundComponentException("Cannot find forecast for " + cityName));
            return forecastRepository
                    .save(forecastMapper.mapToNewForecast(forecastFromSingleForecast, localization));

        } catch (JsonProcessingException e) {
            throw new CriticalError("Critical error: " + e.getMessage());
        }
    }
}
