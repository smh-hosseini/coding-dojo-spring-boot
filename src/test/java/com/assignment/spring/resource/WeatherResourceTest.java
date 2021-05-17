package com.assignment.spring.resource;

import com.assignment.spring.client.WeatherAPIClient;
import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.util.MockUtil;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"APP_ID=api-key"})
@AutoConfigureWireMock(port = 9561)
public class WeatherResourceTest {


    @Autowired
    private WireMockServer mockWeatherClient;

    @Autowired
    private WeatherAPIClient apiClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private Environment environment;


    @Test
    public void weatherSucceed() throws Exception {

        MockUtil.setupMockWeatherResponse(mockWeatherClient, "London", "api-key", 200);
        mockMvc.perform(get("/api/weather?city=London"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("London"))
                .andExpect(jsonPath("$.country").value("GB"));
        final List<WeatherEntity> weatherEntities = weatherRepository.findAll();
        Assertions.assertEquals(weatherEntities.size(), weatherEntities.size());
    }

    @Test
    public void weatherFailed404() throws Exception {
        MockUtil.setupMockWeatherResponse(mockWeatherClient, "L", "api-key", 404);
        mockMvc.perform(get("/api/weather?city=L"))
                .andExpect(status().is(HttpStatus.SC_NOT_FOUND))
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.reason").value("city not found"));
    }

    @Test
    public void weatherFailed401() throws Exception {
        MockUtil.setupMockWeatherResponse(mockWeatherClient, "London", "api-key", 401);
        mockMvc.perform(get("/api/weather?city=London"))
                .andExpect(status().is(HttpStatus.SC_UNAUTHORIZED))
                .andExpect(jsonPath("$.code").value(401))
                .andExpect(jsonPath("$.reason").value("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."));
    }

    @Test
    public void weatherFailed400() throws Exception {
        MockUtil.setupMockWeatherResponse(mockWeatherClient, "London", "api-key", 400);
        mockMvc.perform(get("/api/weather?city=London"))
                .andExpect(status().is(HttpStatus.SC_BAD_REQUEST))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.reason").value("Nothing to geocode"));
    }

}