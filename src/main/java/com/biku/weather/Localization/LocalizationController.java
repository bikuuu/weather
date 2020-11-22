package com.biku.weather.Localization;

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



//         todo map each Localization to LocalizationDto, use .stream().map()
        List<LocalizationDto> collect = localizationFetchService.getAllLocations().stream().map(p -> {
            LocalizationDto localizationDto = new LocalizationDto();
            localizationDto.id = p.getId();
            localizationDto.cityName = p.getCityName();
            localizationDto.longitude = p.getLongitude();
            localizationDto.latitude = p.getLatitude();
            localizationDto.region = p.getRegion();
            localizationDto.country = p.getCountry();
            return localizationDto;
        }).collect(Collectors.toList());
        return collect;
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

        Localization localization = localizationMapper.mapToLocalization(localizationDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(localization));
    }
}
