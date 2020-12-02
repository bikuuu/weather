package com.biku.weather.weather;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
@Builder
@NoArgsConstructor
public class Forecast {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;
    double temperature;
    int airPressure;
    int airHumidity;
    double windSpeed;
    String windDirection;
}
