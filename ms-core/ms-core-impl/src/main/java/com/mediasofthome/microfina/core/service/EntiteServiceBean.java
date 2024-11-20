/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.constants.CorePermissionConstants;
import com.mediasofthome.krnl.dao.EntityTypeDaoBean;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.*;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.*;
import com.mediasofthome.biblio.core.constants.CommonPermissionConstants;
import com.mediasofthome.biblio.core.dao.EntiteDaoBean;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.omnifaces.util.Messages;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Stateless
public class EntiteServiceBean extends GenericServiceBean<Entite, Integer> implements EntiteServiceBeanLocal {

    @EJB
    private EntiteDaoBean dao;
    @EJB
    EntityPersonServiceBeanLocal entityPersonService;
    @EJB
    private EntityTypeDaoBean entityTypeDao;
    @EJB
    private KEntityServiceBeanLocal kEntityService;

    @EJB
    private UserServiceBeanLocal userService;
    @EJB
    private PersonServiceBeanLocal personService;
    @EJB
    private AddressServiceBeanLocal addressService;
    @EJB
    private EntiteServiceBeanLocal entiteService;
    @EJB
    private EntityVariableServiceBeanLocal entityVariableService;
    @EJB
    private EntiteSettingServiceBeanLocal entiteSettingsService;

    @Override
    protected GenericDAOBean<Entite, Integer> getDAO() {
        return dao;
    }

    @Override
    public Integer getId(Entite e) {
        return e.getId();
    }

    @Override
    public List<Entite> selectionnerTout() {
        return this.dao.selectionnerTout();
    }

    @Override
    public List<KEntity> recupererEntiteAutorise(User user) {
        return this.kEntityService.getAutirised(user);
    }

    @Override
    public TreeNode<Entite> creerArbre() {
        TreeNode<Entite> racine = new DefaultTreeNode(new Entite(), null);

        List<Entite> entiteRacines = this.getAll(FilterParams.from("root", Boolean.TRUE));

        if (entiteRacines != null) {
            entiteRacines.forEach(er -> {
                TreeNode<Entite> enfant = new DefaultTreeNode(er, racine);
                if (er.getChildren() != null && !er.getChildren().isEmpty()) {
                    this.construireNoeudEnfant(er, enfant);
                }
            });
        }

        return racine;
    }

