package com.mercadolibre.challenge.mapper;

import com.mercadolibre.challenge.Entity.CountryEntity;
import com.mercadolibre.challenge.model.CountryDto;
import com.mercadolibre.challenge.model.CountryDtoOut;
import com.mercadolibre.challenge.util.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CountryMapper2 {

    public CountryDtoOut entityToDto(CountryEntity entity) {
        return CountryDtoOut.builder()
                .ip(entity.getIp())
                .create(entity.getCreate().toString())
                .distance(entity.getDistance().toString().concat("Kms"))
                .countryCode(entity.getCountryCode())
                .countryName(entity.getCountryName())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .languages(entity.getLanguagesEntity().stream()
                        .map(languageEntity -> {
                    var languageDTO = new CountryDtoOut.LanguageDTO();
                    languageDTO.setCode(languageEntity.getCode());
                    languageDTO.setName(languageEntity.getName());
                    return languageDTO;
                }).collect(Collectors.toList()))
                .build();
    }

    public CountryEntity dtoToEntity(CountryDto dto) {
        var entity = new CountryEntity();
        entity.setIp(dto.getIp());
        entity.setCreate(LocalDateTime.now());
        entity.setDistance(Utils.calculateDistance(dto.getLatitude(), dto.getLongitude()));
        entity.setCountryCode(dto.getCountryCode());
        entity.setCountryName(dto.getCountryName());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setLanguagesEntity(dto.getLocation().getLanguages().stream()
                .map(languageDTO -> {
            var languageEntity = new CountryEntity.LanguageEntity();
            languageEntity.setCode(languageDTO.getCode());
            languageEntity.setName(languageDTO.getName());
            return languageEntity;
        }).collect(Collectors.toList()));
        return entity;
    }
}
