package com.mediasoftstage.biblio.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "emprunteurs")
public class Emprunteur extends EntiteBasique {
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = true)
    private String addresse;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String email;

    @OneToMany(mappedBy = "emprunteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "emprunteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Abonnement> abonnements;

    public Emprunteur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public Boolean hasAbonnement(){
        Boolean resultat = false;
        for (Abonnement abonnement : abonnements) {
            if (abonnement.getDate_fin().isAfter(LocalDate.now())) {
                return true;
            }
        }
        return resultat;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

}
