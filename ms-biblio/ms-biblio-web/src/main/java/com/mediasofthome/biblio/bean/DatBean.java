/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.Dat;
import com.mediasofthome.biblio.service.DatServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 *
 * @author TOKPE Kossi Voltaire
 */
@Named
@ViewScoped
public class DatBean extends GenericBean<Dat, Long> {

    @EJB
    protected DatServiceBeanLocal service;

    @Override
    public GenericServiceBeanLocal<Dat, Long> getService() {
        return this.service;
    }

    @Override
    public void initEntity() {
        super.initEntity();
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_COMPTE_DAT_DELETE);
    }

    @Override
    public boolean canAccessDetails() {
        return false;
    }

    @Override
    public void initAdd() {

    }

}
