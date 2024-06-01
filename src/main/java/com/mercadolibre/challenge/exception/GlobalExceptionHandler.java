package com.mercadolibre.challenge.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IpNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(IpNotFoundException ex) {
        log.error("Error IpNotFoundException {}", ex.getMessage());
        var error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, error.getStatusCode());
    }

    @ExceptionHandler(AccessKeyException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(AccessKeyException ex) {
        log.error("Error AccessKeyException {}", ex.getMessage());
        var error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, error.getStatusCode());
    }
}
