package com.biku.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationFetchService localizationFetchService;
    final LocalizationMapper localizationMapper;

    @GetMapping("/localizations")
    public List<LocalizationDto> getAllLocalizations() {
        // todo use localizationMapper.mapToLocalizationDto
        return localizationFetchService.getAllLocations().stream().map(p -> {
            LocalizationDto localizationDto = new LocalizationDto();
            localizationDto.id = p.getId();
            localizationDto.cityName = p.getCityName();
            localizationDto.longitude = p.getLongitude();
            localizationDto.latitude = p.getLatitude();
            localizationDto.region = p.getRegion();
            localizationDto.country = p.getCountry();
            return localizationDto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/localization/{id}")
    LocalizationDto getLocalization(@PathVariable long id) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createLocalization(@RequestBody LocalizationDto localizationDto) {
        LocalizationDefinition localizationDefinition = LocalizationDefinition.builder()
                .cityName(localizationDto.getCityName())
                .longitude(localizationDto.getLongitude())
                .latitude(localizationDto.getLatitude())
                .region(localizationDto.getRegion())
                .country(localizationDto.getCountry())
                .build();

        // todo uncomment and adjust this method
        // Localization localization = localizationCreateService.createLocalization(localizationDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(null)); // todo change to localization
    }
}
