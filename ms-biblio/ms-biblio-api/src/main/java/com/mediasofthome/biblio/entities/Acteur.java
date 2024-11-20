package com.mediasofthome.biblio.entities;

import com.mediasofthome.krnl.entities.Address;
import com.mediasofthome.krnl.entities.BaseAuditEntity;
import com.mediasofthome.krnl.entities.Country;
import com.mediasofthome.krnl.entities.Locality;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Entity
@Table(name = "acteurs")
public class Acteur extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_acteur", nullable = false)
    private String nom;

    @Column(name = "prenom_acteur", nullable = false)
    private String prenom;

    @Column(name = "date_naissance", nullable = true)
    private LocalDate dateNaissance;

    @Column(name = "lieu_naissance", nullable = true)
    private String lieuNaissance;

    @Column(name = "nom_jeune_fille")
    private String nomJeuneFille;

    @Column(name = "poste_occupe", nullable = true)
    private String posteOccupe;

    @Column(name = "personne_a_contacter", nullable = true)
    private String personneAContacter;

    @Column(name = "signataire", nullable = false)
    public boolean signataire;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "telephone", nullable = true)
    private String telephone;

    @Column(name = "actif", nullable = false, columnDefinition = "boolean default false")
    private boolean actif;

    @Column(name = "obligatoire", nullable = false, columnDefinition = "boolean default false")
    private boolean obligatoire;

    @Column(name = "chef", nullable = false, columnDefinition = "boolean default false")
    private boolean chef;

    @Column(name = "analphabete", nullable = false, columnDefinition = "boolean default false")
    private boolean analphabete;

    @Transient
    private String fullName;

    @OneToOne
    @JoinColumn(name = "id_sexe")
    private Sexe sexe;

    @OneToMany(mappedBy = "acteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ActeurTypeOperation> acteurTypeOperations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profession")
    private Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_poste")
    private Poste poste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_secteur_activite")
    private SecteurActivite secteurActivite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_branche_activite")
    private BrancheActivite brancheActivite;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_adresse")
    private Address adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pays_nationalite")
    private Country nationalite;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "id_piece", nullable = true)
    private Piece piece;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_filiation")
    private TypeFiliation typeFiliation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_acteur")
    private TypeActeur typeActeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localite")
    private Locality locality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre")
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entite")
    private Entite entite;

    public Acteur() {
        this.adresse = new Address();
        this.piece = new Piece();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public boolean isSignataire() {
        return signataire;
    }

    public void setSignataire(boolean signataire) {
        this.signataire = signataire;
    }

    public TypeActeur getTypeActeur() {
        return typeActeur;
    }

    public void setTypeActeur(TypeActeur typeActeur) {
        this.typeActeur = typeActeur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<ActeurTypeOperation> getActeurTypeOperations() {
        return acteurTypeOperations;
    }

    public void setActeurTypeOperations(List<ActeurTypeOperation> acteurTypeOperations) {
        this.acteurTypeOperations = acteurTypeOperations;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public TypeFiliation getTypeFiliation() {
        return typeFiliation;
    }

    public void setTypeFiliation(TypeFiliation typeFiliation) {
        this.typeFiliation = typeFiliation;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getFullName() {
        return this.nom + " " + this.prenom;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getPosteOccupe() {
        return posteOccupe;
    }

    public void setPosteOccupe(String posteOccupe) {
        this.posteOccupe = posteOccupe;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public boolean isChef() {
        return chef;
    }

    public void setChef(boolean chef) {
        this.chef = chef;
    }

    public boolean isAnalphabete() {
        return analphabete;
    }

    public void setAnalphabete(boolean analphabete) {
        this.analphabete = analphabete;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getPersonneAContacter() {
        return personneAContacter;
    }

    public void setPersonneAContacter(String personneAContacter) {
        this.personneAContacter = personneAContacter;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Address getAdresse() {
        return adresse;
    }

    public void setAdresse(Address adresse) {
        this.adresse = adresse;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public SecteurActivite getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public BrancheActivite getBrancheActivite() {
        return brancheActivite;
    }

    public void setBrancheActivite(BrancheActivite brancheActivite) {
        this.brancheActivite = brancheActivite;
    }

    public Country getNationalite() {
        return nationalite;
    }

    public void setNationalite(Country nationalite) {
        this.nationalite = nationalite;
    }
    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    @PostLoad
    public void setFullName() {
        this.fullName = this.nom + " " + this.prenom;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Acteur other = (Acteur) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Acteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", lieuNaissance=" + lieuNaissance + ", nomJeuneFille=" + nomJeuneFille + ", posteOccupe=" + posteOccupe + ", personneAContacter=" + personneAContacter + ", signataire=" + signataire + ", email=" + email + ", telephone=" + telephone + ", actif=" + actif + ", obligatoire=" + obligatoire + ", chef=" + chef + ", analphabete=" + analphabete + ", fullName=" + fullName + ", sexe=" + sexe + ", acteurTypeOperations=" + acteurTypeOperations + ", profession=" + profession + ", poste=" + poste + ", secteurActivite=" + secteurActivite + ", brancheActivite=" + brancheActivite + ", nationalite=" + nationalite + ", typeFiliation=" + typeFiliation + ", typeActeur=" + typeActeur + ", membre=" + membre + ", entite=" + entite + '}';
    }

}
