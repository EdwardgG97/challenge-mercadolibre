package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.repository.CountryRepository;
import com.mercadolibre.challenge.Entity.CountryEntity;
import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.mapper.CountryMapper;
import com.mercadolibre.challenge.model.CountryDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.mercadolibre.challenge.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class IpapiService implements IpService {

    private final RestTemplate restTemplate;
    private final CountryRepository repository;
    private final CountryMapper mapper;
    @Value("${ipapi.url.host}")
    private String host;
    @Value("${ipapi.api.key}")
    private String apiKey;

    @Override
    public CountryResponseDTO findByIp(String ip) throws IpNotFoundException, AccessKeyException, RequestException {
        try {
            URI uri = UriComponentsBuilder.fromUriString(host)
                    .queryParam(ACCESS_KEY.getMsg(), apiKey)
                    .queryParam(LANGUAGE.getMsg(), ES.getMsg())
                    .buildAndExpand(ip)
                    .toUri();
            log.info("URI: {}", uri);
            var response = restTemplate.getForObject(uri, CountryDTO.class);
            return handleResponse(response);
        } catch (RestClientException ex) {
            throw new RequestException(ex.getMessage());
        }
    }

    private CountryResponseDTO handleResponse(CountryDTO response) throws AccessKeyException, IpNotFoundException, RequestException {
        if (response.getError() != null) {
            handleResponseError(response.getError());
        }
        CountryEntity entity = repository.save(mapper.dtoToEntity(response));
        return mapper.entityToDto(entity);
    }

    private void handleResponseError(CountryDTO.ErrorDTO error) throws IpNotFoundException, AccessKeyException, RequestException {
        int errorCode = error.getCode();
        switch (errorCode) {
            case 106:
                throw new IpNotFoundException(error.getInfo());
            case 101:
                throw new AccessKeyException(error.getInfo());
            default:
                throw new RequestException(error.getInfo());
        }
    }

}
