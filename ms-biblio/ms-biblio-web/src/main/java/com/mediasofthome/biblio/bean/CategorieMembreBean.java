/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.CategorieMembre;
import com.mediasofthome.biblio.service.CategorieMembreServiceBeanLocal;
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
public class CategorieMembreBean extends GenericCrudBean<CategorieMembre, Integer> {

    private @EJB
    CategorieMembreServiceBeanLocal categorieMbService;

    @Override
    @PostConstruct
    public void init() {
        super.init();
        this.entity = new CategorieMembre();
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_MEMBRE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_MEMBRE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_MEMBRE_DELETE);
    }

    @Override
    protected GenericServiceBeanLocal<CategorieMembre, Integer> getService() {
        return this.categorieMbService;
    }

}
