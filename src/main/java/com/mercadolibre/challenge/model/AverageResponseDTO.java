package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AverageResponseDTO(
        @JsonProperty("distancia_promedio") String average) {
}
