package org.example.serviceclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * API pour créer un nouveau client.
     */
    @PostMapping
    public ResponseEntity<Client> creerClient(@RequestBody Client client) {
        if (!clientService.estEmailValide(client.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        Client nouveauClient = clientService.creerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauClient);
    }

    /**
     * API pour obtenir tous les clients.
     */
    @GetMapping
    public ResponseEntity<List<Client>> obtenirTousLesClients() {
        List<Client> clients = clientService.clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    /**
     * API pour obtenir un client par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> obtenirClientParId(@PathVariable Long id) {
        Optional<Client> client = clientService.obtenirClientParId(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * API pour supprimer un client par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.ok("Client supprimé avec succès !");
    }
}

