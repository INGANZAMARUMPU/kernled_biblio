/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasofthome.biblio.domain.MembreVo;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.biblio.entities.ProduitEpargne;
import com.mediasofthome.biblio.entities.TraceBlocage;
import com.mediasofthome.biblio.service.CompteEpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.MembreServiceBeanLocal;
import com.mediasofthome.biblio.service.ProduitEpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.TraceBlocageServiceBeanLocal;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.params.InFilterParam;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.LocalityServiceBeanLocal;
import com.mediasofthome.krnl.service.LocalityTypeServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.service.EntiteServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author INGANZAMARUMPU Software Engineer Developer Cette classe
 * représente le controleur de gestion des comptes épargnes
 */
@Named
@ViewScoped
public class CompteEpargneBean extends GenericBean<CompteEpargne, Long> {

    @EJB
    protected CompteEpargneServiceBeanLocal compteEpargneService;
    @EJB
    protected ProduitEpargneServiceBeanLocal produitEpargneService;
    @EJB
    protected MembreServiceBeanLocal membreService;
    @EJB
    protected LocalityServiceBeanLocal localityService;
    @EJB
    protected LocalityTypeServiceBeanLocal localityTypeService;
    @EJB
    private TraceBlocageServiceBeanLocal traceBlocageService;
    @EJB
    private EntiteServiceBeanLocal entiteService;
    @Inject
    private UserSessionBean userSession;

    private List<KEntity> agences = new ArrayList<>();
    private KEntity agence;
    private List<MembreVo> membreSources = new ArrayList<>();
    private List<ProduitEpargne> produitEpargnes = new ArrayList<>();
    private Membre membre;
    private LocalDate currentDate = LocalDate.now();
    private boolean showCompte = false;
    private boolean showAutocomplete = false;
    private boolean showBlocageCompteByMembre = true;
    private boolean bloquerCompte = false;
    private boolean bloquerMontant = false;
    private boolean debloquerCompte = false;
    private boolean debloquerMontant = false;
    private boolean canBlock = true;
    private boolean fermeture = true;
    private boolean fermerCmp = false;
    private boolean rouvrirCmp = false;
    private boolean rouverture = true;
    private Long idEntite = null;
    private BigDecimal montantBloque = BigDecimal.ZERO;
    private BigDecimal montantDebloque = BigDecimal.ZERO;
    private String remarque;
    private String motif = "";
    private boolean rendered = Boolean.TRUE;
    private String nomClient;
    private CompteEpargneVo stats;
    private List<CompteEpargne> comptes = new ArrayList<>();
    private List<Membre> membres = new ArrayList<>();

    @Override
    public void staticFilters(List<FilterParam> filters) {
        if (this.userSession.getEntity() != null) {
            filters.add(FilterParam.from("agence", this.userSession.getEntity()));
        } else {
            agences = this.entiteService.recupererEntiteAutorise(this.userSession.getUser());
            if (!agences.isEmpty()) {
                filters.add(InFilterParam.from("agence", agences));
            }
        }
    }

    public String recupererNumeroCompte(Membre membre) {
        List<CompteEpargneVo> ce = compteEpargneService.selectionnerVoParMembre(membre.getId());
        if (!ce.isEmpty()) {
            return ce.get(0).getNumeroCompte();
        } else {
            return null;
        }
    }

