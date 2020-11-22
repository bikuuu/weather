package com.biku.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        localizationRepository.deleteAll();
    }

    @Test
    void createNewLocalization_createsNewLocalizationAndReturn201StatusCode() throws Exception {
        //given
        LocalizationDto localizationDto = new LocalizationDto(null, "Gdansk", 18.40, 54.21, "Pomorze", "Polska");
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(1);
        assertThat(localizations.get(0)).satisfies(localization -> {
            assertThat(localization.getCityName()).isEqualTo("Gdansk");
            assertThat(localization.getLongitude()).isEqualTo(18.40);
            assertThat(localization.getLatitude()).isEqualTo(54.21);
            assertThat(localization.getRegion()).isEqualTo("Pomorze");
            assertThat(localization.getCountry()).isEqualTo("Polska");
        });
    }

    @Test
    void createNewLocalization_whenCityIsBlank_returns400StatusCode() throws Exception {
        //given
        LocalizationDto localizationDto = new LocalizationDto(null, " ", 18.40, 54.21, "Pomorze", "Polska");
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
}
