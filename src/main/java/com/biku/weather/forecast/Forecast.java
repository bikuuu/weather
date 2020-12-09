package com.biku.weather.forecast;

import com.biku.weather.localization.Localization;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@Entity
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String temperature;
    String airPressure;
    String airHumidity;
    String windSpeed;
    String windDirection;
    Instant date;

    @ManyToOne
    Localization localization;
}
