package com.mediasofthome.biblio.core.beans;

import com.mediasofthome.krnl.constants.CoreConstants;
import com.mediasofthome.krnl.constants.CorePermissionConstants;
import com.mediasofthome.krnl.entities.Country;
import com.mediasofthome.krnl.entities.EntityType;
import com.mediasofthome.krnl.entities.EntityVariable;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.LocalityType;
import com.mediasofthome.krnl.entities.Person;
import com.mediasofthome.krnl.entities.Role;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.entities.Variable;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.InFilterParam;
import com.mediasofthome.krnl.service.AddressServiceBeanLocal;
import com.mediasofthome.krnl.service.AppSettingsServiceBeanRemote;
import com.mediasofthome.krnl.service.CountryServiceBeanLocal;
import com.mediasofthome.krnl.service.EntityTypeServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.KEntityServiceBeanLocal;
import com.mediasofthome.krnl.service.RoleServiceBeanLocal;
import com.mediasofthome.krnl.service.UserServiceBeanLocal;
import com.mediasofthome.krnl.service.VariableServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.AddressBean;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserEntitySessionBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.krnl.web.validations.UserNameValidator;
import com.mediasofthome.biblio.core.constants.CommonPermissionConstants;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import com.mediasofthome.biblio.core.service.EntiteServiceBeanLocal;
import com.mediasofthome.biblio.core.service.EntiteSettingServiceBeanLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.time.ZoneId;
import org.omnifaces.util.Messages;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mawuli
 */
@Named
@ViewScoped
public class EntiteBean extends GenericBean<Entite, Integer> {

    @EJB
    private EntiteServiceBeanLocal service;
    @EJB
    private AddressServiceBeanLocal addressService;
    @EJB
    private CountryServiceBeanLocal countryService;
    @EJB
    private RoleServiceBeanLocal roleService;
    @EJB
    private KEntityServiceBeanLocal kentityService;
    @EJB
    private EntityTypeServiceBeanLocal entityTypeService;
    @EJB
    private UserServiceBeanLocal userService;
    @EJB
    private VariableServiceBeanLocal variableService;
    @EJB
    private AppSettingsServiceBeanRemote appSettingsService;
    @EJB
    private EntiteSettingServiceBeanLocal entiteSettingsService;

    private List<EntityType> typeEntites = new ArrayList<>();
    private List<KEntity> entites;
    private TreeNode<KEntity> racine;
    private List<Country> countries;
    private List<Role> roles;
    private Person person;
    private String userName;
    private User user;
    private Integer indicatif;
    private Integer indicatif2;
    private Country country;
    private boolean root;
    private boolean linkedUser;
    private boolean superuser;
    private boolean userinfo = true;
    private LocalityType type;
    private String telephone1;
    private String telephone2;
    private Integer idEntite;
    private Map<String, String> locales;
    private List<String> zones;
    private EntityVariable entityVariable;
    private EntiteSettings entiteSettings;

    @Inject
    private UserSessionBean userSession;
    @Inject
    private AddressBean address;
    @Inject
    private UserEntitySessionBean userEntitySession;
    private static final String REQUIRED_ADDRESS = "Veuillez remplir l'adresse";
    private static final String REQUIRED_COUNTRY = "Veuillez sélectionner le pays";
    private static final String REQUIRED_LOCALITY_TYPE = "Veuillez sélectionner le type de localité";
    private static final String REQUIRED_LOCALITY = "Veuillez sélectionner la localité";
    private static final String SYSTEME_DISPLAY_NAME = "sys";
    private static final int NUMBER_DIGITS = 10;

    @PostConstruct
    @Override
    public void initList() {
        this.racine = this.service.initRacine(this.userSession.getEntity(), this.userSession.getUser());
    }

