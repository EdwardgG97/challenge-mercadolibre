package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryInformation implements Serializable {

    private String ip;
    @JsonProperty("fecha_actual")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime date;
    @JsonProperty("distancia_estimada")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double distanciaEstimada;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_name")
    private String countryName;
    private double latitude;
    private double longitude;
    private LocationDTO location;
    @JsonIgnore
    private ErrorDTO error;

    @Getter
    @Setter
    public static class LocationDTO {
        private List<LanguageDTO> languages;
    }

    @Getter
    @Setter
    public static class LanguageDTO {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    public static class ErrorDTO {
        private Integer code;
    }
}

