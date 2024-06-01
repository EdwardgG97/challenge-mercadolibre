package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.dto.CountryInformationDTO;
import com.mercadolibre.challenge.exception.IpNotFoundException;

public interface IpService {

    CountryInformationDTO findByIp(String ip) throws IpNotFoundException;
}
