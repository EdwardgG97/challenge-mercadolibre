package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CountryResponseDTO (
        @JsonProperty("ip") String ip,
        @JsonProperty("fecha_actual") String create,
        @JsonProperty("distancia_aproximada") String distance,
        @JsonProperty("iso_code") String countryCode,
        @JsonProperty("pais") String countryName,
        @JsonProperty("latitud") Double latitude,
        @JsonProperty("longitud") Double longitude,
        @JsonProperty("idiomas") List<LanguageResponseDTO> languages
) {   }
