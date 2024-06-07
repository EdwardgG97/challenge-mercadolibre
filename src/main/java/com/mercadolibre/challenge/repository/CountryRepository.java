package com.mercadolibre.challenge.repository;

import com.mercadolibre.challenge.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    @Query(value = "SELECT * FROM country WHERE distance = (SELECT MAX(distance) FROM country) LIMIT 1", nativeQuery = true)
    Optional<CountryEntity> findCountryWithMaxDistance();

    @Query(value = "SELECT * FROM country WHERE distance = (SELECT MIN(distance) FROM country) LIMIT 1", nativeQuery = true)
    Optional<CountryEntity> findCountryWithMinDistance();

    @Query(value = "SELECT SUM(distance * invocations) / SUM(invocations) " +
            "FROM (SELECT country_name, distance, COUNT(*) AS invocations FROM country GROUP BY country_name) AS country_data", nativeQuery = true)
    Optional<Double> calculateWeightedAverageDistance();
}
