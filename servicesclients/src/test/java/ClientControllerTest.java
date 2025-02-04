
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.models.ClientDTO;
import org.example.serviceclients.entity.Client;
import org.example.serviceclients.controller.ClientController;
import org.example.serviceclients.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNom("John Doe");
        client.setPrenom("Doe");
        client.setAdresse("123 Main Street");
        client.setDateDeNaissance(LocalDate.of(1993, 12, 12));
        client.setEmail("johndoe@example.com");

        when(clientService.clientRepository.findById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.adresse").value("123 Main Street"))
                .andExpect(jsonPath("$.dateDeNaissance").value("1993-12-12"));
    }

    @Test
    void testcreerClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNom("Jane Doe");
        client.setEmail("janedoe@example.com");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("Jane Doe");
        clientDTO.setEmail("janedoe@example.com");

        when(clientService.creerClient(Mockito.any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("janedoe@example.com"));
    }
}
