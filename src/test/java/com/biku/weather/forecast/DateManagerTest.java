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
        localDateTime = LocalDateTime.of(2020, 12, 05, 15, 00, 00);
    }

    @Test
    void nowDatePlusPeriodTest() {
        //given
        Integer period = 1;
        //when
        LocalDateTime nextDay = LocalDateTime.of(LocalDate.now().plusDays(period), LocalTime.of(12, 0));    // todo move to the 'given' section
                                                                                                            // todo LocalDateTime result = dateManager.nowDatePlusPeriod(period)
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
        Instant instant = Instant.now(clock);   // todo move to the 'given' section
                                                // todo Instant result = dateManager.instantDateConverter(localDateTime)
        // then
        assertThat(dateManager.instantDateConverter(localDateTime)).isEqualTo(instant);

        // todo
        //  Expected :2020-12-05T15:00:00Z - it is a value from the line 47
        //  Actual   :2020-12-05T14:00:00Z - it is a value from the line 19 transformed to the universal time
        //                                   if we run the test on a computer in Europe/Warsaw time zone
        //                                   then from 15:00 it becomes 14:00
    }

}
