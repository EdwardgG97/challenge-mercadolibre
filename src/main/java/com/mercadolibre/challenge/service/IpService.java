package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.model.CountryInformation;
import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;

public interface IpService {

    CountryInformation findByIp(String ip) throws IpNotFoundException, AccessKeyException, RequestException;
}
