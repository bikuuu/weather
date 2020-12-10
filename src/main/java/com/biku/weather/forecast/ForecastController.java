package com.biku.weather.forecast;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Validated
class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;

    @GetMapping("/localization/{id}/forecast")
    ForecastDto getForecast(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(5) Integer period) {
        Forecast forecast = forecastService.getForecast(id, period);
        return forecastMapper.mapToForecastDto(forecast);
    }

    @GetMapping("/forecast")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String getForecast(@RequestParam String cityName, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return null;
    }
}
