package com.assignment.spring.service;

import com.assignment.spring.client.WeatherAPIClient;
import com.assignment.spring.client.WeatherResponseMapper;
import com.assignment.spring.client.model.Main;
import com.assignment.spring.client.model.Sys;
import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.config.AppConfig;
import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;

import static org.mockito.Mockito.*;

@TestPropertySource(properties = {"APP_ID=api-key"})
@SpringBootTest
class WeatherServiceTest {

    public static final Integer ID = 1;
    public static final String CITY = "London";
    public static final String COUNTRY = "GB";
    public static final String API_KEY = "api-key";
    public static final Double TEMPERATURE = 10D;

    @Mock
    private WeatherAPIClient apiClient;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherResponseMapper mapper;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private WeatherEntity weatherEntity;
    private WeatherResponse weatherResponse;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        when(environment.getProperty("APP_ID")).thenReturn(API_KEY);
        weatherEntity = createWeatherEntity();
        weatherResponse = createWeatherResponse();

    }

    @Test
    void testHappyFlow() {

        when(mapper.weatherResponseToWeatherEntity(weatherResponse)).thenReturn(weatherEntity);
        when(weatherRepository.save(weatherEntity)).thenReturn(weatherEntity);
        when(apiClient.getByCity(CITY, API_KEY)).thenReturn(weatherResponse);
        final WeatherEntity london = weatherService.getWeatherByCity("London");

        verify(apiClient).getByCity(CITY, API_KEY);
        verify(weatherRepository).save(weatherEntity);

        Assertions.assertEquals(CITY, london.getCity());
        Assertions.assertEquals(COUNTRY, london.getCountry());

    }

    @Test
    void weatherClientThrowsAnError() {
        final WeatherEntity weatherEntity = createWeatherEntity();
        when(weatherRepository.save(weatherEntity)).thenReturn(weatherEntity);
        when(apiClient.getByCity(CITY, API_KEY)).thenThrow(FeignException.errorStatus(
                "getWeather",
                Response.builder()
                        .status(404)
                        .headers(new HashMap<>())
                        .reason("City Not found")
                        .request(Request.create(Request.HttpMethod.GET,
                        "/data/2.5/weather",
                        new HashMap<>(),
                        null,
                        null,
                        null))
                        .build()));
        Assertions.assertThrows(FeignException.class, () -> weatherService.getWeatherByCity(CITY));

    }

    private static WeatherEntity createWeatherEntity() {
        WeatherEntity weatherEntity =  new WeatherEntity();
        weatherEntity.setId(1L);
        weatherEntity.setCity("London");
        weatherEntity.setCountry("GB");
        weatherEntity.setTemperature(12D);
        return weatherEntity;
    }

    public static WeatherResponse createWeatherResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setName("London");

        Sys sys = new Sys();
        sys.setCountry("GB");
        weatherResponse.setSys(sys);

        var main = new Main();
        main.setTemp(TEMPERATURE);
        weatherResponse.setMain(main);

        return weatherResponse;
    }

}