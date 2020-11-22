package com.biku.weather.Localization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocalizationControllerTest {
    LocalizationController localizationController;
    LocalizationDto localizationDto;

    @BeforeEach
    public void init() {
        localizationDto = new LocalizationDto(null, "Gdansk", 18.40, 54.21, "Pomorze", "Polska");
    }

    @Test
    void createLocalizationTest() {
        ResponseEntity<LocalizationDto> localization = localizationController.createLocalization(localizationDto);
        assertThat(localization.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
    }

}