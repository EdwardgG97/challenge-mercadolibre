package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.exception.AccessKeyException;
import com.mercadolibre.challenge.exception.IpNotFoundException;
import com.mercadolibre.challenge.exception.RequestException;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.model.LanguageResponseDTO;
import com.mercadolibre.challenge.service.IpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.mercadolibre.challenge.util.Constants.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IpController.class)
class IpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IpService service;

    private CountryResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        responseDTO = new CountryResponseDTO(
                "123.123.123",
                "2024-06-06",
                "123.456 km",
                "CO",
                "Colombia",
                123.456,
                123.456,
                List.of(new LanguageResponseDTO("ES", "Español")));
    }

    @Test
    void testFindByIpSuccess() throws Exception {
        String validIp = "192.168.0.1";

        given(service.findByIp(validIp)).willReturn(responseDTO);

        mockMvc.perform(get("/api/ip/{ip}", validIp)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pais").value("Colombia"))
                .andExpect(jsonPath("$.iso_code").value("CO"));
    }

    @Test
    void testFindByIpInvalidIpFormat() throws Exception {
        String invalidIp = "invalidIp";

        mockMvc.perform(get("/api/ip/{ip}", invalidIp)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid IP address format"));
    }

    @Test
    void testFindByIpIpNotFoundException() throws Exception {
        String validIp = "192.168.0.1";
        given(service.findByIp(anyString())).willThrow(new IpNotFoundException(IP_NOT_FOUND.getMsg()));

        mockMvc.perform(get("/api/ip/{ip}", validIp)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(IP_NOT_FOUND.getMsg()));
    }

    @Test
    void testFindByIpAccessKeyException() throws Exception {
        String validIp = "192.168.0.1";
        given(service.findByIp(anyString())).willThrow(new AccessKeyException(INVALID_ACCESS_KEY.getMsg()));

        mockMvc.perform(get("/api/ip/{ip}", validIp)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(INVALID_ACCESS_KEY.getMsg()));
    }

    @Test
    void testFindByIpRequestException() throws Exception {
        String validIp = "192.168.0.1";
        given(service.findByIp(anyString())).willThrow(new RequestException(UNKNOWN_ERROR.getMsg()));

        mockMvc.perform(get("/api/ip/{ip}", validIp)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(UNKNOWN_ERROR.getMsg()));
    }
}