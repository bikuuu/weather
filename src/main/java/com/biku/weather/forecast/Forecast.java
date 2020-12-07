package com.biku.weather.forecast;

import com.biku.weather.localization.Localization;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
    String date;            // todo use Instant

    @ManyToOne
    Localization localization;
}
