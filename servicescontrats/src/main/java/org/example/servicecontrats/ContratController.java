package org.example.servicecontrats;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    /**
     * API pour créer un contrat.
     */
    @PostMapping
    public ResponseEntity<Contrat> creerContrat(@RequestBody Contrat contrat) {
        try {
            Contrat nouveauContrat = contratService.creerContrat(contrat);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauContrat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    /**
     * API pour obtenir un contrat par ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> obtenirContratParId(@PathVariable Long id) {
        Optional<Contrat> contrat = contratService.obtenirContratParId(id);
        return contrat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * API pour mettre à jour un contrat.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contrat> mettreAJourContrat(@PathVariable Long id, @RequestBody Contrat updatedContrat) {
        try {
            Contrat contrat = contratService.mettreAJourContrat(id, updatedContrat);
            return ResponseEntity.ok(contrat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * API pour supprimer un contrat.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerContrat(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.ok("Contrat supprimé avec succès !");
    }
}

