package com.assignment.spring.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class OpenWeatherClientException extends RuntimeException {

    private int status;
    private String reason;

}
