package com.biku.weather.weather;

import com.biku.weather.exceptions.BadRequestException;
import com.biku.weather.exceptions.WrongDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final WeatherURIConfig uriConfig;
    private Forecast forecast;
    private final ForecastMapper forecastMapper;
    private final ForecatRepository forecatRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();


    public ForecastDto getForecast(String localization){
        UriComponents uriBuilder = UriComponentsBuilder.fromUriString(uriConfig.getUri())
                .queryParam("access_key", uriConfig.getApiKey())
                .queryParam("query",localization)
                .queryParam("units", uriConfig.getUnits())
                .queryParam("lang",uriConfig.getLang())
                .build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriBuilder.toUri(), String.class);

        if ((!responseEntity.getStatusCode().is2xxSuccessful())){
            throw new BadRequestException("Impossoble get data from weather service");
        }

        String responseBody = responseEntity.getBody();

        ForecastDto forecastDto = null;
        try {
            forecast = objectMapper.readValue(responseBody, Forecast.class);
            return forecastMapper.mapToForecastDto(forecatRepository.save(forecast));
        } catch (JsonProcessingException e) {
           throw new WrongDataException("Forecast data is invalid");
        }
    }
}
