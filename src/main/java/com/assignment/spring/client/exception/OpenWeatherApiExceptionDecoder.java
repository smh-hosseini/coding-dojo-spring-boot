package com.assignment.spring.client.exception;

import com.assignment.spring.client.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


import static feign.FeignException.errorStatus;
import static java.nio.charset.StandardCharsets.UTF_8;

public class OpenWeatherApiExceptionDecoder implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        final ErrorResponse errorResponse = getBody(response);
        if (response.status() >= 400 && response.status() <= 499) {
            return new OpenWeatherClientException(
                    response.status(),
                    errorResponse.getMessage()
            );
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new OpenWeatherServerException(
                    response.status(),
                    errorResponse.getMessage()
            );
        }
        return errorStatus(methodKey, response);
    }

    private ErrorResponse getBody(Response response) throws IOException {

        String body = new BufferedReader(
                new InputStreamReader(response.body().asInputStream(), UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, ErrorResponse.class);
    }
}
