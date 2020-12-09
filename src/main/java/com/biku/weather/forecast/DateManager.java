package com.biku.weather.forecast;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
class DateManager { // todo good job! just write unit tests for this class

    private final static String zoneId = "Europe/Warsaw";

    LocalDateTime nowDatePlusPeriod(Integer period) {
        return LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));
    }

    LocalDateTime localDateTimeConverter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    Instant instatntDateConverter(LocalDateTime dateTime) {     // todo typo - instant
        return dateTime.toInstant(ZoneOffset.of(zoneId));       // todo dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
