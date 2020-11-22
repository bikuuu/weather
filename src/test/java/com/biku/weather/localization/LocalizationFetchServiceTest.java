package com.biku.weather.localization;


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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationFetchServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    @BeforeEach
    public void init() {
        localizationRepository.deleteAll();
    }

    @Test
    void fetchLozalizationDetails_returnsDetailsOfLocalization() throws Exception {
        //given
        Localization localization = new Localization();
        localization.setCityName("Gdansk");
        localization.setLongitude(18.40);
        localization.setLatitude(54.21);
        localization.setRegion("Pomorze");
        localization.setCountry("Polska");
        System.out.println(localization.toString());
        Localization savedLocalization = localizationRepository.save(localization);
        Long id = savedLocalization.getId();
        System.out.println("ID: " + id);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/localization/{id}" + id)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}