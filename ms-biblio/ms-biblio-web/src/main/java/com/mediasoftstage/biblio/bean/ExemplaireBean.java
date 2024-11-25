/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Exemplaire;
import com.mediasoftstage.biblio.service.ExemplaireBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@ViewScoped
public class ExemplaireBean extends GenericCrudBean<Exemplaire, Integer> {

    @EJB
    protected ExemplaireBeanLocal exemplaire_bean_local;
    
    private List<Exemplaire> exemplaires;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Exemplaire();
        this.exemplaires = exemplaire_bean_local.getAll();
    }

    @Override
    public boolean canAdd() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_ADD) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canUpdate() {
        return 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_EDIT) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canDelete() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_DELETE) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EXEMPLAIRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    protected GenericServiceBeanLocal<Exemplaire, Integer> getService() {
        return this.exemplaire_bean_local;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

}
