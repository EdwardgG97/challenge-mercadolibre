package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AverageDTO(
        @JsonProperty("distancia_promedio") String average) {
}
