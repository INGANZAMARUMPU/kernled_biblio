/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.entities.Categorie;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import com.mediasofthome.biblio.service.CategorieServiceBeanLocal;

/**
 *
 * @author mawuli
 */
@Named
@ViewScoped
public class CategorieBean extends GenericCrudBean<Categorie, Integer> {

    @EJB
    protected CategorieServiceBeanLocal categorieService;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Categorie();
    }

    @Override
    public boolean canAdd() {
        return true;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    protected GenericServiceBeanLocal<Categorie, Integer> getService() {
        return this.categorieService;
    }

}
