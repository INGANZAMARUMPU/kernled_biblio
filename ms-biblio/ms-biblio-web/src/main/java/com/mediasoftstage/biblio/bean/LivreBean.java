/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Livre;
import com.mediasoftstage.biblio.service.LivreBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@ViewScoped
public class LivreBean extends GenericBean<Livre, Integer> {

    @EJB
    protected LivreBeanLocal service;
    
    @Override
    public void initEntity() {
        super.initEntity();
    }
    
    @Override
    @PostConstruct
    public void initList() {
        super.initList();
    }
    
    @Override
    public void initAdd() {
        this.entity = new Livre();
    }

    @Override
    public boolean canAdd() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ADD) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canUpdate() {
        return 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_EDIT) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canDelete() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_DELETE) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public GenericServiceBeanLocal<Livre, Integer> getService() {
        return this.service;
    }

    @Override
    public boolean canAccessDetails() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_DETAILS) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

}
