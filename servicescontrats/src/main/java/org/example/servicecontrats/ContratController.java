package org.example.servicecontrats;

import org.example.common.models.ContratDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @PostMapping
    public ResponseEntity<ContratDTO> creerContrat(@RequestBody ContratDTO contratDTO) {
        try {
            ContratDTO nouveauContrat = contratService.creerContrat(contratDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauContrat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratDTO> obtenirContratParId(@PathVariable Long id) {
        Optional<ContratDTO> contratDTO = contratService.obtenirContratParId(id);
        return contratDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratDTO> mettreAJourContrat(@PathVariable Long id, @RequestBody ContratDTO updatedContrat) {
        try {
            ContratDTO contratMisAJour = contratService.mettreAJourContrat(id, updatedContrat);
            return ResponseEntity.ok(contratMisAJour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerContrat(@PathVariable Long id) {
        try {
            contratService.supprimerContrat(id);
            return ResponseEntity.ok("Contrat supprimé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
