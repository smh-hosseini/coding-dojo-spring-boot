package com.assignment.spring.resource.vm;

import com.assignment.spring.domain.WeatherEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeatherVMMapper {

    WeatherVM weatherEntityToWeatherVM(WeatherEntity weatherEntity);
}
