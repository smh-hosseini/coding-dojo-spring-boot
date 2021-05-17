package com.assignment.spring.util;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SampleFeignClientException extends FeignException {

    public SampleFeignClientException (int status, String message, byte[] content) {
        super(status, message, content);
    }
}
