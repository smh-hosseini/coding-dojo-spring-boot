package com.assignment.spring.resource;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.resource.vm.WeatherVM;
import com.assignment.spring.resource.vm.WeatherVMMapper;
import com.assignment.spring.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherResource {

    private final WeatherService weatherService;

    private final WeatherVMMapper mapper;

    @GetMapping
    public ResponseEntity<WeatherVM> weatherByCity(@RequestParam("city") String city) {
        final WeatherEntity weatherByCity = weatherService.getWeatherByCity(city);
        return ResponseEntity.ok(mapper.weatherEntityToWeatherVM(weatherByCity));
    }
}
