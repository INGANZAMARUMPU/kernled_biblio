/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Emprunteur;
import com.mediasoftstage.biblio.service.EmprunteurBeanLocal;
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
public class EmprunteurBean extends GenericCrudBean<Emprunteur, Integer> {

    @EJB
    protected EmprunteurBeanLocal emprunt_bean_local;
    
    private List<Emprunteur> emprunts;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Emprunteur();
        this.emprunts = emprunt_bean_local.getAll();
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
    protected GenericServiceBeanLocal<Emprunteur, Integer> getService() {
        return this.emprunt_bean_local;
    }

    public List<Emprunteur> getEmprunteurs() {
        return emprunts;
    }

    public void setEmprunteurs(List<Emprunteur> emprunts) {
        this.emprunts = emprunts;
    }

}
