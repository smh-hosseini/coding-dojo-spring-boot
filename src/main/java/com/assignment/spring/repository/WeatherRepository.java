package com.assignment.spring.repository;

import com.assignment.spring.domain.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
}
