/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.beans;

import com.mediasofthome.krnl.entities.EntityType;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.biblio.core.entities.EmployeEntiteAffecte;
import com.mediasofthome.biblio.core.service.EmployeEntiteAffecteServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.util.List;

/**
 *
 * @author believes
 */
@Named
@ViewScoped
public class EmployeEntiteAffecteBean extends GenericBean<EmployeEntiteAffecte, Long> {

    @EJB
    private EmployeEntiteAffecteServiceBeanLocal service;

    private EntityType entityType;
    private List<KEntity> entites;

    @Override
    public void initAdd() {
        this.entity = new EmployeEntiteAffecte();
        this.entityType = new EntityType();
    }

    @Override
    public void initEntity() {
        super.initEntity();
    }

    public List<KEntity> getEntites() {
        return entites;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public GenericServiceBeanLocal<EmployeEntiteAffecte, Long> getService() {
        return this.service;
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
    public boolean canAccessDetails() {
        return false;
    }

}
