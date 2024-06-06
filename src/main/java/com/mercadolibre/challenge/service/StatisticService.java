package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.model.AverageDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;

public interface StatisticService {

    CountryResponseDTO farthest();
    CountryResponseDTO closest();
    AverageDTO average();
}
