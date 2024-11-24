/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasoftstage.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.Epargne;
import com.mediasofthome.biblio.entities.Guichet;
import com.mediasofthome.biblio.entities.Origine;
import com.mediasoftstage.biblio.service.ActeurServiceBeanLocal;
import com.mediasofthome.biblio.service.CompteEpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.EpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.GuichetServiceBeanLocal;
import com.mediasofthome.biblio.service.OrigineServiceBeanLocal;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.params.InFilterParam;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import com.mediasofthome.biblio.core.service.EntiteServiceBeanLocal;
import com.mediasofthome.biblio.core.service.TypeOperationServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INGANZAMARUMPU Ce bean sert à gérer les vues des épargnes
 */
@Named
@ViewScoped
public class EpargneBean extends GenericBean<Epargne, Long> {

    @EJB
    protected EpargneServiceBeanLocal service;
    @EJB
    protected EntiteServiceBeanLocal entiteService;
    @EJB
    protected OrigineServiceBeanLocal origineService;
    @EJB
    protected GuichetServiceBeanLocal guichetService;
    @EJB
    protected ActeurServiceBeanLocal acteurService;
    @EJB
    protected TypeOperationServiceBeanLocal typeOperationService;
    @EJB
    protected CompteEpargneServiceBeanLocal compteEpargneService;
    @Inject
    private UserSessionBean userSession;

    private List<KEntity> agences = new ArrayList<>();
    private List<Origine> origines = new ArrayList<>();
    private List<Guichet> guichets = new ArrayList<>();
    private List<Acteur> mandataires;
    List<CompteEpargneVo> compteEpargnes = new ArrayList<>();
    private KEntity agence;
    private Acteur mandataire;
    private List<TypeOperation> typeOperations;
    private String numeroCompte;
    private CompteEpargneVo compteEpargne;

    @Override
    public void staticFilters(List<FilterParam> filters) {
        if (this.agence != null) {
            filters.add(FilterParam.from("agence", agence));
        } else {
            agences = this.entiteService.recupererEntiteAutorise(this.userSession.getUser());
            if (!agences.isEmpty()) {
                filters.add(InFilterParam.from("agence", agences));
            }
        }
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.init();
    }

    private void init() {
        this.agences = this.entiteService.recupererEntiteAutorise(this.userSession.getUser());
        this.origines = this.origineService.getAll();
        this.typeOperations = this.typeOperationService.getAll();
    }

    public void onAgenceChange() {
        this.agence = this.getAgence();
        retrieve(this.agence);
    }

    public void retrieve(KEntity kEntity) {
        this.guichets = guichetService.getAll(FilterParams.from("agence", kEntity));
        this.mandataires = acteurService.getAll(FilterParams.from("entite", kEntity));
        this.compteEpargnes = compteEpargneService.chargerCompteVo((Entite) kEntity);
    }

    public void onCompteChange() {
        this.compteEpargne = this.getCompteEpargne();
        this.numeroCompte = this.compteEpargne.getNumeroCompte();
    }

    @Override
    public void beforeAdd() {
        this.entity.setAgence((Entite) this.getAgence());
        this.entity.setCompteEpargne(compteEpargneService.getOne(this.compteEpargne.getId()));
        this.entity.setNumeroCompte(this.numeroCompte);
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        retrieve(this.entity.getAgence());
        this.numeroCompte = this.entity.getNumeroCompte();
        this.compteEpargne = compteEpargneService.chargerCompteVoParId(this.entity.getCompteEpargne().getId());
    }

    @Override
    public void initAdd() {
        this.entity = new Epargne();
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public List<KEntity> getAgences() {
        return agences;
    }

    public void setAgences(List<KEntity> agences) {
        this.agences = agences;
    }

    public List<Origine> getOrigines() {
        return origines;
    }

    public void setOrigines(List<Origine> origines) {
        this.origines = origines;
    }

    public List<Guichet> getGuichets() {
        return guichets;
    }

    public void setGuichets(List<Guichet> guichets) {
        this.guichets = guichets;
    }

    public List<Acteur> getMandataires() {
        return mandataires;
    }

    public void setMandataires(List<Acteur> mandataires) {
        this.mandataires = mandataires;
    }

    public KEntity getAgence() {
        return agence;
    }

    public void setAgence(KEntity agence) {
        this.agence = agence;
    }

    public Acteur getMandataire() {
        return mandataire;
    }

    public void setMandataire(Acteur mandataire) {
        this.mandataire = mandataire;
    }

    public List<TypeOperation> getTypeOperations() {
        return typeOperations;
    }

    public void setTypeOperations(List<TypeOperation> typeOperations) {
        this.typeOperations = typeOperations;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public List<CompteEpargneVo> getCompteEpargnes() {
        return compteEpargnes;
    }

    public void setCompteEpargnes(List<CompteEpargneVo> compteEpargnes) {
        this.compteEpargnes = compteEpargnes;
    }

    public CompteEpargneVo getCompteEpargne() {
        return compteEpargne;
    }

    public void setCompteEpargne(CompteEpargneVo compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_CAISSE_EPARGNE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_CAISSE_EPARGNE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_CAISSE_EPARGNE_DELETE);
    }

    public boolean canDeposit() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_CAISSE_EPARGNE_DEPOSIT);
    }

    public boolean canWithdrawal() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_CAISSE_EPARGNE_WITHDRAWAL);
    }

    @Override
    public boolean canAccessDetails() {
        return false;
    }

    @Override
    public GenericServiceBeanLocal<Epargne, Long> getService() {
        return this.service;
    }
}
