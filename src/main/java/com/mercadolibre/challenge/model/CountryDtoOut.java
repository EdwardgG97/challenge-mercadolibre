package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CountryDtoOut {

    private String ip;

    @JsonProperty("fecha_actual")
    private String create;

    @JsonProperty("distancia")
    private String distance;

    @JsonProperty("iso_code")
    private String countryCode;

    @JsonProperty("pais")
    private String countryName;

    @JsonProperty("latitud")
    private Double latitude;

    @JsonProperty("longitud")
    private Double longitude;

    @JsonProperty("idiomas")
    private List<LanguageDTO> languages;

    @Getter
    @Setter
    public static class LanguageDTO {
        @JsonProperty("idioma_code")
        private String code;
        @JsonProperty("idioma")
        private String name;
    }

}
