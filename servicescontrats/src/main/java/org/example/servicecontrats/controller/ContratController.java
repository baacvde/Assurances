package org.example.servicecontrats.controller;

import org.example.common.models.ContratDTO;
import org.example.servicecontrats.service.ContratService;
import org.example.servicecontrats.mappers.ContratMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {



    @Autowired
    private ContratService contratService;
    @Autowired
    private ContratMapper contratMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ContratDTO> creerContrat(@RequestBody ContratDTO contratDTO) {
        try {
            ContratDTO nouveauContrat = contratService.creerContrat(contratDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauContrat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ContratDTO> obtenirContratParId(@PathVariable Long id) {
        Optional<ContratDTO> contratDTO = contratService.obtenirContratParId(id);
        return contratDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContratDTO> mettreAJourContrat(@PathVariable Long id, @RequestBody ContratDTO updatedContrat) {
        try {
            ContratDTO contratMisAJour = contratService.mettreAJourContrat(id, updatedContrat);
            return ResponseEntity.ok(contratMisAJour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerContrat(@PathVariable Long id) {
        try {
            contratService.supprimerContrat(id);
            return ResponseEntity.ok("Contrat supprimé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContratDTO>> listerContrats() {
        List<ContratDTO> contrats = contratService.contratRepository.findAll().stream().
                map(contratMapper::toContratDTO).collect(Collectors.toList());
        return ResponseEntity.ok(contrats);
    }
}
