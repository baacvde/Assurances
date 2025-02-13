package org.example.common.models;

import java.time.LocalDate;

public class ContratDTO {
    private Long clientId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montantAssure;
    private boolean estValide;
    private int dureeEnAnnees;

    // Getters et setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

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


}
