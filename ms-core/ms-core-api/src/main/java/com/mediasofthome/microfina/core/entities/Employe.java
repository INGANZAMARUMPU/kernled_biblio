package com.mediasofthome.biblio.core.entities;

import com.mediasofthome.krnl.entities.Fonction;
import com.mediasofthome.krnl.entities.Person;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 *
 * @author mawuli
 */
@Entity
@DiscriminatorValue("EP")
public class Employe extends Person {

    @Size(max = 10, message = "Le nombre de caratère ne doit pas dépassé 10")
    @Column(name = "numro_matricule", nullable = true, length = 10)
    private String numeroMatricule;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fonction", nullable = true)
    private Fonction fonction;

    public Employe() {
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public String getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(String numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

}
