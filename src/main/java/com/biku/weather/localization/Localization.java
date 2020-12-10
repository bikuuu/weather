package com.biku.weather.localization;

import com.biku.weather.forecast.Forecast;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cityName;
    Double longitude;
    Double latitude;
    String region;
    String country;

}
