package com.mercadolibre.challenge.mapper;

import com.mercadolibre.challenge.Entity.CountryEntity;
import com.mercadolibre.challenge.Entity.LanguageEntity;
import com.mercadolibre.challenge.model.CountryDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.model.LanguageResponseDTO;
import com.mercadolibre.challenge.util.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryResponseDTO entityToDto(CountryEntity entity) {
        return new CountryResponseDTO(
                entity.getIp(),
                Utils.formatDate(entity.getCreate()),
                Utils.formatDouble(entity.getDistance()).concat(" km"),
                entity.getCountryCode(),
                entity.getCountryName(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getLanguagesEntity().stream()
                        .map(languageEntity -> new LanguageResponseDTO(
                                languageEntity.getCode(),
                                languageEntity.getName()
                        )).collect(Collectors.toList())
                );
    }

    public CountryEntity dtoToEntity(CountryDTO dto) {
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
            var languageEntity = new LanguageEntity();
            languageEntity.setCode(languageDTO.getCode());
            languageEntity.setName(languageDTO.getName());
            languageEntity.setCountry(entity);
            return languageEntity;
        }).collect(Collectors.toList()));
        return entity;
    }
}
