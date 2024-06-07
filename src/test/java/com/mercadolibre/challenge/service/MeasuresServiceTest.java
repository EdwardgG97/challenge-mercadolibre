package com.mercadolibre.challenge.service;

import com.mercadolibre.challenge.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MeasuresServiceTest {

    @Mock
    private CountryRepository repository;

    @InjectMocks
    private MeasuresService measuresService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFarthestEntityNotFoundException() {
        when(repository.findCountryWithMaxDistance()).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> measuresService.farthest());
    }

    @Test
    void testClosestEntityNotFoundException() {
        when(repository.findCountryWithMaxDistance()).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> measuresService.closest());
    }

    @Test
    void testAverageEntityNotFoundException() {
        when(repository.findCountryWithMaxDistance()).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> measuresService.average());
    }
}