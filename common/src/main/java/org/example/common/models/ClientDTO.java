package org.example.common.models;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateDeNaissance;
    private String adresse;

    public ClientDTO(long id, String nom, String prenom, String email, LocalDate dateDeNaissance, String adresse) {
    }

    public ClientDTO() {}
    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAge() {
        return  LocalDate.now().getYear() - dateDeNaissance.getYear();
    }
}
