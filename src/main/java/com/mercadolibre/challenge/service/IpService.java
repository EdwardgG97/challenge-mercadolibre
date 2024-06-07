package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.model.CountryResponseDTO;

public interface IpService {

    CountryResponseDTO findByIp(String ip) throws IpNotFoundException, AccessKeyException, RequestException;
}
