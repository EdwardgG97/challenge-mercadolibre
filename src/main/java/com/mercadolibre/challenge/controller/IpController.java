package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.dto.CountryInformationDTO;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.service.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="${spring.application.name}")
public class IpController {

    private final IpService ipService;

    @GetMapping(path = "/findByIp/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryInformationDTO> findByIp(@PathVariable String ip) throws IpNotFoundException {
        return ResponseEntity.ok(ipService.findByIp(ip));
    }
}