    public TreeNode<KEntity> creerArbre(KEntity entite, User user) {
        TreeNode<KEntity> racine = new DefaultTreeNode(new Entite(), null);
        List<KEntity> entiteRacines = new ArrayList<>();
        if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_CAN_ACCESS_DATA)) {
            if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_ANCESTORS_ACCESS)) {
                entiteRacines = this.kEntityService.getAutirised(user, true);
            }
            if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_DESCENDANTS_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_MULTIPLE_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_PARENT_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_ANCESTORS_ACCESS)) {
                entiteRacines.add(entite);
            }
            if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_CHILDREN_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_DESCENDANTS_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_ANCESTORS_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_MULTIPLE_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_PARENT_ACCESS)) {
                entiteRacines.add(entite);
            }
            if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_PARENT_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_ANCESTORS_ACCESS)) {
                entiteRacines.add(entite.getParent());

            }
            if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_MULTIPLE_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITE_ANCESTORS_ACCESS)
                    && !this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_PARENT_ACCESS)) {
                entiteRacines.addAll(entite.getParent().getChildren());
            }
        }

        if (entiteRacines != null) {
            entiteRacines.forEach(er -> {
                TreeNode<KEntity> enfant = new DefaultTreeNode(er, racine);
                if (er.getChildren() != null && !er.getChildren().isEmpty()) {
                    this.construireNoeudEnfantSiPermi(er, user, enfant);
                }
            });
        }
        return racine;
    }

    @Override
    public TreeNode<KEntity> creerArbreWithKentity() {
        TreeNode<KEntity> racine = new DefaultTreeNode(new Entite(), null);
        List<Entite> entiteRacines = this.getAll(FilterParams.from("root", Boolean.TRUE));
        if (entiteRacines != null) {
            entiteRacines.forEach(er -> {
                TreeNode<Entite> enfant = new DefaultTreeNode(er, racine);
                if (er.getChildren() != null && !er.getChildren().isEmpty()) {
                    this.construireNoeudEnfant(er, enfant);
                }
            });
        }

        return racine;
    }

    private void construireNoeudEnfantSiPermi(KEntity entite, User user, TreeNode<? extends BaseEntity> noeudRacine) {
        entite.getChildren().forEach(ef -> {
            List<KEntity> entitesAutorisees = this.kEntityService.getAutirised(user);
            if (entitesAutorisees.contains(ef)) {
                TreeNode<Entite> parent = new DefaultTreeNode(ef, noeudRacine);
                if (ef.getChildren() != null && !ef.getChildren().isEmpty()) {
                    this.construireNoeudEnfantSiPermi(ef, user, parent);
                }
            }
        });
    }

    private void construireNoeudEnfant(KEntity entite, TreeNode<? extends BaseEntity> noeudRacine) {
        entite.getChildren().forEach(ef -> {
            TreeNode<Entite> parent = new DefaultTreeNode(ef, noeudRacine);
            if (ef.getChildren() != null && !ef.getChildren().isEmpty()) {
                this.construireNoeudEnfant(ef, parent);
            }
        });
    }

    @Override
    public boolean canBeRoot(EntityType entityType) {
        return this.dao.canBeRoot(entityType).isCanRoot();
    }

    @Override
    public boolean root(Entite entite) {
        if (entite.getEntityType() != null) {
            return this.canBeRoot(entite.getEntityType());
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<Entite> entitesByPerson(Entite parent, EntityType entityType) {
        return this.dao.entitesByPerson(parent, entityType);
    }

    @Override
    public String telephone2(String telephone2, Integer indicatif2) {
        if (telephone2 != null) {
            telephone2 = Integer.toString(indicatif2) + telephone2;
        } else {
            telephone2 = " ";
        }
        return telephone2;
    }

    @Override
    public List<EntityType> typeEntiteList(Boolean b) {
        return this.entityTypeDao.getAll(FilterParams.from(FilterParam.from("canRoot", b)));
    }

    @Override
    public List<EntityType> typeEntiteList() {
        return this.entityTypeDao.getAll();
    }

    @Override
    public TreeNode<KEntity> initRacine(KEntity entite, User user) {
        if (entite != null) {
            return this.creerArbre(entite, user);
        } else {
            return this.creerArbreWithKentity();
        }
    }

    @Override
    public List<Entite> initAgenceList(Entite parent, boolean bool) {
        if (!bool) {
            return this.getAll(FilterParams.from(FilterParam.from("parent", parent)));
        } else {
            return this.getAll();
        }
    }

    @Override
    public List<Entite> chargerAgences(Entite entite) {
        FilterParams filters = FilterParams.from(FilterParam.from("entityType.code", EntityType.AGENCY_CODE));
        if (Objects.nonNull(entite)) {
            filters.add("parent", entite);
        }
        return getAll(filters);
    }

    @Override
    public List<Entite> chargerAgences(Entite... entites) {
        return dao.chargerEntites(entites);
    }

    @Override
    public List<Entite> entiteParents(Entite e) {
        List<Entite> entites = new ArrayList<>();
        if (e != null) {
            entites = this.getAll(FilterParams.from(FilterParam.from("parent", e)));
            entites.addAll(this.entiteEnfants(entites));
        }
        return entites;
    }

    public List<Entite> entiteEnfants(List<Entite> entites) {
        for (Entite entite : entites) {
            entites.add(entite);
        }
        return entites;
    }

    @Override
    public KEntity getParent(KEntity kentity) {
        if (kentity != null) {
            KEntity kentityTemp = kentity;
            while (kentityTemp.getParent() != null) {
                kentity = kentityTemp.getParent();
                kentityTemp = kentity;
            }
        }
        return kentity;
    }

    @Override
    public void rolesSelected(UnselectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public String verifierChampsAddressEntiteEtAjouter(Entite entite, Integer idEntite, Integer entityId, Person person, boolean linkedUser,
            String telephone1, Integer indicatif1, String telephone2, Integer indicatif2, String userName, User user, EntityVariable entityVariable,
            Variable variable, String nomUtilisateur, String motDePasseAleatoire, String REQUIRED_ADDRESS, String REQUIRED_LOCALITY,
            String REQUIRED_LOCALITY_TYPE, String REQUIRED_COUNTRY, String LIST_OUTCOME, EntiteSettings entiteSettings) {
        this.erreurNullAddressMessage(entite, REQUIRED_ADDRESS, REQUIRED_LOCALITY, REQUIRED_LOCALITY_TYPE, REQUIRED_COUNTRY);
        entiteSettings.setEntity(entite);
        this.entiteSettingsService.addOne(entiteSettings);
        if (entite.getAddress() != null && entite.getAddress().getLocality() != null
                && entite.getAddress().getLocality().getLocalityType() != null
                && entite.getAddress().getLocality().getCountry() != null) {
            this.dao.addOne(entite);
            return this.verifierAccesManagerEtAjouterPerson(entite, idEntite, entityId, person, linkedUser, telephone1, indicatif1, telephone2,
                    indicatif2, userName, user, entityVariable, variable, nomUtilisateur, motDePasseAleatoire, REQUIRED_ADDRESS, REQUIRED_LOCALITY, LIST_OUTCOME);
        }
        return null;
    }

    private String erreurNullAddressMessage(Entite entite, String REQUIRED_ADDRESS, String REQUIRED_LOCALITY, String REQUIRED_LOCALITY_TYPE, String REQUIRED_COUNTRY) {
        if (entite.getAddress() == null) {
            Messages.addFlashGlobalError(REQUIRED_ADDRESS);
            return null;
        }
        if (entite.getAddress() != null && entite.getAddress().getLocality() == null) {
            Messages.addFlashGlobalError(REQUIRED_LOCALITY);
            return null;
        }
        if (entite.getAddress().getLocality().getLocalityType() == null) {
            Messages.addFlashGlobalError(REQUIRED_LOCALITY_TYPE);
            return null;
        }
        if (entite.getAddress().getLocality().getCountry() == null) {
            Messages.addFlashGlobalError(REQUIRED_COUNTRY);
        }
        return null;
    }

    private String verifierAccesManagerEtAjouterPerson(Entite entite, Integer idEntite, Integer entityId, Person person, boolean linkedUser,
            String telephone1, Integer indicatif1, String telephone2, Integer indicatif2, String userName, User user,
            EntityVariable entityVariable, Variable variable, String nomUtilisateur, String motDePasseAleatoire, String REQUIRED_ADDRESS, String REQUIRED_LOCALITY, String LIST_OUTCOME) {
        if (this.userService.isPermitted(CommonPermissionConstants.PERM_MANAGER_ACCES) && idEntite == null && entityId == null && linkedUser) {
            if (person.getAddress() == null) {
                Messages.addFlashGlobalError(REQUIRED_ADDRESS);
                return null;
            } else {
                if (person.getAddress().getLocality() == null) {
                    Messages.addFlashGlobalError(REQUIRED_LOCALITY);
                    return null;
                } else {
                    this.procederAjoutPerson(person, telephone1, indicatif1, telephone2, indicatif2, userName, entite, user, entityVariable, variable, nomUtilisateur, motDePasseAleatoire);
                }
            }
            Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
        }
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    private void procederAjoutPerson(Person person, String telephone1, Integer indicatif1, String telephone2,
            Integer indicatif2, String userName, Entite entite, User user, EntityVariable entityVariable,
            Variable variable, String nomUtilisateur, String motDePasseAleatoire) {
        telephone1 = Integer.toString(indicatif1) + telephone1;
        telephone2 = entiteService.telephone2(telephone2, indicatif2);
        person.getUser().setUsername(userName);
        addressService.addOneWithFlush(person.getAddress());
        person.setTelephon1(telephone1);
        person.setTelephone2(telephone2);
        person.setEntity(entite);
        userService.hashPassword(person.getUser());
        this.entityPersonService.addOneEntityPerson(entite, person);
        personService.addOne(person);
        this.ajouterEntityVariable(entite, user, entityVariable, variable, nomUtilisateur, motDePasseAleatoire);
    }

    private void ajouterEntityVariable(Entite entite, User user, EntityVariable entityVariable, Variable variable,
            String nomUtilisateur, String motDePasseAleatoire) {
        if (entite.isRoot() == false) {
            return;
        }
        user = new User(nomUtilisateur, false);
        userService.hashPasswordWithRandom(motDePasseAleatoire, user);
        userService.addOne(user);
        entityVariable = new EntityVariable(user.getUsername(), entite, variable);
        this.entityVariableService.addOne(entityVariable);
    }
}
