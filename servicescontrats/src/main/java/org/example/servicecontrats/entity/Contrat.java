package org.example.servicecontrats.entity;

import jakarta.persistence.*;

import org.example.serviceclients.entity.Client;



import java.time.LocalDate;

@Entity
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Le client est obligatoire

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Référence au client

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montantAssure;
    private boolean estValide;
    private int dureeEnAnnees;

    public Contrat() {
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    //public void setClient(ClientDTO clientDTO) {
    //    this.client = client;
    //}

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontantAssure() {
        return montantAssure;
    }

    public void setMontantAssure(double montantAssure) {
        this.montantAssure = montantAssure;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public int getDureeEnAnnees() {
        return dureeEnAnnees;
    }

    public void setDureeEnAnnees(int dureeEnAnnees) {
        this.dureeEnAnnees = dureeEnAnnees;
    }


    public Long getClientId() {
        return client != null ? client.getId() : null; // Evite le NullPointerException
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isNew() {
        return true;
    }
}
