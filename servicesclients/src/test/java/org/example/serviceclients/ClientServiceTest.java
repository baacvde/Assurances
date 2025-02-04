package org.example.serviceclients;


import org.example.common.models.ClientDTO;
import org.example.serviceclients.entity.Client;
import org.example.serviceclients.mapper.ClientMapper;
import org.example.serviceclients.repository.ClientRepository;
import org.example.serviceclients.service.ClientService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Parasoft Jtest UTA: Test class for ClientService
 *
 * @see ClientService
 * @author diop
 */
public class ClientServiceTest
{
    /**
     * Parasoft Jtest UTA: Test for obtenirClients()
     *
     * @author diop
     * @see ClientService#obtenirClients()
     */
    @Test(timeout = 5000)
    public void testObtenirClients() throws Throwable {
        // Given
        ClientRepository clientRepository = mock(ClientRepository.class);
        List<Client> findAllResult = new ArrayList<Client>(); // UTA: default value
        Client item = mock(Client.class);
        findAllResult.add(item);
        doReturn(findAllResult).when(clientRepository).findAll();
        ClientMapper clientMapper = mock(ClientMapper.class);
        ClientService underTest = new ClientService(clientRepository, clientMapper);

        // When
        List<ClientDTO> result = underTest.obtenirClients();

    }

    /**
     * Parasoft Jtest UTA: Test for obtenirClientParId(Long)
     *
     * @author diop
     * @see ClientService#obtenirClientParId(Long)
     */
    @Test(timeout = 5000)
    public void testObtenirClientParId() throws Throwable {
        // Given
        ClientRepository clientRepository = mock(ClientRepository.class);
        ClientMapper clientMapper = mock(ClientMapper.class);
        ClientService underTest = new ClientService(clientRepository, clientMapper);

        // When
        Long id = 1L; // UTA: default value
        ClientDTO result = underTest.obtenirClientParId(id);

    }

    /**
     * Parasoft Jtest UTA: Test for creerClient(ClientDTO)
     *
     * @author diop
     * @see ClientService#creerClient(ClientDTO)
     */
    @Test
    public void testCreerClient() throws Throwable {
        // Given
        ClientRepository clientRepository = mock(ClientRepository.class);
        boolean existsByEmailResult = false; // UTA: provided value
        when(clientRepository.existsByEmail(nullable(String.class))).thenReturn(existsByEmailResult);

        Client saveResult = mock(Client.class);
        when(clientRepository.save(nullable(Client.class))).thenReturn(saveResult);
        ClientMapper clientMapper = mock(ClientMapper.class);
        Client toClientResult = mock(Client.class);
        when(clientMapper.toClient(nullable(ClientDTO.class))).thenReturn(toClientResult);
        ClientService underTest = new ClientService(clientRepository, clientMapper);

        // When
        ClientDTO clientDTO = mock(ClientDTO.class);
        ClientDTO result = underTest.creerClient(clientDTO);

    }

    /**
     * Parasoft Jtest UTA: Test for creerClient(ClientDTO)
     *
     * @author diop
     * @see ClientService#creerClient(ClientDTO)
     */
    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void testCreerClient2() throws Throwable {
        // Given
        ClientRepository clientRepository = mock(ClientRepository.class);
        boolean existsByEmailResult = true; // UTA: provided value
        when(clientRepository.existsByEmail(nullable(String.class))).thenReturn(existsByEmailResult);
        ClientMapper clientMapper = mock(ClientMapper.class);
        Client toClientResult = mock(Client.class);
        when(clientMapper.toClient(nullable(ClientDTO.class))).thenReturn(toClientResult);
        ClientService underTest = new ClientService(clientRepository, clientMapper);

        // When
        ClientDTO clientDTO = mock(ClientDTO.class);
        underTest.creerClient(clientDTO);

    }
}
