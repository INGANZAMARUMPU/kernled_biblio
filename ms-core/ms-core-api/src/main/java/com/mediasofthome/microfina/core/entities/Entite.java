package com.mediasofthome.biblio.core.entities;

import com.mediasofthome.krnl.entities.Address;
import com.mediasofthome.krnl.entities.KEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author mawuli
 */
@Entity
@DiscriminatorValue(value = "ENT")
public class Entite extends KEntity {

    @Column(name = "denomination", nullable = true, length = 31)
    private String denomination;

    @Column(name = "numero_approuve", nullable = true, length = 31)
    private String numeroApprouve;
    
    public Entite() {
        this.address = new Address();
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getNumeroApprouve() {
        return numeroApprouve;
    }

    public void setNumeroApprouve(String numeroApprouve) {
        this.numeroApprouve = numeroApprouve;
    }

}
