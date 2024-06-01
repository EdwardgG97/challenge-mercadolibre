package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.dto.CountryInformationDTO;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    public CountryInformationDTO findByIp(String ip) throws IpNotFoundException {
        log.debug("Start Service");
        try {
            URI uri = UriComponentsBuilder.fromUriString(host).queryParam("access_key", apiKey).buildAndExpand(ip).toUri();
            log.info("URI: {}", uri.toString());
            return restTemplate.getForObject(uri, CountryInformationDTO.class);
        } catch (Exception ex) {
            throw new IpNotFoundException(ex.getMessage());
        }
    }
}
