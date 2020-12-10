package com.biku.weather.forecast;

import com.biku.weather.exceptions.WrongDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectionCalculatorTest {
    private DirectionCalculator directionCalculator;
    private String degree;

    @BeforeEach
    void setUp() {
        directionCalculator = new DirectionCalculator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"33.45", "0", "337.30", "360"})
    void directTestWhenResultIsN(String degree) {
        // given
        String result = "N";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"33.46", "67.29"})
    void directTestWhenDegreeResultIsNE(String degree) {
        // given
        String result = "NE";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"67.30", "67.31", "112.29", "112.30"})
    void directTestWhenDegreeResultIsE(String degree) {
        // given
        String result = "E";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"112.31", "157.29"})
    void directTestWhenDegreeIsResultIsSE(String degree) {
        // given
        String result = "SE";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"157.30", "157.31", "202.29", "202.30"})
    void directTestWhenDegreeIsResultIsS(String degree) {
        // given
        String result = "S";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"202.31", "247.29"})
    void directTestWhenDegreeIsResultIsSW(String degree) {
        // given
        String result = "SW";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"247.30", "247.31", "292.29", "292.30"})
    void directTestWhenDegreeIsResultIsW(String degree) {
        // given
        String result = "W";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"292.31", "337.29"})
    void directTestWhenDegreeIsResultIsNW(String degree) {
        // given
        String result = "NW";
        // when
        String calculate = directionCalculator.directCalculate(degree);
        // then
        assertThat(calculate).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.00", "361"})
    void whenDirectIsOverRange0To360_ThrownWrongDataException(String degree) {
        // when
        Exception exception = assertThrows(WrongDataException.class, () -> {
            directionCalculator.directCalculate(degree);
        });
        // then
        assertThat(exception).isExactlyInstanceOf(WrongDataException.class);
    }


}
