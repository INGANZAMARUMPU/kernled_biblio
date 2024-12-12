package com.mediasoftstage.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

import com.mediasofthome.krnl.entities.BaseEntity;

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "emprunts")
public class Emprunt extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column()
    private LocalDate date = LocalDate.now();

    @Column(nullable = true)
    private LocalDate date_retour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emprunteur")
    private Emprunteur emprunteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exemplaire")
    private Exemplaire exemplaire;

    public Emprunt() {
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprunt other = (Emprunt) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Emprunt{" + "date=" + date + ", emprunteur=" + emprunteur + ", exemplaire=" + exemplaire + '}';
    }
}
