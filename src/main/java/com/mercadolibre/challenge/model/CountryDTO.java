package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public record CountryDTO(
        String ip,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("country_name") String countryName,
        double latitude,
        double longitude,
        LocationDTO location,
        ErrorDTO error
) implements Serializable {

    public record LocationDTO(
            List<LanguageDTO> languages
    ) {}

    public record LanguageDTO(
            String code,
            String name
    ) {}

    public record ErrorDTO(
            Integer code,
            String info
    ) {}
}

