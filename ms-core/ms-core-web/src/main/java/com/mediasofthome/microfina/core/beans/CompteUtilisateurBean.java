/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.beans;

import com.mediasofthome.krnl.constants.CoreConstants;
import com.mediasofthome.krnl.entities.Role;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.RoleServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.constants.CommonPermissionConstants;
import com.mediasofthome.biblio.core.entities.Employe;
import com.mediasofthome.biblio.core.service.EmployeServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 *
 * @author TOKPE Kossi Voltaire 
 * Software Engineer Developer
 * Ce contrôleur sert à créer un compte utilisateur à un employé
 */
@Named
@ViewScoped
public class CompteUtilisateurBean extends GenericBean<Employe, Long> {

    @EJB
    private EmployeServiceBeanLocal service;
    @EJB
    private RoleServiceBeanLocal roleService;
    @Inject
    private UserSessionBean userSession;
    private Employe employe;
    private Long employeId;
    private User user;
    private String username;
    private String password;
    private String confirmPassword;
    private List<Role> roles;
    private List<Role> rolesSelected;

    @Override
    public void initEntity() {
        super.initEntity();
        if (this.userSession.getUser().hasRole(Role.SUPER_ADMIN)) {
            roles = this.roleService.getAll();
        } else {
            this.roles = this.roleService.getAll(
                    FilterParams.from(
                            FilterParam.notFrom("label", Role.SUPER_ADMIN)
                    )
            );
        }
        if(Objects.nonNull(this.getEmployeId())){
         this.employe = service.getOne(this.getEmployeId());
         genererUsername();
        }
    }

    @Override
    public void beforeAdd() {
        this.user = new User();
        this.user.setUsername(this.username);
        this.user.setPassword(this.password);
        this.user.setActive(true);
        this.user.setRoles(this.rolesSelected);
    }

    @Override
    public String add() {
        try {
            User userSaved = userService.addOneWithFlush(user);
            if (Objects.nonNull(userSaved)) {
                this.employe.setUser(userSaved);
                this.getService().updateOne(this.employe);
                Messages.addFlashGlobalInfo("Compte utilisateur créé avec succès.");
                this.logService.info("Mise à jour de " + this.entity.getClass().getSimpleName(), CoreConstants.LOG_CORE);
                return LIST_OUTCOME + "?faces-redirect=true";
            } else {
                Messages.addFlashGlobalError("Échec de création du compte utilisateur.");
                return "/employe/creerCompteUtilisateur?faces-redirect=true";
            }
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
    public void initAdd() {
        this.entity = new Employe();
    }

    @Override
    public GenericServiceBeanLocal<Employe, Long> getService() {
        return this.service;
    }

    public void genererUsername() {
        if (Objects.nonNull(this.employe.getFirstName())
                && Objects.nonNull(this.employe.getLastName())) {
            this.username = this.userService.generateUsername(
                    this.employe.getFirstName(), this.employe.getLastName());

        }
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public List<Role> getRoles(Employe e) {
        return this.roleService.getAll(e.user);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<Role> getRolesSelected() {
        return rolesSelected;
    }

    public void setRolesSelected(List<Role> rolesSelected) {
        this.rolesSelected = rolesSelected;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_USER_AFFECT_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_USER_AFFECT_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_EMPLOYE_USER_AFFECT_DELETE);
    }

    @Override
    public boolean canAccessDetails() {
        return false;
    }
}
