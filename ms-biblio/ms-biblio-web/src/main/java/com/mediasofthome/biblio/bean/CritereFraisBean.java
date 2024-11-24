package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.CategorieMembre;
import com.mediasofthome.biblio.entities.CategorieMembreCritereFrais;
import com.mediasofthome.biblio.entities.CritereFrais;
import com.mediasofthome.biblio.entities.Devise;
import com.mediasofthome.biblio.entities.DeviseCritereFrais;
import com.mediasofthome.biblio.entities.Frais;
import com.mediasofthome.biblio.entities.LocalityCritereFrais;
import com.mediasofthome.biblio.entities.PeriodeCritereFrais;
import com.mediasofthome.biblio.entities.Profession;
import com.mediasofthome.biblio.entities.ProfessionCritereFrais;
import com.mediasofthome.biblio.entities.Sexe;
import com.mediasofthome.biblio.entities.SexeCritereFrais;
import com.mediasofthome.biblio.entities.SousTypeMembre;
import com.mediasofthome.biblio.entities.SousTypeMembreCritereFrais;
import com.mediasofthome.biblio.entities.TypeFrais;
import com.mediasofthome.biblio.service.CategorieMembreServiceBeanLocal;
import com.mediasofthome.biblio.service.CritereFraisServiceBeanLocal;
import com.mediasofthome.biblio.service.FraisServiceBeanLocal;
import com.mediasofthome.biblio.service.ProfessionServiceBeanLocal;
import com.mediasofthome.biblio.service.SexeServiceBeanLocal;
import com.mediasofthome.biblio.service.SousTypeMembreServiceBeanLocal;
import com.mediasofthome.biblio.service.TypeFraisServiceBeanLocal;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.Locality;
import com.mediasofthome.krnl.entities.LocalityType;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.LocalityTypeServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import com.mediasofthome.biblio.core.service.TypeOperationServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.omnifaces.util.Messages;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:50
 */
@Named
@ViewScoped
public class CritereFraisBean extends GenericBean<Frais, Long> {

    @EJB
    private CritereFraisServiceBeanLocal critereFraisService;
    @EJB
    private FraisServiceBeanLocal service;
    @EJB
    private SexeServiceBeanLocal sexeService;
    @EJB
    private LocalityTypeServiceBeanLocal localityTypeService;
    @EJB
    private CategorieMembreServiceBeanLocal categorieMembreService;
    @EJB
    private SousTypeMembreServiceBeanLocal sousTypeMembreService;
    @EJB
    private ProfessionServiceBeanLocal professionService;
    @EJB
    private TypeFraisServiceBeanLocal typeFraisService;
    @EJB
    private TypeOperationServiceBeanLocal typeOperationService;
    @Inject
    private UserSessionBean userSession;

    private List<TypeFrais> listeTypeFrais;
    private List<Sexe> sexes;
    private List<CategorieMembre> categorieMembres;
    private List<LocalityType> localityTypes;
    private List<Locality> localites;
    private List<Profession> professions;
    private List<SousTypeMembre> sousTypeMembres;
    private List<Devise> devises;

    private Map<String, Object> criteres;
    private Class<? extends CritereFrais> critereClass;
    private CritereFrais critereFrais;
    private PeriodeCritereFrais periodeCritereFrais;
    private LocalityType localityType;
    private List<TypeOperation> typeOperations = new ArrayList<>();
    private TypeOperation typeOperation;
    private List<KEntity> entites;
    private List<Frais> frais = new ArrayList<>();

    @Override
    public void initList() {
        this.typeOperations = this.typeOperationService.getAll();
    }

    public void listeFrais() {
        if (userSession.getEntity() != null) {
            this.frais = service.getAll(FilterParams.from("entity", userSession.getEntity()));
        } else {
            this.frais = service.getAll();
        }
    }

