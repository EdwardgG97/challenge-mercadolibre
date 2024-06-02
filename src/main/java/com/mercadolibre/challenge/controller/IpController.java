package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.model.CountryInformation;
import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.service.IpService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

    @GetMapping(path = "/ip/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryInformation> findByIp(@PathVariable @Pattern(regexp = "^(\\d{1,3}\\.){3}\\d{1,3}$",
            message = "Invalid IP address format") String ip) throws IpNotFoundException, AccessKeyException, RequestException {
        log.info("[[Ingreso a controlador]]");
        return ResponseEntity.ok(ipService.findByIp(ip));
    }

}
