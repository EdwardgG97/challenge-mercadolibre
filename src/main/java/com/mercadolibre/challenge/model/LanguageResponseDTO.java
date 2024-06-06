package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LanguageResponseDTO (
        @JsonProperty("idioma_code") String code,
        @JsonProperty("idioma") String name) {
}
