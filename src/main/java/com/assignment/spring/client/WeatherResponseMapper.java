package com.assignment.spring.client;

import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.WeatherEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WeatherResponseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "city")
    @Mapping(source = "sys.country", target = "country")
    @Mapping(source = "main.temp", target = "temperature")
    WeatherEntity weatherResponseToWeatherEntity(WeatherResponse response);
}
