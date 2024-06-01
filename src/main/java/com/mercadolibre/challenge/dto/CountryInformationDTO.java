package com.mercadolibre.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CountryInformationDTO implements Serializable {


    private String ip; //OK

    private String type;

    @JsonProperty("continent_code")
    private String continentCode;

    @JsonProperty("continent_name")
    private String continentName;

    @JsonProperty("country_code")
    private String countryCode; //OK

    @JsonProperty("country_name")
    private String countryName; //OK

    @JsonProperty("region_code")
    private String regionCode;

    @JsonProperty("region_name")
    private String regionName;

    private String city;
    private String zip;
    private double latitude; //OK
    private double longitude; //OK

    private LocationDTO location;

    @Getter
    @Setter
    public static class LocationDTO {

        @JsonProperty("geoname_id")
        private int geonameId;

        private String capital;
        private List<LanguageDTO> languages; //OK

        @JsonProperty("country_flag")
        private String countryFlag;

        @JsonProperty("country_flag_emoji")
        private String countryFlagEmoji;

        @JsonProperty("country_flag_emoji_unicode")
        private String countryFlagEmojiUnicode;

        @JsonProperty("calling_code")
        private String callingCode;

        @JsonProperty("is_eu")
        private boolean isEu;
    }

    @Getter
    @Setter
    public static class LanguageDTO {

        private String code;//OK
        private String name;//OK

    }
}

