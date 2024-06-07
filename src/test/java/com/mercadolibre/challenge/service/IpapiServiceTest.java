package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.entity.CountryEntity;
import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.mapper.CountryMapper;
import com.mercadolibre.challenge.model.CountryDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.model.LanguageResponseDTO;
import com.mercadolibre.challenge.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class IpapiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CountryRepository repository;

    @Mock
    private CountryMapper mapper;

    @InjectMocks
    private IpapiService ipapiService;

    private final String host = "http://api.ipapi.com";
    private final String apiKey = "test_api_key";
    private final String ip = "123.456.789.321";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(ipapiService, "host", host);
        ReflectionTestUtils.setField(ipapiService, "apiKey", apiKey);
    }

    @Test
    void testFindByIpSuccess() throws IpNotFoundException, AccessKeyException, RequestException {
        var countryDTO = new CountryDTO (
                "123.123.123"
                , "CO"
                , "Colombia"
                , 123.456
                , -123.456
                , new CountryDTO.LocationDTO(List.of(new CountryDTO.LanguageDTO("es", "Español")))
                , null);
        var countryEntity = new CountryEntity();
        var expectedResponse = new CountryResponseDTO(
                "123.123.123"
                , "2024-06-06"
                , "9876,54321 km"
                , "CO"
                , "Colombia"
                , 123.456
                , -123.456
                , List.of(new LanguageResponseDTO("es", "Español")));

        when(restTemplate.getForObject(any(URI.class), eq(CountryDTO.class))).thenReturn(countryDTO);
        when(mapper.dtoToEntity(countryDTO)).thenReturn(countryEntity);
        when(repository.save(countryEntity)).thenReturn(countryEntity);
        when(mapper.entityToDto(countryEntity)).thenReturn(expectedResponse);

        var actualResponse = ipapiService.findByIp(ip);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testRestClientException() {
        when(restTemplate.getForObject(any(URI.class), eq(CountryDTO.class))).thenThrow(new RestClientException("Error"));
        assertThrows(RequestException.class, () -> ipapiService.findByIp(ip));
    }

    @Test
    void testIpNotFoundException() {
        var errorDTO = new CountryDTO.ErrorDTO(106, "The IP Address supplied is invalid");
        var countryDTO = new CountryDTO (null,null,null,0.0, 0.0, null,errorDTO);
        when(restTemplate.getForObject(any(URI.class), eq(CountryDTO.class))).thenReturn(countryDTO);
        assertThrows(IpNotFoundException.class, () -> ipapiService.findByIp(ip));
    }

    @Test
    void testAccessKeyException() {
        var errorDTO = new CountryDTO.ErrorDTO(101, "You have not supplied a valid API Access Key");
        var countryDTO = new CountryDTO (null,null,null,0.0, 0.0, null, errorDTO);
        when(restTemplate.getForObject(any(URI.class), eq(CountryDTO.class))).thenReturn(countryDTO);
        assertThrows(AccessKeyException.class, () -> ipapiService.findByIp(ip));
    }

    @Test
    void testRequestException() {
        var errorDTO = new CountryDTO.ErrorDTO(301, "One or more invalid fields were specified using the fields parameter");
        var countryDTO = new CountryDTO (null,null,null,0.0, 0.0, null, errorDTO);
        when(restTemplate.getForObject(any(URI.class), eq(CountryDTO.class))).thenReturn(countryDTO);
        assertThrows(RequestException.class, () -> ipapiService.findByIp(ip));
    }

}