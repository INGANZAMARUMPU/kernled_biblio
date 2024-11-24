package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.DomaineActivite;
import com.mediasofthome.biblio.service.DomaineActiviteServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
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
public class DomaineActiviteBean extends GenericCrudBean<DomaineActivite, Integer> {

    @EJB
    private DomaineActiviteServiceBeanLocal categorieMbService;

    @Override
    @PostConstruct
    public void init() {
        super.init();
        this.entity = new DomaineActivite();
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_DOMAINE_ACTIVITE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_DOMAINE_ACTIVITE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_DOMAINE_ACTIVITE_DELETE);
    }

    @Override
    protected GenericServiceBeanLocal<DomaineActivite, Integer> getService() {
        return this.categorieMbService;
    }

}
