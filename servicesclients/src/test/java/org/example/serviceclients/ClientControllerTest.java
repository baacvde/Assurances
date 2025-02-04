package org.example.serviceclients;


import org.example.common.models.ClientDTO;
import org.example.serviceclients.controller.ClientController;
import org.example.serviceclients.repository.ClientRepository;
import org.example.serviceclients.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Parasoft Jtest UTA: Test class for ClientController
 *
 * @see ClientController
 * @author diop
 */
public class ClientControllerTest
{
    // Parasoft Jtest UTA: Object under test
    @InjectMocks
    ClientController underTest;

    // Parasoft Jtest UTA: Dependency generated for field clientService in ClientController
    @Mock
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    // Parasoft Jtest UTA: Initialize object under test with mocked dependencies
    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Parasoft Jtest UTA: Test for obtenirClients()
     *
     * @author diop
     * @see ClientController#obtenirClients()
     */
    @Test(timeout = 5000)
    public void testObtenirClients() throws Throwable {
        // Given
        List<ClientDTO> obtenirClientsResult = new ArrayList<ClientDTO>(); // UTA: default value
        ClientDTO item = mock(ClientDTO.class);
        obtenirClientsResult.add(item);
        doReturn(obtenirClientsResult).when(clientService).obtenirClients();

        // When
        ResponseEntity<List<ClientDTO>> result = underTest.obtenirClients();

    }

    /**
     * Parasoft Jtest UTA: Test for obtenirClientParId(Long)
     *
     * @author diop
     * @see ClientController#obtenirClientParId(Long)
     */
    @Test(timeout = 5000)
    public void testObtenirClientParId() throws Throwable {
        // Given
        ClientDTO obtenirClientParIdResult = mock(ClientDTO.class);
        when(clientService.obtenirClientParId(nullable(Long.class))).thenReturn(obtenirClientParIdResult);

        // When
        Long id = 1L; // UTA: default value
        ResponseEntity<ClientDTO> result = underTest.obtenirClientParId(id);

    }

    /**
     * Parasoft Jtest UTA: Test for creerClient(ClientDTO)
     *
     * @author diop
     * @see ClientController#creerClient(ClientDTO)
     */
    @Test()
    public void testCreerClient() throws Throwable {
        // Given
        boolean estEmailValideResult = false; // UTA: provided value
        when(clientService.estEmailValide(nullable(String.class))).thenReturn(estEmailValideResult);

        // When
        ClientDTO clientDTO = mock(ClientDTO.class);
        ResponseEntity<ClientDTO> result = underTest.creerClient(clientDTO);

    }

    /**
     * Parasoft Jtest UTA: Test for creerClient(ClientDTO)
     *
     * @author diop
     * @see ClientController#creerClient(ClientDTO)
     */
    @Test()
    public void testCreerClient2() throws Throwable {
        // Given
        boolean estEmailValideResult = true; // UTA: provided value
        when(clientService.estEmailValide(nullable(String.class))).thenReturn(estEmailValideResult);

        ClientDTO creerClientResult = mock(ClientDTO.class);
        when(clientService.creerClient(nullable(ClientDTO.class))).thenReturn(creerClientResult);

        // When
        ClientDTO clientDTO = mock(ClientDTO.class);
        ResponseEntity<ClientDTO> result = underTest.creerClient(clientDTO);

    }
}