    public String getFormattedDates(LocalDate debut, LocalDate fin) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.userSession.getSettings().getFormats().getShortDatePattern());
            return debut.format(formatter) + " - " + fin.format(formatter);
        } catch (NullPointerException e) {
            Messages.addGlobalError("Les champs de date ne doivent pas être vides");
            logger.log(Level.SEVERE, "Les champs de date ne doivent pas être vides", e);
        }
        return null;
    }

    @Override
    public void initAdd() {
        this.entity = new Frais();
        this.periodeCritereFrais = new PeriodeCritereFrais();
    }


    public void onCritereChange() {
        try {
            this.critereFrais = this.critereClass.getDeclaredConstructor().newInstance();
            this.initDataProcess();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur d'instantiation", ex);
        }
    }

    public void ajouter() {
        this.periodeCritereFrais = this.critereFraisService.ajouter(this.critereFrais, this.periodeCritereFrais, this.entity);
        this.critereFrais = null;
        this.critereClass = null;
    }

    public void retirer(CritereFrais c) {
        this.entity.supprimerCritere(c);
    }

    public void onTypeOperationChange() {
        this.listeTypeFrais = this.typeFraisService.onTypeOperationChange(this.entity, this.typeOperation);
    }

    public void onLocalityTypeChange() {
        this.localites = this.critereFraisService.onLocalityTypeChange(this.localityType);
    }

    private void initDataProcess() {
        if (this.critereFrais.isCritereCategorieMembre()) {
            this.categorieMembres = this.categorieMembreService.getAll();
        }
        if (this.critereFrais.isCritereLocalite()) {
            this.localityTypes = this.localityTypeService.getAll();
        }
        if (this.critereFrais.isCritereProfession()) {
            this.professions = this.professionService.getAll();
        }
        if (this.critereFrais.isCritereSexe()) {
            this.sexes = this.sexeService.getAll();
        }
        if (this.critereFrais.isCritereSoutTypeMembre()) {
            this.sousTypeMembres = this.sousTypeMembreService.getAll();
        }
    }

    @Override
    public void beforeAdd() {
        super.beforeAdd();
        this.toOrder();
    }

    @Override
    public void beforeUpdate() {
        super.beforeUpdate();
        this.toOrder();
    }

    private void toOrder() {
        int ordre = 0;
        for (CritereFrais e : this.entity.getCritereFrais()) {
            ordre++;
            e.setNumeroOrdre(ordre);
        }
    }

    @Override
    public void initEntity() {
        this.typeOperations = this.typeOperationService.getAll();
        this.criteres = new HashMap<>();
        this.criteres.put("Localité", LocalityCritereFrais.class);
        this.criteres.put("Sexe", SexeCritereFrais.class);
        this.criteres.put("Profession", ProfessionCritereFrais.class);
        this.criteres.put("Période", PeriodeCritereFrais.class);
        this.criteres.put("Catégorie membre", CategorieMembreCritereFrais.class);
        this.criteres.put("Sous type membre", SousTypeMembreCritereFrais.class);
        this.criteres.put("Devise", DeviseCritereFrais.class);
        super.initEntity();
    }

    @Override
    public GenericServiceBeanLocal<Frais, Long> getService() {
        return service;
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.typeOperation = this.entity.getTypeFrais().getTypeOperation();
        this.onTypeOperationChange();
        this.entity.setTypeFrais(this.getEntity().getTypeFrais());
    }

    @Override
    public String update() {
        try {
            if (this.entity.getCritereFrais().isEmpty()) {
                Messages.addFlashGlobalError("Veuillez ajouter au moins un critère de frais.");
                return null;
            } else {
                this.entity = this.getService().updateOne(this.entity);
                Messages.addFlashGlobalInfo("Mise à jour effectuée avec succès.");
                this.logger.log(Level.INFO, "Mise à jour de {0} effectué: {1}.", new Object[]{this.entity.getClass().getSimpleName(), this.entity});
                return LIST_OUTCOME + "?faces-redirect=true";
            }
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de la mise à jour.");
            this.logger.log(Level.SEVERE, ex, () -> "Erreur à la mise à jour de l'objet: " + this.entity);
            return null;
        }
    }

    @Override
    public String add() {
        try {
            if (this.entity.getCritereFrais().isEmpty()) {
                Messages.addFlashGlobalError("Veuillez ajouter au moins un critère de frais.");
                return null;
            } else {
                this.getService().addOne(this.entity);
                this.initList();
                Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
                this.logger.log(Level.INFO, "Enregistrement de {0} effectué: {1}.", new Object[]{this.entity.getClass().getSimpleName(), this.entity});
                return LIST_OUTCOME + "?faces-redirect=true";
            }
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de l'ajout.");
            this.logger.log(Level.SEVERE, ex, () -> "Erreur à l'ajout de l'objet: " + this.entity);
            return null;
        }
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_FRAIS_ADD);
    }

    public boolean canAddTypeFrais() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_TYPE_FRAIS_ADD);
    }

    public boolean canAddFrais() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_FRAIS_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_FRAIS_EDIT);
    }

    public boolean canListFrais() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_FRAIS_LIST);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRAGE_FRAIS_DELETE);
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    @Override
    public boolean canAccessDetails() {
        return false;
    }

    public List<Frais> getFrais() {
        return frais;
    }

    public void setFrais(List<Frais> frais) {
        this.frais = frais;
    }

    public List<TypeFrais> getAllTypeFrais(TypeOperation typeOperation) {
        return this.critereFraisService.getAllTypeFrais(typeOperation);
    }

    public List<Frais> getAllFrais(TypeFrais typeFrais) {
        return this.critereFraisService.getAllFrais(typeFrais);
    }

    public List<KEntity> getEntites() {
        return entites;
    }

    public PeriodeCritereFrais getPeriodeCritereFrais() {
        return periodeCritereFrais;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public List<Devise> getDevises() {
        return devises;
    }

    public List<TypeOperation> getTypeOperations() {
        return typeOperations;
    }

    public LocalityType getLocalityType() {
        return localityType;
    }

    public void setLocalityType(LocalityType localityType) {
        this.localityType = localityType;
    }

    public List<LocalityType> getLocalityTypes() {
        return localityTypes;
    }

    public List<Locality> getLocalites() {
        return localites;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public List<SousTypeMembre> getSousTypeMembres() {
        return sousTypeMembres;
    }

    public CritereFrais getCritereFrais() {
        return critereFrais;
    }

    public void setCritereFrais(CritereFrais critereFrais) {
        this.critereFrais = critereFrais;
    }

    public List<CategorieMembre> getCategorieMembres() {
        return categorieMembres;
    }

    public List<Sexe> getSexes() {
        return sexes;
    }

    public Class<? extends CritereFrais> getCritereClass() {
        return critereClass;
    }

    public void setCritereClass(Class<? extends CritereFrais> critereClass) {
        this.critereClass = critereClass;
    }

    public List<TypeFrais> getListeTypeFrais() {
        return listeTypeFrais;
    }

    public Map<String, Object> getCriteres() {
        return criteres;
    }

}
