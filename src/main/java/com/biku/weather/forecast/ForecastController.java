package com.biku.weather.forecast;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    String getForecast(@RequestParam String cityName, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        // todo it is one of the additional functionalities
        //  @DateTimeFormat("yyyy-MM-dd") LocalDate date
        //  Question:
        //  Like this? @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
        //  yes
        return null;
    }
}
