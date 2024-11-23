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
@Table(name = "emprunts")
public class Emprunt extends EntiteBasique {

    @Column(nullable = true)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emprunteur")
    private Emprunteur emprunteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livre")
    private Livre livre;

    public Emprunt() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Emprunt{" + "date=" + date + ", emprunteur=" + emprunteur + ", livre=" + livre + '}';
    }
}
