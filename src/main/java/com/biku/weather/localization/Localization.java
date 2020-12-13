package com.biku.weather.localization;

import com.biku.weather.forecast.Forecast;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cityName;
    Double longitude;
    Double latitude;
    String region;
    String country;
    @CreatedDate
    private Instant createdDate;
    private String createdBy;

}
