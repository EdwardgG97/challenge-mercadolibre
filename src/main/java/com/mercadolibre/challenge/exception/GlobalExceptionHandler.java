package com.mercadolibre.challenge.exception;

import com.mercadolibre.challenge.model.ResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.mercadolibre.challenge.util.Constants.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IpNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleIpNotFoundException(IpNotFoundException ex) {
        log.error("[[Error IpNotFoundException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(IP_NOT_FOUND.getMsg()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessKeyException.class)
    public ResponseEntity<ResponseDTO> handleKeyException(AccessKeyException ex) {
        log.error("[[Error AccessKeyException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(INVALID_ACCESS_KEY.getMsg()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ResponseDTO> handleServerException(RequestException ex) {
        log.error("[[Error RequestException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(UNKNOWN_ERROR.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("[[Error ConstraintViolationException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(INVALID_IP_FORMAT.getMsg()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDTO> handleNullPointerException(NullPointerException ex) {
        log.error("[[Error NullPointerException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(UNKNOWN_ERROR.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("[[Error EntityNotFoundException]] {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDTO(COUNTRY_NOT_FOUND.getMsg()), HttpStatus.NOT_FOUND);
    }
}
