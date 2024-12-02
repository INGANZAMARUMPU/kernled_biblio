/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Emprunt;
import com.mediasoftstage.biblio.service.EmpruntBeanLocal;
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
public class EmpruntBean extends GenericBean<Emprunt, Integer> {

    @EJB
    protected EmpruntBeanLocal service;
    
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
        this.entity = new Emprunt();
    }

    @Override
    public boolean canAdd() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_ADD) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canUpdate() {
        return 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_EDIT) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canDelete() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_DELETE) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public GenericServiceBeanLocal<Emprunt, Integer> getService() {
        return this.service;
    }

    @Override
    public boolean canAccessDetails() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_DETAILS) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNT_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

}
