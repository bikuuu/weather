package com.biku.weather.localization;

import com.biku.weather.exceptions.NoDataException;
import com.biku.weather.exceptions.OverRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    // todo accept LocalizationDefinition as a parameter
    Localization createLocalization(String cityName,
                                    Double longitude,
                                    Double latitude,
                                    String region,
                                    String country) {
        if (cityName.isBlank()) {
            throw new NoDataException("City Name Exception");
        }
        if (longitude <= -90 && longitude >= 90) {
            throw new OverRangeException("Over Range Longitude Argument: " + longitude);
        }
        if (latitude <= -180 && latitude >= 180) {
            throw new OverRangeException("Over Range Latitude Argument: " + latitude);
        }
        if (country.isBlank()) {
            throw new NoDataException("Country Name Exception");
        }

        Localization localization = new Localization();
        localization.setCityName(cityName);
        localization.setLongitude(longitude);
        localization.setLatitude(latitude);
        localization.setCountry(country);
        Optional.ofNullable(region).ifPresent(localization::setRegion);

        return localizationRepository.save(localization);
    }
}
