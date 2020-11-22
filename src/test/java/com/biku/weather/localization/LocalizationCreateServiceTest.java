package com.biku.weather.localization;

import com.biku.weather.exceptions.NoDataException;
import com.biku.weather.exceptions.OverRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalizationCreateServiceTest {

    @Mock
    LocalizationRepository localizationRepository;
    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createLocalization_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(18.40)
                .latitude(54.21)
                .country("Polska")
                .region("Pomorski")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenCityIsEmpty_throwsNoDataException(){
        //when
        Throwable result = catchThrowable(()-> localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("")
                .longitude(18.40)
                .latitude(54.21)
                .country("Polska")
                .region("Pomorski")
                .build()));

        //then
        assertThat(result).isExactlyInstanceOf(NoDataException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }
    @Test
    void createLocalization_whenCityIsBlank_throwsNoDataException(){
        //when
        Throwable result = catchThrowable(()-> localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName(" ")
                .longitude(18.40)
                .latitude(54.21)
                .country("Polska")
                .region("Pomorski")
                .build()));

        //then
        assertThat(result).isExactlyInstanceOf(NoDataException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenCountryIsEmpty_throwsNoDataException(){
        //when
        Throwable result = catchThrowable(()-> localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(18.40)
                .latitude(54.21)
                .country("")
                .region("Pomorski")
                .build()));

        //then
        assertThat(result).isExactlyInstanceOf(NoDataException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }
    @Test
    void createLocalization_whenCountryIsBlank_throwsNoDataException(){
        //when
        Throwable result = catchThrowable(()-> localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(18.40)
                .latitude(54.21)
                .country(" ")
                .region("Pomorski")
                .build()));

        //then
        assertThat(result).isExactlyInstanceOf(NoDataException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenRegionIsEmpty_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(18.40)
                .latitude(54.21)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }
    @Test
    void createLocalization_whenRegionIsBlank_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(18.40)
                .latitude(54.21)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }
    @Test
    void createLocalization_whenLongitudeIs90_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(90.0)
                .latitude(54.21)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLongitudeIsMinus90_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(-90.0)
                .latitude(54.21)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLatitudeIs180_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(80.0)
                .latitude(180.0)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLatitudeIsMinus180_callsLocalizationRepository(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());

        //when
        Localization result = localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(88.0)
                .latitude(-180.0)
                .country("Polska")
                .region("")
                .build());

        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLongitudeIs91_throwsOverRangeException(){
        //when
        Throwable result = catchThrowable(()-> localizationCreateService.createLocalization(LocalizationDefinition.builder()
                .cityName("Gdansk")
                .longitude(91.0)
                .latitude(54.21)
                .country("Polska")
                .region("Pomorski")
                .build()));

        //then
        assertThat(result).isExactlyInstanceOf(OverRangeException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }



}