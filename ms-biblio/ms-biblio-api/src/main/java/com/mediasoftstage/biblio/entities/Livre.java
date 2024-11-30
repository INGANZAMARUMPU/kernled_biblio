package com.mediasoftstage.biblio.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

import com.mediasofthome.krnl.entities.BaseEntity;

/**
 *
 * @author INGANZAMARUMPU
 */
@Entity
@Table(name = "livres")
public class Livre extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(nullable = false)
    private String fonction_auteur;

    @Column(nullable = false)
    private String editeur;

    @Column(nullable = false)
    private Integer anne_edition;

    @Column(nullable = false)
    private String resume;

    @OneToMany(mappedBy = "livre", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Exemplaire> exemplaires;

    public Livre() {
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getFonction_auteur() {
        return fonction_auteur;
    }

    public void setFonction_auteur(String fonction_auteur) {
        this.fonction_auteur = fonction_auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Integer getAnne_edition() {
        return anne_edition;
    }

    public void setAnne_edition(Integer anne_edition) {
        this.anne_edition = anne_edition;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
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
        final Livre other = (Livre) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Livre{" + "isbn=" + isbn + ", titre=" + titre + ", anne_edition=" + anne_edition + '}';
    }
    
}
