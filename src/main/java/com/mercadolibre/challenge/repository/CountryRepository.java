package com.mercadolibre.challenge.repository;

import com.mercadolibre.challenge.Entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
