package com.assignment.spring.resource.vm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WeatherVM {

    private Integer id;

    private String city;

    private String country;

    private Double temperature;

}
