package com.mediasoftstage.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "exemplaires")
public class Exemplaire extends EntiteBasique {

    @Column(nullable = false)
    private String numero;

    @Column(nullable = true)
    private String situation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profession")
    private Livre livre;

    public Exemplaire() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "numero=" + numero + ", livre=" + livre + '}';
    }

}