    @Override
    public void staticFilters(List<FilterParam> filters) {
        if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_CAN_ACCESS_DATA)) {
            List<KEntity> autorises = new ArrayList<>(this.userEntitySession.getEntities());
            if (!autorises.isEmpty()) {
                filters.add(InFilterParam.from("agence", autorises));
            }
        }
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.person = new Person();
        this.roles = this.roleService.getRoles(this.userSession.getUser());
        this.entites = this.kentityService.getAutirised(this.userService.getCurrent());
        this.countries = this.countryService.getAll();
        this.loadChildren();
        this.initZones();
        this.initLocales();
    }

    public void loadChildren() {
        if (this.entity.getParent() != null) {
            this.typeEntites = this.entityTypeService.entityTypeChildren(this.getEntityType());
        } else {
            this.typeEntites = this.service.typeEntiteList();
        }
    }

    public EntityType getEntityType() {
        return this.entity.getParent().getEntityType();
    }

    private void initZones() {
        this.zones = new ArrayList<>();
        this.zones.addAll(ZoneId.getAvailableZoneIds());
        Collections.sort(this.zones);
    }

    private void initLocales() {
        this.locales = new HashMap<>();
        List<Locale> list = new ArrayList<>();
        list.addAll(Arrays.asList(Locale.getAvailableLocales()));
        Comparator<Locale> comparator = Comparator
                .comparing((Locale e) -> e.getDisplayLanguage())
                .thenComparing(e -> e.getDisplayCountry());
        list.sort(comparator);
        for (Locale e : list) {
            if (StringUtils.isBlank(e.toString())) {
                continue;
            }
            this.locales.put(this.getLocaleLabel(e), e.toString());
        }
    }

    private String getLocaleLabel(Locale locale) {
        if (StringUtils.isBlank(locale.getDisplayCountry())) {
            return locale.getDisplayLanguage(locale);
        }
        return locale.getDisplayLanguage(locale) + " - " + locale.getDisplayCountry(locale);
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.address.setCountry(this.entity.getAddress().getLocality().getCountry());
        this.address.setLocalityType(this.entity.getAddress().getLocality().getLocalityType());
        this.address.findLocalitiesByType();
        this.entiteSettings = this.entiteSettingsService.recupererUnSettingsParEntite(this.entityId);
    }

    @Override
    public void initAdd() {
        this.entity = new Entite();
        this.user = new User();
        this.entityVariable = new EntityVariable();
        this.entityVariable.setKentity(new KEntity());
        this.entityVariable.setVariable(new Variable());
        this.entiteSettings = EntiteSettings.getDefault(appSettingsService.getActive(), this.entity);
        if (this.idEntite != null) {
            this.entity.setParent(this.recuprerParent());
        }
    }

    public UserNameValidator getUserNameValidator() {
        return new UserNameValidator(userService, this.person.user);
    }

    public Entite recuprerParent() {
        return this.service.getOne(this.idEntite);
    }

    public void canBeRoot() {
        this.root = this.service.canBeRoot(this.entity.getEntityType());
    }

    public void rolesSelected(UnselectEvent event) {
        this.service.rolesSelected(event);
    }

    public void getCodeIndicatif() {
        this.indicatif = this.country.getPhoneCode();
    }

    public void usersNameGenerator() {
        if (Objects.nonNull(this.person.getFirstName()) && Objects.nonNull(this.person.getLastName())) {
            this.userName = this.userService.generateUsername(this.person.getFirstName(), this.person.getLastName());
        }
    }

    @Override
    public GenericServiceBeanLocal<Entite, Integer> getService() {
        return this.service;
    }

    @Override
    public String add() {
        try {
            return this.service.verifierChampsAddressEntiteEtAjouter(this.entity, this.idEntite, this.entityId, this.person, this.linkedUser,
                    this.telephone1, this.indicatif, this.telephone2, this.indicatif2, this.userName, this.user, this.entityVariable,
                    this.variable(), this.nomUtilisateur(), this.genererMotPasseAleatoire(), REQUIRED_ADDRESS, REQUIRED_LOCALITY,
                    REQUIRED_LOCALITY_TYPE, REQUIRED_COUNTRY, LIST_OUTCOME, this.entiteSettings);
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

    public String genererMotPasseAleatoire() {
        return this.userService.generateAleatoirePassword(NUMBER_DIGITS);
    }

    public String nomUtilisateur() {
        return this.userService.generateAutomaticallyUsername(SYSTEME_DISPLAY_NAME, this.entity.getDenomination());
    }

    public Variable variable() {
        return this.variableService.getByName(CoreConstants.VAR_ENTITY_SYSTEM_USER);
    }

    @Override
    public String update() {
        try {
            this.entiteSettingsService.updateOne(this.entiteSettings);
            this.addressService.updateOne(this.entity.getAddress());
            this.entity = this.getService().updateOne(this.entity);
            Messages.addFlashGlobalInfo("Mise à jour effectuée avec succès.");
            this.logService.info("Mise à jour de " + this.entity.getClass().getSimpleName(), CoreConstants.LOG_CORE);
            return LIST_OUTCOME + "?faces-redirect=true";
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, "Erreur à la mise à jour de l'objet: {0}", this.entity);
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de la mise à jour.");
            this.logger.log(Level.SEVERE, "Erreur à la mise à jour de l'objet: {0}", this.entity);
            return null;
        }
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_ENTITE_ADD);
    }

    public boolean canAddChild() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_ENTITE_ADD_CHILD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_ENTITE_EDIT);
    }

    @Override
    public boolean canAccessDetails() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_ENTITE_DETAILS);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_ENTITE_DELETE);
    }

    public boolean canManage() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_MANAGER_ACCES);
    }

    public boolean canCreateRootEntity() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_RACINE_ACCES);
    }

    public boolean canAssociateUserToEntity() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_SPECIAL_CAN_ASSOCIATE_USER_TO_ENTITY);
    }

    public EntiteSettings getEntiteSettings() {
        return entiteSettings;
    }

    public void setEntiteSettings(EntiteSettings entiteSettings) {
        this.entiteSettings = entiteSettings;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    public Map<String, String> getLocales() {
        return locales;
    }

    public void setLocales(Map<String, String> locales) {
        this.locales = locales;
    }

    public EntityVariable getEntityVariable() {
        return entityVariable;
    }

    public void setEntityVariable(EntityVariable entityVariable) {
        this.entityVariable = entityVariable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(boolean linkedUser) {
        this.linkedUser = linkedUser;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public TreeNode<KEntity> getRacine() {
        return racine;
    }

    public List<EntityType> getTypeEntites() {
        return typeEntites;
    }

    public List<KEntity> getEntites() {
        return entites;
    }

    public boolean isUserinfo() {
        return userinfo;
    }

    public void setUserinfo(boolean userinfo) {
        this.userinfo = userinfo;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(Integer indicatif) {
        this.indicatif = indicatif;
    }

    public Integer getIndicatif2() {
        return indicatif2;
    }

    public void setIndicatif2(Integer indicatif2) {
        this.indicatif2 = indicatif2;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalityType getType() {
        return type;
    }

    public void setType(LocalityType type) {
        this.type = type;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public Integer getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(Integer idEntite) {
        this.idEntite = idEntite;
    }
}
