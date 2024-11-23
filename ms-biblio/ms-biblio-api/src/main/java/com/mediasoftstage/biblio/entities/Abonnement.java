package com.mediasoftstage.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "abonnements")
public class Abonnement extends EntiteBasique {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Emprunteur emprunteur;

    @Column(nullable = true)
    private LocalDate date_debut;

    @Column(nullable = true)
    private LocalDate date_fin;

    public Abonnement() {
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "emprunteur=" + emprunteur + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

}
