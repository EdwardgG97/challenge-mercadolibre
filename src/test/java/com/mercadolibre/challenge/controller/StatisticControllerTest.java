package com.mercadolibre.challenge.controller;

import com.mercadolibre.challenge.model.AverageResponseDTO;
import com.mercadolibre.challenge.model.CountryResponseDTO;
import com.mercadolibre.challenge.model.LanguageResponseDTO;
import com.mercadolibre.challenge.service.StatisticService;
import jakarta.persistence.EntityNotFoundException;
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

import static com.mercadolibre.challenge.util.Constants.COUNTRY_NOT_FOUND;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatisticController.class)
class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService service;

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
    void testFarthestSuccess() throws Exception {
        given(service.farthest()).willReturn(responseDTO);

        mockMvc.perform(get("/api/statistics/farthest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pais").value("Colombia"))
                .andExpect(jsonPath("$.iso_code").value("CO"));
    }

    @Test
    void testFarthestNotFound() throws Exception {
        given(service.farthest()).willThrow(new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()));

        mockMvc.perform(get("/api/statistics/farthest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(COUNTRY_NOT_FOUND.getMsg()));
    }

    @Test
    void testClosestSuccess() throws Exception {
        given(service.closest()).willReturn(responseDTO);

        mockMvc.perform(get("/api/statistics/closest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pais").value("Colombia"))
                .andExpect(jsonPath("$.iso_code").value("CO"));
    }

    @Test
    void testClosestNotFound() throws Exception {
        given(service.closest()).willThrow(new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()));

        mockMvc.perform(get("/api/statistics/closest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(COUNTRY_NOT_FOUND.getMsg()));
    }

    @Test
    void testAverageSuccess() throws Exception {
        given(service.average()).willReturn(new AverageResponseDTO("123,456 km"));

        mockMvc.perform(get("/api/statistics/average").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distancia_promedio").value("123,456 km"));
    }

    @Test
    void testAverageNotFound() throws Exception {
        given(service.average()).willThrow(new EntityNotFoundException(COUNTRY_NOT_FOUND.getMsg()));

        mockMvc.perform(get("/api/statistics/average").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(COUNTRY_NOT_FOUND.getMsg()));
    }
}