package com.biku.weather.Localization;

import com.biku.weather.Exceptions.NotFoundComponentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalizationController {
    final LocalozationCreateService localozationCreateService;
    final LocalizationFetchService localizationFetchService;
    final LocalizationMapper localizationMapper;

    @GetMapping("/location")
    public List<Localization> getAllLocalizations() {
        return localozationCreateService.getAllLocations();
    }

    @GetMapping("/localization/{id}")
    LocalizationDto getLocalization(@PathVariable long id) throws NotFoundComponentException {
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
        Localization newLocalization = localozationCreateService.createLocalization(cityName, longitude, latitude, region, country);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(newLocalization));
    }

}
