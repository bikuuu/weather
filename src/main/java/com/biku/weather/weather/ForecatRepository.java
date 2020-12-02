package com.biku.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecatRepository extends JpaRepository<Forecast, Long> {
}
