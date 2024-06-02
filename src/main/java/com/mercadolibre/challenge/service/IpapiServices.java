package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.model.CountryInformation;
import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

import static com.mercadolibre.challenge.util.Constants.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class IpapiServices implements IpService {

    @Value("${ipapi.url.host}")
    private String host;

    @Value("${ipapi.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CountryInformation findByIp(String ip) throws IpNotFoundException, AccessKeyException, RequestException {
        try {

            URI uri = UriComponentsBuilder.fromUriString(host)
                    .queryParam(ACCESS_KEY.getMsg(), apiKey)
                    .queryParam(LANGUAGE.getMsg(), ES.getMsg()).buildAndExpand(ip).toUri();
            log.info("URI: {}", uri.toString());
            var response = restTemplate.getForObject(uri, CountryInformation.class);

            if (response.getError() != null) {
                handleResponseError(response.getError());
            } else {
                response.setDate(LocalDateTime.now());
                response.setDistanciaEstimada(Utils.calculateDistance(response.getLatitude(), response.getLongitude()));
            }
            return response;

        } catch (RestClientException | NullPointerException ex) {
            throw new RequestException(ex.getMessage());
        }
    }

    private void handleResponseError(CountryInformation.ErrorDTO error) throws IpNotFoundException, AccessKeyException, RequestException {
        int errorCode = error.getCode();
        switch (errorCode) {
            case 106:
                throw new IpNotFoundException(INVALID_IP_ADDRESS.getMsg());
            case 101:
                throw new AccessKeyException(INVALID_ACCESS_KEY.getMsg());
            default:
                throw new RequestException(UNKNOWN_ERROR_CODE.getMsg());
        }
    }

}
