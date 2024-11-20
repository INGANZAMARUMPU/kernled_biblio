/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.beans;

import com.mediasofthome.krnl.constants.CorePermissionConstants;
import com.mediasofthome.krnl.entities.Civility;
import com.mediasofthome.krnl.entities.Country;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.Role;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.params.InFilterParam;
import com.mediasofthome.krnl.service.CivilityServiceBeanLocal;
import com.mediasofthome.krnl.service.CountryServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.KEntityServiceBeanLocal;
import com.mediasofthome.krnl.service.RoleServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.constants.CommonPermissionConstants;
import com.mediasofthome.biblio.core.entities.Employe;
import com.mediasofthome.biblio.core.service.EmployeServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.AddressBean;
import com.mediasofthome.krnl.web.converters.UserConverter;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.event.UnselectEvent;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Mediasoft
 */
@Named
@ViewScoped
public class EmployeBean extends GenericBean<Employe, Long> {

    @EJB
    private CivilityServiceBeanLocal civilityService;
    @EJB
    private EmployeServiceBeanLocal employeService;

    @EJB
    private RoleServiceBeanLocal roleService;

    @EJB
    private CountryServiceBeanLocal countryService;
    @EJB
    private KEntityServiceBeanLocal entityService;
    @Inject
    private UserSessionBean userSession;
    @Inject
    private AddressBean address;

    private List<KEntity> entites;
    private List<Country> countries;
    private List<Role> roles;
    private List<KEntity> entitesAutorises;
    private Employe employe;
    private User user;
    private User userAffect;
    private KEntity entite;
    private String phoneNumber;
    private Country country;
    private String userName;
    private String te11;
    private String tel2;
    private Integer indicatif1;
    private Integer indicatif2;
    private String code1, code2;
    private boolean labels;
    private boolean createUser;
    private List<Civility> civilites;
    private static final String REQUIRED_ADDRESS = "Veuillez remplir l'adresse";
    private static final String REQUIRED_COUNTRY = "Veuillez sélectionner le pays";
    private static final String REQUIRED_LOCALITY_TYPE = "Veuillez sélectionner le type de localité";
    private static final String REQUIRED_LOCALITY = "Veuillez sélectionner la localité";
    private static final String INVALID_BIRTHDATE = "La date de naissance saisie est incorrecte";

    @Override
    public void initAdd() {
        this.entity = new Employe();
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.user = new User();
        this.initRoles();
        this.countries = this.countryService.getAll();
        this.civilites = this.civilityService.getAll();

    }

    public void initRoles() {
        if (this.userSession.getUser().hasRole(Role.SUPER_ADMIN)) {
            roles = this.roleService.getAll();
        } else {
            this.roles = this.roleService.getAll(FilterParams.from(FilterParam.notFrom("label", Role.SUPER_ADMIN)));
        }
    }

    public List<User> recupererUsers(String prefixe) {
        return this.employeService.getAllUser(prefixe);
    }

    public UserConverter getUserConverter() {
        return new UserConverter(this.userService);
    }

    private void errorValidationMessage(String message) {
        Messages.addFlashGlobalError(message);
    }

    private String checkBirthdate(LocalDate birthdate) throws ParseException {
        if (birthdate.isAfter(LocalDate.now())) {
            errorValidationMessage(INVALID_BIRTHDATE);
        }
        return null;
    }

    private String checkAdress() {
        if (this.entity.getAddress() == null) {
            errorValidationMessage(REQUIRED_ADDRESS);
            return null;
        }
        if (this.entity.getAddress() != null && this.entity.getAddress().getLocality() == null) {
            errorValidationMessage(REQUIRED_LOCALITY);
            return null;
        }
        if (this.entity.getAddress().getLocality().getLocalityType() == null) {
            errorValidationMessage(REQUIRED_LOCALITY_TYPE);
            return null;
        }
        if (this.entity.getAddress().getLocality().getCountry() == null) {
            errorValidationMessage(REQUIRED_COUNTRY);
        }
        return null;
    }

    private String checkInfosFieldsAndAdd() throws ParseException {
        checkBirthdate(this.entity.getBirthDate());
        checkAdress();
        if (this.entity.getAddress() != null && this.entity.getAddress().getLocality() != null
                && this.entity.getAddress().getLocality().getLocalityType() != null
                && this.entity.getAddress().getLocality().getCountry() != null) {
            addTelNumbers();
            assignUser();
            this.getService().addOne(this.entity);
            Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
            return LIST_OUTCOME + "?faces-redirect=true";
        }
        return null;
    }

