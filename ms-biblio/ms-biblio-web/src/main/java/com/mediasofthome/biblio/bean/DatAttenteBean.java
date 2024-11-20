/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasofthome.biblio.domain.MembreVo;
import com.mediasofthome.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.CertificatDat;
import com.mediasofthome.biblio.entities.ChargeDat;
import com.mediasofthome.biblio.entities.Dat;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.biblio.entities.Periodicite;
import com.mediasofthome.biblio.entities.ProduitEpargneDat;
import com.mediasofthome.biblio.entities.ProduitEpargnePlanEpargne;
import com.mediasofthome.biblio.entities.Profession;
import com.mediasofthome.biblio.entities.Sexe;
import com.mediasofthome.biblio.entities.TypeFiliation;
import com.mediasofthome.biblio.service.CertificatDatServiceBeanLocal;
import com.mediasofthome.biblio.service.CompteEpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.DatServiceBeanLocal;
import com.mediasofthome.biblio.service.MembreServiceBeanLocal;
import com.mediasofthome.biblio.service.PeriodiciteServiceBeanLocal;
import com.mediasofthome.biblio.service.ProduitEpargneDatServiceBeanLocal;
import com.mediasofthome.biblio.service.ProfessionServiceBeanLocal;
import com.mediasofthome.biblio.service.SexeServiceBeanLocal;
import com.mediasofthome.biblio.service.TypeFiliationServiceBeanLocal;
import com.mediasofthome.biblio.core.service.EntiteServiceBeanLocal;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author TOKPE Kossi Voltaire
 */
@Named
@ViewScoped
public class DatAttenteBean extends GenericBean<CertificatDat, Long> {

    @EJB
    protected CertificatDatServiceBeanLocal service;
    @EJB
    protected DatServiceBeanLocal datService;
    @EJB
    private EntiteServiceBeanLocal entiteService;
    @EJB
    private MembreServiceBeanLocal membreService;
    @EJB
    private ProduitEpargneDatServiceBeanLocal produitPeService;
    @EJB
    private CompteEpargneServiceBeanLocal compteService;
    @EJB
    protected SexeServiceBeanLocal sexeService;
    @EJB
    protected TypeFiliationServiceBeanLocal typeFiliationService;
    @EJB
    protected ProfessionServiceBeanLocal professionService;
    @EJB
    private PeriodiciteServiceBeanLocal periodiciteService;
    @Inject
    private UserSessionBean userSession;
    private List<Entite> agences;
    private List<MembreVo> membreSources = new ArrayList<>();
    private List<ProduitEpargneDat> produits;
    private List<CompteEpargneVo> dats;
    private List<CompteEpargneVo> virements;
    private List<CompteEpargneVo> comptes;
    private List<TypeFiliation> typeFiliations;
    private List<Sexe> sexes;
    private List<Profession> professions;
    private List<Periodicite> periodicites;
    private List<ChargeDat> chargeDats;

    private Long client;
    private String nomClient;
    private BigDecimal solde = BigDecimal.ZERO;
    private BigDecimal montantDat = BigDecimal.ZERO;
    private BigDecimal interetDat = BigDecimal.ZERO;

    private Dat dat;
    private Membre membre;
    private Acteur beneficiaire;
    private Entite agence;
    private CompteEpargneVo compteDat, compteVirement, compteEpargne;

    @Override
    public void staticFilters(List<FilterParam> filters) {
        filters.add(FilterParam.from("valide", Boolean.FALSE));
    }

    @Override
    public void initAdd() {
        this.entity = new CertificatDat();
        this.beneficiaire = new Acteur();
        this.entity.setAgence((Entite) userSession.getEntity());
        agences = entiteService.getAll(FilterParams.from(FilterParam.from("entityType.label", "Agence")));
        this.init();
    }

    private void init() {
        produits = produitPeService.getAll();
        sexes = sexeService.getAll();
        this.dat = new Dat();
        typeFiliations = typeFiliationService.getAll();
        professions = professionService.getAll();
        periodicites = periodiciteService.getAll();
        agences = entiteService.getAll(FilterParams.from(FilterParam.from("entityType.label", "Agence")));
    }

    @Override
    public void initUpdate() {
        this.entity = this.service.selectionnerUnParId(this.entityId);
        this.beneficiaire = new Acteur();
        client = this.entity.getMembre().getId();
        compteEpargne = new CompteEpargneVo(this.entity.getCompteEpargne().getId(), this.entity.getCompteEpargne().getNumeroCompte(),
                this.entity.getCompteEpargne().getIntitule());
        compteDat = new CompteEpargneVo(this.entity.getCompteDAT().getId(), this.entity.getCompteDAT().getNumeroCompte(),
                this.entity.getCompteDAT().getIntitule());
        agence = this.entity.getAgence();
        completeMembre(this.entity.getMembre().getNumeroMembre());
        this.init();
        initItems();
    }

