package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.model.AverageResponseDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;

public interface StatisticService {

    CountryResponseDTO farthest();
    CountryResponseDTO closest();
    AverageResponseDTO average();
}
