package com.mercadolibre.challenge.mapper;

import com.mercadolibre.challenge.Entity.CountryEntity;
import com.mercadolibre.challenge.model.CountryDto;
import com.mercadolibre.challenge.model.CountryDtoOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    /*
    @Mappings({
            @Mapping(target = "languages", source = "languagesEntity"),
            @Mapping(target = "distance", expression = "java(entity.getEstimatedDistance().toString().concat(\" Kms\"))")
    })
    CountryDtoOut countryToCountryDtoOut(CountryEntity entity);

    @Mappings({
            @Mapping(target = "languagesEntity", source = "location.languages"),
            @Mapping(target = "create", expression = "java(LocalDateTime.now())", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "distance", expression = "java(Utils.calculateDistance(countryDto.getLatitude(), countryDto.getLongitude()))")
    })
    CountryEntity dtoToEntity(CountryDto countryDto);
    */
}
