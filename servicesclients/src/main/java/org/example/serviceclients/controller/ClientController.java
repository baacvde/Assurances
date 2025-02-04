package org.example.serviceclients.controller;

import org.example.common.models.ClientDTO;
import org.example.serviceclients.service.ClientService;
import org.example.serviceclients.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> creerClient(@RequestBody ClientDTO clientDTO) {
        if (!clientService.estEmailValide(clientDTO.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        ClientDTO nouveauClient = clientService.creerClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauClient);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> obtenirClientParId(@PathVariable Long id) {
        ClientDTO client = clientService.obtenirClientParId(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> obtenirClients() {
        List<ClientDTO> clients = clientService.obtenirClients();
        return ResponseEntity.ok(clients);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removerClientParId(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.ok("Client supprimé avec succès !");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
