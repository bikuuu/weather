package com.biku.weather.localization;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
