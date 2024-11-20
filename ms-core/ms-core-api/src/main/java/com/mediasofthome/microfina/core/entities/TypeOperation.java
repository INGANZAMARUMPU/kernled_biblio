/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.entities;

import com.mediasofthome.krnl.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Entity
@Table(name = "type_operations")
public class TypeOperation extends BaseEntity {
    
    public static final String CODE_BIBLIO = "ADH";

    @Id
    @Column(name = "code", nullable = false, length = 31)
    private String code;

    @Column(name = "libelle", nullable = false, length = 47)
    private String libelle;

    public TypeOperation() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
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
        final TypeOperation other = (TypeOperation) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "TypeOperation{" + "code=" + code + ", libelle=" 
                + libelle + '}';
    }
}
