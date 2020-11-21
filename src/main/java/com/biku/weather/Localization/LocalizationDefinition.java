package com.biku.weather.Localization;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalizationDefinition {
    String cityName;
    Double longitude;
    Double latitude;
    String region;
    String country;
}