    public void checkUnauthorized(CompteEpargne ce) throws IOException {
        if (etatCompte(ce)) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/unauthorized.xhtml");
        }
    }

    public void checkUnauthorizedDebloquer(CompteEpargne ce) throws IOException {
        if (etatCompte(ce) == false) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/unauthorized.xhtml");
        }
    }

    private void insuffisantMontantMessage() {
        Messages.addFlashGlobalError("Le solde disponible sur le compte est insuffisant...");
    }

    private void invalidMontantMessage() {
        Messages.addFlashGlobalError("Le montant saisi est incorrect");
    }

    private void redirectToBloqueMontantPage() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (this.idEntite != null) {
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/bloquer-montant.xhtml?idEntite=" + this.entity.getId() + "&bloquerMontant=true");
        } else {
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/bloquer-montant.xhtml?entityId=" + this.entity.getId() + "&bloquerMontant=true");
        }
    }

    private void redirectToDebloqueMontantPage() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (this.idEntite != null) {
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/debloquer-montant.xhtml?idEntite=" + this.entity.getId() + "&debloquerMontant=true");
        } else {
            ec.redirect(ec.getRequestContextPath() + "/compte/pages/compte/debloquer-montant.xhtml?entityId=" + this.entity.getId() + "&debloquerMontant=true");
        }
    }

    public void fermetureEtReouvertureDeCompte() {
        this.canBlock = true;
        this.fermeture = false;
        this.rouverture = false;
        if (this.compteEpargneService.getOne(entityId).isFerme()) {
            this.canBlock = false;
            this.rouverture = true;
            this.fermerCmp = false;
            this.rouvrirCmp = true;
            this.entity.setFerme(false);
        } else if (!this.compteEpargneService.getOne(entityId).isFerme()) {
            this.canBlock = false;
            this.fermeture = true;
            this.fermerCmp = true;
            this.rouvrirCmp = false;
            this.entity.setFerme(true);
        }
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.agence = this.entity.getAgence();
        logger.log(Level.SEVERE, "--> Entité {0}", this.entity.getAgence());
        this.showAutocomplete = false;
        this.fermetureEtReouvertureDeCompte();
        this.stats = this.compteEpargneService.chargerCompteVoParId(this.entity.getId());

    }

    private void retrieveProduitEpargnes() {
        this.produitEpargnes = this.produitEpargneService.getAll(
                FilterParams.from("entity", this.agence));
    }

    private void init() {
        this.membre = this.entity.getMembre();
        this.agences = this.entiteService.recupererEntiteAutorise(this.userSession.getUser());
        this.retrieveProduitEpargnes();
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.init();
    }

    public CompteEpargneVo compteInfos(CompteEpargne ce) {
        this.stats = this.compteEpargneService.chargerCompteVoParId(ce.getId());
        return this.stats;
    }

    public List<TraceBlocage> getTraceBlocages(CompteEpargne compteEpargne) {
        return this.traceBlocageService.getAllByCompteEpargne(compteEpargne.getId());
    }

    public boolean etatCompte(CompteEpargne c) {
        return this.compteEpargneService.etatCompte(c);
    }

    public boolean etatSoldeDisponible(CompteEpargne ce) {
        return this.compteEpargneService.etatSoldeDisponible(this.compteEpargneService.
                chargerCompteVoParId(ce.getId()).getSolde());
    }

    @Override
    public void initAdd() {
        this.entity = new CompteEpargne();
        this.entity.setAgence((Entite) this.agence);
    }


    public void entitySelected() {
        this.agence = this.entity.getAgence();
        if (this.agence != null) {
            this.showAutocomplete = true;
        }
    }

    @Override
    public void beforeAdd() {
        if (Objects.nonNull(this.entity.getProduitEpargne())) {
            this.entity.setMembre(this.membre);
            this.entity.setNumeroCompte(this.compteEpargneService.
                    genererNumeroCompteEpargne(this.membre, this.entity.getProduitEpargne()));
            this.entity.setIntitule(this.entity.getProduitEpargne().getLibelle());
        }
    }

    @Override
    public void beforeUpdate() {

    }

    @Override
    public String update() {
        try {
            if (bloquerCompte) {
                this.executerBloquerCompte(this.remarque);
            }
            if (bloquerMontant) {
                this.verifierBlocageMontant();
            }
            if (debloquerMontant) {
                this.verifierDeblocageMontant();
            }
            if (debloquerCompte) {
                this.executerDebloquerCompte();
            }
            if (this.fermeture == true) {
                this.entity.setFerme(true);
                this.entity.setRemarque(motif);
            } else if (this.rouverture == true) {
                this.entity.setFerme(false);
                this.entity.setRemarque(motif);
            }
            this.entity = this.getService().updateOne(this.entity);
        } catch (IOException ex) {
            this.logger.log(Level.SEVERE, ex, () -> "Erreur lors de la mise à jour: " + ex.getMessage());
        }
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    private void verifierBlocageMontant() throws IOException {
        if (this.compteEpargneService.isValidMontant(montantBloque)) {
            boolean check1 = this.compteEpargneService.verifierSoldeDisponibleAvecMontantBloque(this.stats.getSolde(), this.montantBloque);
            if (!check1) {
                insuffisantMontantMessage();
                redirectToBloqueMontantPage();
            } else {
                this.executerBloquerMontant(this.montantBloque, this.remarque);
            }
        } else {
            invalidMontantMessage();
            redirectToBloqueMontantPage();
        }
    }

    private void verifierDeblocageMontant() throws IOException {
        if (this.compteEpargneService.isValidMontant(montantDebloque)) {
            boolean check2 = this.compteEpargneService.verifierSoldeDisponibleAvecMontantDebloque(this.stats.getSolde(), this.montantDebloque);
            boolean check3 = this.compteEpargneService.verifierDifferenceMontantBloqueEtDebloque(this.entity.getId(), this.montantDebloque);
            if ((!check2 || !check3) || (!check2 && !check3)) {
                insuffisantMontantMessage();
                redirectToDebloqueMontantPage();
            }
            if (check2 && check3) {
                this.executerDebloquerMontant(this.montantDebloque, this.remarque);
            }
        } else {
            invalidMontantMessage();
            redirectToDebloqueMontantPage();
        }
    }

    private String executerBloquerCompte(String remarque) {
        this.entity.setDateBlocage(currentDate);
        this.entity.setBloque(true);
        this.compteEpargneService.updateOne(this.entity);
        this.traceBlocageService.creerTraceBlocage(this.entity, currentDate, null, null, remarque);
        Messages.addFlashGlobalInfo("Compte bloqué avec succès.");
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    private String executerBloquerMontant(BigDecimal montantBloque, String remarque) {
        this.entity.setBloque(false);
        this.compteEpargneService.updateOne(this.entity);
        this.traceBlocageService.creerTraceBlocage(this.entity, currentDate, montantBloque, null, remarque);
        Messages.addFlashGlobalInfo("Montant bloqué avec succès.");
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    private String executerDebloquerMontant(BigDecimal montantDebloque, String remarque) {
        this.entity.setBloque(false);
        this.compteEpargneService.updateOne(this.entity);
        this.traceBlocageService.creerTraceBlocage(this.entity, currentDate, null, montantDebloque, remarque);
        Messages.addFlashGlobalInfo("Montant débloqué avec succès.");
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    private String executerDebloquerCompte() {
        this.entity.setDateDeblocage(currentDate);
        this.entity.setBloque(false);
        this.compteEpargneService.updateOne(this.entity);
        Messages.addFlashGlobalInfo("Compte débloqué avec succès.");
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    public EntiteServiceBeanLocal getEntiteService() {
        return entiteService;
    }

    public void setEntiteService(EntiteServiceBeanLocal entiteService) {
        this.entiteService = entiteService;
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public void onProduitEpargneChange() {
        this.entity.getProduitEpargne();
    }

    public BigDecimal getMontantBloque() {
        return montantBloque;
    }

    public void setMontantBloque(BigDecimal montantBloque) {
        this.montantBloque = montantBloque;
    }

    public List<Membre> filterMembres(String query) {
        if (this.agence != null && query != null) {
            this.membres = this.membreService.filterByNumeroMembre(query, (Entite) this.agence);
        } else {
            this.membres = this.membreService.filterByNumeroMembre(query, (Entite) this.getAgence());
        }
        return this.membres;
    }

    public void onMembreSelected(SelectEvent<String> event) {
        this.membre = this.membreService.getOne(Long.valueOf(event.getObject()));
        this.entity.setProduitEpargne(null);
        this.retrieveProduitEpargnes();
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public List<KEntity> getAgences() {
        return agences;
    }

    public void setAgences(List<KEntity> agences) {
        this.agences = agences;
    }

    public KEntity getAgence() {
        return agence;
    }

    public void setAgence(KEntity agence) {
        this.agence = agence;
    }

    public void setAgence(Entite agence) {
        this.agence = agence;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public List<ProduitEpargne> getProduitEpargnes() {
        return produitEpargnes;
    }

    public CompteEpargneVo getStats() {
        return stats;
    }

    public void setStats(CompteEpargneVo stats) {
        this.stats = stats;
    }

    public void setProduitEpargnes(List<ProduitEpargne> produitEpargnes) {
        this.produitEpargnes = produitEpargnes;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isShowCompte() {
        return showCompte;
    }

    public void setShowCompte(boolean showCompte) {
        this.showCompte = showCompte;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public boolean isBloquerCompte() {
        return bloquerCompte;
    }

    public void setBloquerCompte(boolean bloquerCompte) {
        this.bloquerCompte = bloquerCompte;
    }

    public boolean isBloquerMontant() {
        return bloquerMontant;
    }

    public void setBloquerMontant(boolean bloquerMontant) {
        this.bloquerMontant = bloquerMontant;
    }

    public boolean isDebloquerCompte() {
        return debloquerCompte;
    }

    public void setDebloquerCompte(boolean debloquerCompte) {
        this.debloquerCompte = debloquerCompte;
    }

    public boolean isDebloquerMontant() {
        return debloquerMontant;
    }

    public void setDebloquerMontant(boolean debloquerMontant) {
        this.debloquerMontant = debloquerMontant;
    }

    @Override
    public GenericServiceBeanLocal<CompteEpargne, Long> getService() {
        return this.compteEpargneService;
    }

    public List<CompteEpargne> getComptes() {
        return comptes;
    }

    public void setComptes(List<CompteEpargne> comptes) {
        this.comptes = comptes;
    }

    public BigDecimal getMontantDebloque() {
        return montantDebloque;
    }

    public void setMontantDebloque(BigDecimal montantDebloque) {
        this.montantDebloque = montantDebloque;
    }

    public List<MembreVo> getMembreSources() {
        return membreSources;
    }

    public void setMembreSources(List<MembreVo> membreSources) {
        this.membreSources = membreSources;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public boolean isShowAutocomplete() {
        return showAutocomplete;
    }

    public void setShowAutocomplete(boolean showAutocomplete) {
        this.showAutocomplete = showAutocomplete;
    }

    public boolean isShowBlocageCompteByMembre() {
        return showBlocageCompteByMembre;
    }

    public void setShowBlocageCompteByMembre(boolean showBlocageCompteByMembre) {
        this.showBlocageCompteByMembre = showBlocageCompteByMembre;
    }

    public boolean canBlock() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_BLOCK);
    }

    public boolean canDeblock() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_DEBLOCK);
    }

    public boolean canBlockAmount() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_MONTANT_BLOCK);
    }

    public boolean canDeblockAmount() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_MONTANT_DEBLOCK);
    }

    public boolean canSeeTraceBlocage() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_TRACE_BLOCAGE);
    }

    public boolean canSeeInfosClient() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_INFOS_CLIENT);
    }

    public boolean canSeeInfosCompte() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_INFOS_COMPTE);
    }

    public boolean canSeeInfosSolde() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_INFOS_SOLDE);
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_EDIT);
    }

    @Override
    public boolean canAccessDetails() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_DETAILS);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_COMPTE_DELETE);
    }

    public boolean isCanBlock() {
        return canBlock;
    }

    public void setCanBlock(boolean canBlock) {
        this.canBlock = canBlock;
    }

    public boolean isFermeture() {
        return fermeture;
    }

    public void setFermeture(boolean fermeture) {
        this.fermeture = fermeture;
    }

    public boolean isRouverture() {
        return rouverture;
    }

    public void setRouverture(boolean rouverture) {
        this.rouverture = rouverture;
    }

    public boolean isFermerCmp() {
        return fermerCmp;
    }

    public void setFermerCmp(boolean fermerCmp) {
        this.fermerCmp = fermerCmp;
    }

    public boolean isRouvrirCmp() {
        return rouvrirCmp;
    }

    public void setRouvrirCmp(boolean rouvrirCmp) {
        this.rouvrirCmp = rouvrirCmp;
    }

    public Long getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(Long idEntite) {
        this.idEntite = idEntite;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

}
