package com.sm_digitalinnovation.ECom.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private int statusCode;
    private String message;


    private Object data;
}
