package com.biku.weather.Localization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationFetchService localizationFetchService;
    final LocalizationMapper localizationMapper;

    @GetMapping("/location")
    public List<LocalizationDto> getAllLocalizations() {
//        return localozationCreateService.getAllLocations();
        // todo map each Localization to LocalizationDto, use .stream().map()
        return Collections.emptyList();
    }

    @GetMapping("/localization/{id}")
    LocalizationDto getLocalization(@PathVariable long id) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createLocalization(@RequestBody LocalizationDto localizationDto) {
        String cityName = localizationDto.getCityName();
        Double longitude = localizationDto.getLongitude();
        Double latitude = localizationDto.getLatitude();
        String region = localizationDto.getRegion();
        String country = localizationDto.getCountry();
        // todo use LocalizationDefinition to wrap a data
        Localization newLocalization = localizationCreateService.createLocalization(cityName, longitude, latitude, region, country);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(newLocalization));
    }
}
