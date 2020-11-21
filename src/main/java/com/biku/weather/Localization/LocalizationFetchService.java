package com.biku.weather.Localization;

import com.biku.weather.Exceptions.NotFoundComponentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationFetchService {

    private final LocalizationRepository localizationRepository;

    public Localization fetchLocalization(Long id) {
        // todo add ExceptionHandler
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundComponentException("Bad id: " + id));
    }
}
