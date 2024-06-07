package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.entity.CountryEntity;
import com.mercadolibre.challenge.mapper.CountryMapper;
import com.mercadolibre.challenge.model.AverageResponseDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.repository.CountryRepository;
import com.mercadolibre.challenge.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mercadolibre.challenge.util.Constants.COUNTRY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasuresService implements StatisticService {

    private final CountryRepository repository;
    private final CountryMapper mapper;

    @Cacheable("furthestCountry")
    @Override
    public CountryResponseDTO farthest() {
        Optional<CountryEntity> optionalCountryEntity = repository.findCountryWithMaxDistance();
        CountryEntity countryEntity = optionalCountryEntity.orElseThrow(() ->
                new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()));
        return mapper.entityToDto(countryEntity);
    }

    @Cacheable("nearestCountry")
    @Override
    public CountryResponseDTO closest() {
        Optional<CountryEntity> optionalCountryEntity = repository.findCountryWithMinDistance();
        CountryEntity countryEntity = optionalCountryEntity.orElseThrow(() ->
                new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()));
        return mapper.entityToDto(countryEntity);
    }

    @Cacheable("averageDistances")
    @Override
    public AverageResponseDTO average() {
        Optional<Double> average = repository.calculateWeightedAverageDistance();
        return new AverageResponseDTO(Utils.formatDouble(average.orElseThrow(() ->
                new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()))).concat(" km"));
    }
}
