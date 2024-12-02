/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Emprunteur;
import com.mediasoftstage.biblio.service.EmprunteurBeanLocal;
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
public class EmprunteurBean extends GenericBean<Emprunteur, Integer> {

    @EJB
    protected EmprunteurBeanLocal service;
    
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
        this.entity = new Emprunteur();
    }

    @Override
    public boolean canAdd() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_ADD) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canUpdate() {
        return 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_EDIT) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canDelete() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_DELETE) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public GenericServiceBeanLocal<Emprunteur, Integer> getService() {
        return this.service;
    }

    @Override
    public boolean canAccessDetails() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_DETAILS) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_EMPRUNTEUR_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

}
