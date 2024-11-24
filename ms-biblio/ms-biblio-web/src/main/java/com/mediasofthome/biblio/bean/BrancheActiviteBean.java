/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.BrancheActivite;
import com.mediasofthome.biblio.entities.SecteurActivite;
import com.mediasoftstage.biblio.service.BrancheActiviteServiceBeanLocal;
import com.mediasofthome.biblio.service.SecteurActiviteServiceBeanLocal;
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
public class BrancheActiviteBean extends GenericCrudBean<BrancheActivite, Integer> {

    @EJB
    protected BrancheActiviteServiceBeanLocal brancheActiviteService;
    @EJB
    protected SecteurActiviteServiceBeanLocal secteurActvitieService;
    
    private List<SecteurActivite> secteurs;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new BrancheActivite();
        this.secteurs = secteurActvitieService.getAll();
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_BRANCHE_ACTIVITE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_BRANCHE_ACTIVITE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_BRANCHE_ACTIVITE_DELETE);
    }

    @Override
    protected GenericServiceBeanLocal<BrancheActivite, Integer> getService() {
        return this.brancheActiviteService;
    }

    public List<SecteurActivite> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<SecteurActivite> secteurs) {
        this.secteurs = secteurs;
    }

}
