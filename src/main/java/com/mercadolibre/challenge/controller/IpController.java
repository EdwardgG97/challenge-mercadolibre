package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.service.IpService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("api/ip")
@RestController
public class IpController {

    private final IpService ipService;

    @Operation(summary = "Obtener información del país según IP")
    @GetMapping(path = "{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseDTO> findByIp(@PathVariable @Pattern(regexp = "^(\\d{1,3}\\.){3}\\d{1,3}$",
            message = "Invalid IP address format") String ip) throws IpNotFoundException, AccessKeyException, RequestException {
        log.info("[[Ip Request started]]");
        return ResponseEntity.ok(ipService.findByIp(ip));
    }

}
