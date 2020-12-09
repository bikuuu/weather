package com.biku.weather.forecast;

import com.biku.weather.localization.Localization;
import com.biku.weather.localization.LocalizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;
    @Autowired
    ForecastRepository forecastRepository;

    Localization savedLocalization;

    @BeforeEach
    void setUp() {
        forecastRepository.deleteAll();
        localizationRepository.deleteAll();
        Localization localization = new Localization();
        localization.setCityName("Warsaw");
        localization.setCountry("Poland");
        localization.setLongitude(40.0);
        localization.setLatitude(50.0);
        savedLocalization = localizationRepository.save(localization);

    }

    @Test
    void getForecast_returnCorrectForecastAnd200StatusCode() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder requestBuilder = get("/localization/" + id + "/forecast")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // todo check a response (check that all fields are not empty)

        // todo PROBLEM:
        // todo I know that my test don't pass in ForecastMapper in 36 line, but I don't have idea why ?
    }

    @Test
    void getForecast_whenPeriodIs5_returns200StatusCode() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder requestBuilder = get("/localization/" + id + "/forecast?period=5")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getForecast_whenPeriodIsOver5_returns400StatusCode() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder requestBuilder = get("/localization/" + id + "/forecast?period=6")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getForecast_whenPeriodIsUnder1_returns400StatusCode() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder requestBuilder = get("/localization/" + id + "/forecast?period=0")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
