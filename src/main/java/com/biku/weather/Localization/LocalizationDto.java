package com.biku.weather.Localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {
    Long id;
    String cityName;
    Double longitude;
    Double latitude;
    String region;
    String country;
}
