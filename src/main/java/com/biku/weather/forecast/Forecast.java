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
    private long id;
    private String temperature;
    private String airPressure;
    private String airHumidity;
    private String windSpeed;
    private String windDirection;
    private Instant date;

    @ManyToOne
    private Localization localization;
}
