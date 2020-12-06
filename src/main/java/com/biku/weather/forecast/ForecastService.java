package com.biku.weather.forecast;

import com.biku.weather.exceptions.NotFoundComponentException;
import com.biku.weather.localization.Localization;
import com.biku.weather.localization.LocalizationFetchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
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

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            ForecastOpenWeatherResponse forecast = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
            forecast.getList().stream().filter(singleForecast -> forecast.toString().contains(cityName))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundComponentException("Cannot find forecast for " + cityName));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        Forecast newForecast = new Forecast();
        newForecast.setLocalization(localization);
        forecastRepository.save(newForecast);


        return new Forecast();
    }
}