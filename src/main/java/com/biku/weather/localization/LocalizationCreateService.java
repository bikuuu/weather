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

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        if (localizationDefinition.getCityName().isBlank()) {
            throw new NoDataException("City Name Exception");
        } else if (localizationDefinition.getLongitude() <= -90 && localizationDefinition.getLongitude() >= 90) {
            throw new OverRangeException("Over Range Longitude Argument: " + localizationDefinition.getLongitude());
        } else if (localizationDefinition.getLatitude() <= -180 && localizationDefinition.getLatitude() >= 180) {
            throw new OverRangeException("Over Range Latitude Argument: " + localizationDefinition.getLatitude());
        } else if (localizationDefinition.getCountry().isBlank()) {
            throw new NoDataException("Country Name Exception");
        } else {
            Localization localization = new Localization();
            localization.setCityName(localizationDefinition.cityName);
            localization.setLongitude(localizationDefinition.longitude);
            localization.setLatitude(localizationDefinition.latitude);
            localization.setCountry(localizationDefinition.country);
            Optional.ofNullable(localizationDefinition.region).ifPresent(localization::setRegion);

            return localizationRepository.save(localization);
        }
    }
}
