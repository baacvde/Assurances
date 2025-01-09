package org.example.serviceclients;

import org.example.common.models.ClientDTO;
import org.example.serviceclients.ClientService;
import org.example.serviceclients.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<ClientDTO> creerClient(@RequestBody ClientDTO clientDTO) {
        if (!clientService.estEmailValide(clientDTO.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        ClientDTO nouveauClient = clientService.creerClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> obtenirClientParId(@PathVariable Long id) {
        ClientDTO client = clientService.obtenirClientParId(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> obtenirClients() {
        List<ClientDTO> clients = clientService.clientRepository.findAll().stream().map(clientMapper::toClientDTO).collect(Collectors.toList());
        return ResponseEntity.ok(clients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerClientParId(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.ok("Client supprimé avec succès !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifierClientParId(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientService.mettreAJourClient(id, clientDTO);
            return ResponseEntity.ok("Le client a été modifié avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue.");
        }
    }







}
