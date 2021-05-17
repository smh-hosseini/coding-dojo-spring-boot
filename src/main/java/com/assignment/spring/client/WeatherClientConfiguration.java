package com.assignment.spring.client;

import com.assignment.spring.client.exception.OpenWeatherApiExceptionDecoder;
import org.springframework.context.annotation.Bean;


public class WeatherClientConfiguration {

    @Bean
    public OpenWeatherApiExceptionDecoder clientDecoder() {
        return new OpenWeatherApiExceptionDecoder();
    }
}
