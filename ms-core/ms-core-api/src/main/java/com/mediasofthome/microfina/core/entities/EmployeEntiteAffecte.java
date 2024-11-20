package com.mediasofthome.biblio.core.entities;

import com.mediasofthome.krnl.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Entity
@Table(name = "employe_entite_affectes")
public class EmployeEntiteAffecte extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employe", nullable = false)
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entite", nullable = false)
    private Entite entite;

    @Column(name = "actif", nullable = false)
    private boolean actif;

    @Column(name = "date_affectation", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime dateAffectation;

    @Column(name = "date_suppression", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateSuppression;

    public EmployeEntiteAffecte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public LocalDateTime getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDateTime dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public LocalDateTime getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(LocalDateTime dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
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
        final EmployeEntiteAffecte other = (EmployeEntiteAffecte) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "EmployeEntiteAffecte{" + "id=" + id + ", actif=" + actif + ", dateAffectation=" + dateAffectation + ", dateSuppression=" + dateSuppression + '}';
    }

}
