package com.biku.weather.Localization;

import com.biku.weather.Exceptions.NotFoundComponentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalizationFetchService {

    private final LocalizationRepository localizationRepository;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundComponentException.class)
    public Localization fetchLocalization(Long id) {
        // todo add ExceptionHandler
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundComponentException("Bad id: " + id));
    }

    public List<Localization> getAllLocations() {
        return localizationRepository.findAll();
    }
}
