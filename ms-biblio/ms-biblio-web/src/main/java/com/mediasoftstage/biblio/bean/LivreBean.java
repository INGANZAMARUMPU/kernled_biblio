/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Livre;
import com.mediasoftstage.biblio.service.LivreBeanLocal;
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
public class LivreBean extends GenericCrudBean<Livre, Integer> {

    @EJB
    protected LivreBeanLocal livre_bean_local;
    
    private List<Livre> livres;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Livre();
        this.livres = livre_bean_local.getAll();
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
    protected GenericServiceBeanLocal<Livre, Integer> getService() {
        return this.livre_bean_local;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

}
