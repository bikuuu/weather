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
}
