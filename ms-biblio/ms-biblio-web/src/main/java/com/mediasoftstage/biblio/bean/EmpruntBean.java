/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Emprunt;
import com.mediasoftstage.biblio.service.EmpruntBeanLocal;
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
public class EmpruntBean extends GenericCrudBean<Emprunt, Integer> {

    @EJB
    protected EmpruntBeanLocal emprunt_bean_local;
    
    private List<Emprunt> emprunts;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Emprunt();
        this.emprunts = emprunt_bean_local.getAll();
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
    protected GenericServiceBeanLocal<Emprunt, Integer> getService() {
        return this.emprunt_bean_local;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

}
