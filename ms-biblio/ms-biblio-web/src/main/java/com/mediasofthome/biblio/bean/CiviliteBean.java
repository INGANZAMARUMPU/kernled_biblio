/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.krnl.entities.Civility;
import com.mediasofthome.krnl.service.CivilityServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 *
 * @author mawuli
 */
@Named
@ViewScoped
public class CiviliteBean extends GenericCrudBean<Civility, Integer>{

    @EJB
    protected CivilityServiceBeanLocal civiliteService;

    @PostConstruct
    @Override
    public void init() {
        super.init(); 
        this.entity = new Civility();
    }
    
    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CIVILITE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CIVILITE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CIVILITE_DELETE);
    }

    @Override
    protected GenericServiceBeanLocal<Civility, Integer> getService() {
        return this.civiliteService;
    }
    
}