    @Override
    public String add() {
        try {
            return checkInfosFieldsAndAdd();
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

    private void addTelNumbers() {
        this.te11 = Integer.toString(this.indicatif1) + te11;
        this.entity.setTelephon1(te11);
        if (this.tel2 != null && !this.tel2.isEmpty()) {
            this.tel2 = Integer.toString(this.indicatif2) + tel2;
            this.entity.setTelephone2(tel2);
        } else {
            this.entity.setTelephone2(null);
        }
    }

    private void assignUser() {
        if (createUser) {
            this.user.setUsername(userName);
            this.userService.hashPassword(this.user);
            this.entity.setUser(this.user);
        } else {
            this.entity.setUser(null);
        }
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.address.setCountry(this.entity.getAddress().getLocality().getCountry());
        this.address.setLocalityType(this.entity.getAddress().getLocality().getLocalityType());
        this.address.findLocalitiesByType();
        this.te11 = this.entity.getTelephon1();
        this.tel2 = this.entity.getTelephone2();
        this.code1 = this.te11.substring(0, 3);
        this.indicatif1 = Integer.valueOf(this.code1);
        this.te11 = this.te11.substring(3, this.te11.length());
        if (this.tel2 != null && !this.tel2.isEmpty()) {
            this.code2 = this.tel2.substring(0, 3);
            this.indicatif2 = Integer.valueOf(this.code2);
            this.tel2 = this.tel2.substring(3, this.tel2.length());
        }

    }

    @Override
    public void beforeUpdate() {
        addTelNumbers();
    }

    public void userLabelFields() {
        if (createUser) {
            this.labels = true;
            this.userName = this.userService.generateUsername(this.entity.getFirstName(), this.entity.getLastName());
        } else {
            this.labels = false;
        }
    }

    public void rolesSelected(UnselectEvent event) {
        this.employeService.rolesSelected(event);
    }

    @Override
    public void staticFilters(List<FilterParam> filters) {
        List<KEntity> autorises = this.entityService.getAutirised(this.userSession.getUser());
        if (this.userService.isPermitted(CorePermissionConstants.PERM_SPECIAL_ENTITY_CAN_ACCESS_DATA)) {
            return;
        }
        if (!autorises.isEmpty()) {
            filters.add(InFilterParam.from("entity", autorises));
        }
    }

    @Override
    public GenericServiceBeanLocal<Employe, Long> getService() {
        return this.employeService;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_DELETE);
    }

    @Override
    public boolean canAccessDetails() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_DETAILS);
    }

    public List<KEntity> getEntites() {
        return entites;
    }

    public void setEntites(List<KEntity> entites) {
        this.entites = entites;
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

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public Employe getEmploye() {
        return employe;
    }

    public User getUserAffect() {
        return userAffect;
    }

    public void setUserAffect(User userAffect) {
        this.userAffect = userAffect;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIndicatif1() {
        return indicatif1;
    }

    public void setIndicatif1(Integer indicatif1) {
        this.indicatif1 = indicatif1;
    }

    public Integer getIndicatif2() {
        return indicatif2;
    }

    public void setIndicatif2(Integer indicatif2) {
        this.indicatif2 = indicatif2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTe11() {
        return te11;
    }

    public void setTe11(String te11) {
        this.te11 = te11;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public boolean isLabels() {
        return labels;
    }

    public void setLabels(boolean labels) {
        this.labels = labels;
    }

    public boolean isCreateUser() {
        return createUser;
    }

    public void setCreateUser(boolean createUser) {
        this.createUser = createUser;
    }

    public List<KEntity> getEntitesAutorises() {
        return entitesAutorises;
    }

    public void setEntitesAutorises(List<KEntity> entitesAutorises) {
        this.entitesAutorises = entitesAutorises;
    }

    public KEntity getEntite() {
        return entite;
    }

    public void setEntite(KEntity entite) {
        this.entite = entite;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public List<Civility> getCivilites() {
        return civilites;
    }

    public void setCivilites(List<Civility> civilites) {
        this.civilites = civilites;
    }

}
