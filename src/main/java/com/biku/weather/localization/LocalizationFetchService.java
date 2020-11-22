package com.biku.weather.localization;

import com.biku.weather.exceptions.NotFoundComponentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalizationFetchService {

    private final LocalizationRepository localizationRepository;

    public Localization fetchLocalization(Long id) {
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundComponentException("Bad id: " + id));
    }

    public List<Localization> getAllLocations() {
        return localizationRepository.findAll();
    }
}
