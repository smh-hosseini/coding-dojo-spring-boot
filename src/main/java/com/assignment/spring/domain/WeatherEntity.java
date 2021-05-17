package com.assignment.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String country;

    private Double temperature;
}
