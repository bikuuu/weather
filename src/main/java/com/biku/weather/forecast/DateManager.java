package com.biku.weather.forecast;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
class DateManager {

    LocalDateTime nowDatePlusPeriod(Integer period) {
        return LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));
    }

    LocalDateTime localDateTimeConverter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    Instant instantDateConverter(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
