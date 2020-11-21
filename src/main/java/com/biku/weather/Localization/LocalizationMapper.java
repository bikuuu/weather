package com.biku.weather.Localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization newLocalization){
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setId(newLocalization.getId());
        localizationDto.setCityName(newLocalization.cityName);
        localizationDto.setLongitude(newLocalization.longitude);
        localizationDto.setLatitude(newLocalization.latitude);
        localizationDto.setRegion(newLocalization.region);
        localizationDto.setCountry(newLocalization.country);
        return  localizationDto;
    }


    Localization mapToLocalization(LocalizationDefinition newLocalizationDefinition){
        Localization localization = new Localization();
        localization.setCityName(newLocalizationDefinition.cityName);
        localization.setLongitude(newLocalizationDefinition.longitude);
        localization.setLatitude(newLocalizationDefinition.latitude);
        localization.setRegion(newLocalizationDefinition.region);
        localization.setCountry(newLocalizationDefinition.country);
        return  localization;
    }
}
