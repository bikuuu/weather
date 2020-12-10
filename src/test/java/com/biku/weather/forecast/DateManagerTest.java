package com.biku.weather.forecast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DateManagerTest {

    private DateManager dateManager;
    LocalDateTime localDateTime;

    @BeforeEach
    void setUp() {
        dateManager = new DateManager();
        localDateTime = LocalDateTime.of(2020, 12, 05,15, 00,00);
    }

    @Test
    void nowDatePlusPeriodTest() {
        //given
        Integer period = 1;
        //when
        LocalDateTime nextDay = LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));
        //then
        assertThat(dateManager.nowDatePlusPeriod(period)).isEqualTo(nextDay);
    }

    @Test
    void localDataTimeConverterTest() {
        //given
        String dateToTest = "2020-12-05 15:00:00";
        //when
        LocalDateTime converter = dateManager.localDateTimeConverter(dateToTest);
        //then
        assertThat(converter).isEqualTo(localDateTime);
    }

    @Test
    void instantDateConverterTest(){
        // given
        Clock clock = Clock.fixed(Instant.parse("2020-12-05T15:00:00.00Z"), ZoneId.systemDefault());
        // when
        Instant instant = Instant.now(clock);
        // then
        assertThat(dateManager.instantDateConverter(localDateTime)).isEqualTo(instant);
    }

}