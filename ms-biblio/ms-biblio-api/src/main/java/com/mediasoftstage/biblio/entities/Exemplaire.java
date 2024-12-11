package com.mediasoftstage.biblio.entities;

import java.util.Objects;

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

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "exemplaires")
public class Exemplaire extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = true)
    private String situation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profession")
    private Livre livre;

    public Exemplaire() {
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean isDispo(){
        return this.situation.equals(STATES.DISPONIBLE.value);
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
        final Exemplaire other = (Exemplaire) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "numero=" + numero + ", livre=" + livre + '}';
    }

}
