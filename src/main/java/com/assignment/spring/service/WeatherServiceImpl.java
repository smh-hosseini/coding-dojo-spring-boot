package com.assignment.spring.service;

import com.assignment.spring.client.WeatherAPIClient;
import com.assignment.spring.client.WeatherResponseMapper;
import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    private final WeatherAPIClient apiClient;

    private final WeatherResponseMapper mapper;

    private final Environment env;

    @Override
    public WeatherEntity getWeatherByCity(String city) {
        WeatherResponse response = apiClient.getByCity(city, env.getProperty("APP_ID"));
        final WeatherEntity entity = weatherRepository.save(mapper.weatherResponseToWeatherEntity(response));
        return entity;
    }
}
