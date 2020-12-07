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

@Component
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
    private final ForecastRepository forecastRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateManager dateManager;
    private final ForecastCreator forecastCreator;
    private final OpenWeatherConfig config;

    public Forecast getForecast(Long id, Integer period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCityName();
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", config.getApiKey())
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            ForecastOpenWeatherResponse forecast = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
            ForecastOpenWeatherResponse.SingleForecast forecastFromSingleForecast = forecast
                    .getList()
                    .stream()
                    .filter(singleForecast -> forecast.toString().contains(cityName) &&         // todo forecast.toString().contains(cityName) is unnecessary
                            dateManager.localDateTimeConverter(singleForecast.getDate())
                                    .equals(dateManager.nowDatePlusPeriod(period)))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundComponentException("Cannot find forecast for " + cityName));
            return forecastRepository
                    .save(forecastCreator.createNewForecast(forecastFromSingleForecast, localization));

        } catch (JsonProcessingException e) {
            throw new CriticalError("Critical error: " + e.getMessage());
        }
    }
}
