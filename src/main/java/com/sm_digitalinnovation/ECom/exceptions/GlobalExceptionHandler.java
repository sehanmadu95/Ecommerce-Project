package com.sm_digitalinnovation.ECom.exceptions;

import com.sm_digitalinnovation.ECom.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*  @ExceptionHandler(ProuctsNotFoundException.class)
    public ResponseEntity<String>handleProductNotFoundException(ProuctsNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }*/

    /*@ExceptionHandler(ProuctsNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProuctsNotFoundException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("https://example.com/problem/resource-not-found"));
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }*/

    @ExceptionHandler(ProuctsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProuctsNotFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .message("Item not found!")
                .status(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .data(null)
                .build();

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