    public void onAgenceSourceChanged() {
        membreSources = membreService.selectionnerVoParEntite(agence.getId());
    }

    public List<MembreVo> completeMembre(String query) {
        String queryLowerCase = query.toLowerCase();
        return membreSources.stream().filter(t -> t.getNumeroMembre().toLowerCase().contains(queryLowerCase)).toList();
    }

    public void handleSourceSelect(SelectEvent event) {
        initItems();
    }

    public void calculerDateEcheance() {
        if (this.entity.getDuree() != null && this.entity.getDuree() > 0 && this.dat.getDateDeDepot() != null) {
            this.dat.setDateEcheance(this.dat.getDateDeDepot().plusMonths(this.entity.getDuree()));
        }
    }

    private void initItems() {
        comptes = compteService.selectionnerVoParMembreSans(client, ProduitEpargnePlanEpargne.class);
        dats = compteService.selectionnerVoParMembre(client, ProduitEpargneDat.class);
        membre = membreService.getOne(client);
        setNomClient(membre.getNom());
    }

    public void ajouterActeur() {
        if (Objects.nonNull(beneficiaire)) {
            this.entity.ajouterBeneficiare(beneficiaire);
            beneficiaire = new Acteur();
        }
    }

    public void retirerActeur(Acteur act) {
        if (Objects.nonNull(act)) {
            this.entity.retirerBeneficiare(act);
        }
    }

    public void valider(CertificatDat certificat) {
        if (Objects.nonNull(certificat)) {
            certificat.setValide(true);
            service.updateOne(certificat);
        }
    }

    public void enregistrerEtValider() {
    }

    public void validerParLot() {
    }

    @Override
    public GenericServiceBeanLocal<CertificatDat, Long> getService() {
        return this.service;
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public List<Entite> getAgences() {
        return agences;
    }

    public void setAgences(List<Entite> agences) {
        this.agences = agences;
    }

    public List<MembreVo> getMembreSources() {
        return membreSources;
    }

    public void setMembreSources(List<MembreVo> membreSources) {
        this.membreSources = membreSources;
    }

    public List<ProduitEpargneDat> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitEpargneDat> produits) {
        this.produits = produits;
    }

    public List<CompteEpargneVo> getDats() {
        return dats;
    }

    public void setDats(List<CompteEpargneVo> dats) {
        this.dats = dats;
    }

    public List<CompteEpargneVo> getVirements() {
        return virements;
    }

    public void setVirements(List<CompteEpargneVo> virements) {
        this.virements = virements;
    }

    public List<CompteEpargneVo> getComptes() {
        return comptes;
    }

    public void setComptes(List<CompteEpargneVo> comptes) {
        this.comptes = comptes;
    }

    public List<TypeFiliation> getTypeFiliations() {
        return typeFiliations;
    }

    public void setTypeFiliations(List<TypeFiliation> typeFiliations) {
        this.typeFiliations = typeFiliations;
    }

    public List<Sexe> getSexes() {
        return sexes;
    }

    public void setSexes(List<Sexe> sexes) {
        this.sexes = sexes;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }

    public List<Periodicite> getPeriodicites() {
        return periodicites;
    }

    public void setPeriodicites(List<Periodicite> periodicites) {
        this.periodicites = periodicites;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public BigDecimal getMontantDat() {
        return montantDat;
    }

    public void setMontantDat(BigDecimal montantDat) {
        this.montantDat = montantDat;
    }

    public BigDecimal getInteretDat() {
        return interetDat;
    }

    public void setInteretDat(BigDecimal interetDat) {
        this.interetDat = interetDat;
    }

    public Dat getDat() {
        return dat;
    }

    public void setDat(Dat dat) {
        this.dat = dat;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Acteur getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(Acteur beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public Entite getAgence() {
        return agence;
    }

    public void setAgence(Entite agence) {
        this.agence = agence;
    }

    public CompteEpargneVo getCompteDat() {
        return compteDat;
    }

    public void setCompteDat(CompteEpargneVo compteDat) {
        this.compteDat = compteDat;
    }

    public CompteEpargneVo getCompteVirement() {
        return compteVirement;
    }

    public void setCompteVirement(CompteEpargneVo compteVirement) {
        this.compteVirement = compteVirement;
    }

    public CompteEpargneVo getCompteEpargne() {
        return compteEpargne;
    }

    public void setCompteEpargne(CompteEpargneVo compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    public List<ChargeDat> getChargeDats() {
        return chargeDats;
    }

    public void setChargeDats(List<ChargeDat> chargeDats) {
        this.chargeDats = chargeDats;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ATTENTE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ATTENTE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ATTENTE_DELETE);
    }

    public boolean canValidate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ATTENTE_VALIDATE);
    }

    @Override
    public boolean canAccessDetails() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ATTENTE_DETAILS);
    }
}
