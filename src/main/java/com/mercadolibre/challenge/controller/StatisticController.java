package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.model.AverageResponseDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/statistics")
@RestController
public class StatisticController {

    private final StatisticService statisticService;

    @Operation(summary = "Distancia más lejana a Buenos Aires desde las consultas por Ip")
    @GetMapping(path = "farthest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseDTO> farthest() {
        log.info("[[Farthest Request started]]");
        return ResponseEntity.ok(statisticService.farthest());
    }

    @Operation(summary = "Distancia más cercana a Buenos Aires desde las consultas por Ip")
    @GetMapping(path = "closest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseDTO> closest() {
        log.info("[[Closest Request started]]");
        return ResponseEntity.ok(statisticService.closest());
    }

    @Operation(summary = "Distancia promedio de las consultas por Ip")
    @GetMapping(path = "average", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AverageResponseDTO> average() {
        log.info("[[Average Request started]]");
        return ResponseEntity.ok(statisticService.average());
    }

}
