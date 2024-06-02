package com.mercadolibre.challenge.exception;

import jakarta.validation.ConstraintViolationException;
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
    public ResponseEntity<ErrorResponse> handleIpNotFoundException(IpNotFoundException ex) {
        log.error("[[Error IpNotFoundException]] {}", ex.getMessage());
        var error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, error.getStatusCode());
    }

    @ExceptionHandler(AccessKeyException.class)
    public ResponseEntity<ErrorResponse> handleKeyException(AccessKeyException ex) {
        log.error("[[Error AccessKeyException]] {}", ex.getMessage());
        var error = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(error, error.getStatusCode());
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorResponse> handleServerException(RequestException ex) {
        log.error("[[Error RequestException]] {}", ex.getMessage());
        var error = ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(error, error.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("[[Error ConstraintViolationException]] {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
