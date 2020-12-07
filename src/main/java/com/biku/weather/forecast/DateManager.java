package com.biku.weather.forecast;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
class DateManager { // todo good job! just write unit tests for this class

    LocalDateTime nowDatePlusPeriod(Integer period) {
        return LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));
    }

    LocalDateTime localDateTimeConverter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
