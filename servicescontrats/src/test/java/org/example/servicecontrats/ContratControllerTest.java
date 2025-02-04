package org.example.servicecontrats;


import org.example.common.models.ContratDTO;
import org.example.servicecontrats.controller.ContratController;
import org.example.servicecontrats.service.ContratService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Parasoft Jtest UTA: Test class for ContratController
 *
 * @see ContratController
 * @author diop
 */
public class ContratControllerTest
{
    // Parasoft Jtest UTA: Object under test
    @InjectMocks
    ContratController underTest;

    // Parasoft Jtest UTA: Dependency generated for field contratService in ContratController
    @Mock
    ContratService contratService;

    // Parasoft Jtest UTA: Initialize object under test with mocked dependencies
    @BeforeEach
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Parasoft Jtest UTA: Test for creerContrat(ContratDTO)
     *
     * @author diop
     * @see ContratController#creerContrat(ContratDTO)
     */
    @Test
    @Timeout(value = 5, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testCreerContrat() throws Throwable {
        // Given
        ContratDTO creerContratResult = mock(ContratDTO.class);
        when(contratService.creerContrat(nullable(ContratDTO.class))).thenReturn(creerContratResult);

        // When
        ContratDTO contratDTO = mock(ContratDTO.class);
        ResponseEntity<ContratDTO> result = underTest.creerContrat(contratDTO);

    }

    /**
     * Parasoft Jtest UTA: Test for creerContrat(ContratDTO)
     *
     * @author diop
     * @see ContratController#creerContrat(ContratDTO)
     */
    @Test
    @Timeout(value = 5, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testCreerContrat2() throws Throwable {
        // Given
        when(contratService.creerContrat(nullable(ContratDTO.class))).thenThrow(IllegalArgumentException.class);

        // When
        ContratDTO contratDTO = mock(ContratDTO.class);
        ResponseEntity<ContratDTO> result = underTest.creerContrat(contratDTO);

    }
}

