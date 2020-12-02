package com.biku.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping("/weather")
    public ResponseEntity<ForecastDto> getWeather(@RequestParam String localization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastService.getForecast(localization));
    }
}
