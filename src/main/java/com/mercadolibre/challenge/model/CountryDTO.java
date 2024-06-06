package com.mercadolibre.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO implements Serializable {

    private String ip;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    private double latitude;
    private double longitude;
    private LocationDTO location;
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
        private String info;
    }
}
