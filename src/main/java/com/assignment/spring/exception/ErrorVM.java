package com.assignment.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorVM {
    private Integer code;
    private String reason;
}
