/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.biblio.entities.CategoriePartSociale;
import com.mediasofthome.biblio.service.CategoriePartSocialeServiceBeanLocal;
import com.mediasofthome.krnl.constants.CoreMessageConstants;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.KEntityServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Objects;
import java.util.logging.Level;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author sama
 */
@ViewScoped
@Named
public class CategoriePartSocialeBean extends GenericCrudBean<CategoriePartSociale, Integer> {

    @EJB
    private CategoriePartSocialeServiceBeanLocal categoriePartSocialService;
    @EJB
    private KEntityServiceBeanLocal kEntityService;
    @Inject
    private UserSessionBean sessionBean;

    private KEntity entityCategoriePartSocial;

    @PostConstruct
    @Override
    public void init() {
        this.entity = new CategoriePartSociale();
        this.initAutoEntite();
        if (Objects.nonNull(this.entityCategoriePartSocial)) {
            this.list = categoriePartSocialService.getAll(FilterParams.from(FilterParam.from("entity", this.entityCategoriePartSocial)));
            this.adding = true;
        } else {
            super.init();
        }
    }

    private void initAutoEntite() {
    }

    @Override
    public void beforeAdd() {
        if (Objects.nonNull(this.sessionBean.getEntity())) {
            this.entityCategoriePartSocial = this.sessionBean.getEntity();
        }
        this.entity.setEntity(this.getEntiteRoot());
    }

    @Override
    public void add() {
        try {
            this.beforeAdd();
            this.getService().addOne(this.entity);
            this.reset();
            Messages.addGlobalInfo(CoreMessageConstants.ADD_SUCCEEDED);
        } catch (BusinessException e) {
            Messages.addGlobalError(e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (RuntimeException e) {
            Messages.addFlashGlobalError("Cette catégorie existe déjà pour l'entité " + this.entity.getEntity().getLabel() + ", veuillez saisir un autre libellé");
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (Exception e) {
            Messages.addGlobalError(CoreMessageConstants.UNKNOWN_ERROR);
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void rowSelected(SelectEvent event) {
        super.rowSelected(event);
        this.entityCategoriePartSocial = this.entity.getEntity();
    }

    @Override
    public void beforeUpdate() {
        this.entity.setEntity(this.getEntiteRoot());
    }

    @Override
    public void update() {
        try {
            this.beforeUpdate();
            this.getService().updateOne(this.entity);
            this.reset();
            Messages.addGlobalInfo(CoreMessageConstants.UPDATE_SUCCEEDED);
        } catch (BusinessException e) {
            Messages.addGlobalError(e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (RuntimeException e) {
            Messages.addFlashGlobalError("Cette catégorie existe déjà pour l'entité " + this.entity.getEntity().getLabel());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (Exception e) {
            Messages.addGlobalError(CoreMessageConstants.UNKNOWN_ERROR);
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private KEntity getEntiteRoot() {
        return this.kEntityService.getEntiteRoot(this.getEntityCategoriePartSocial());
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_PART_SOCIALE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_PART_SOCIALE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_PART_SOCIALE_DELETE);
    }

    @Override
    protected GenericServiceBeanLocal<CategoriePartSociale, Integer> getService() {
        return this.categoriePartSocialService;
    }

    public KEntity getEntityCategoriePartSocial() {
        return entityCategoriePartSocial;
    }

    public void setEntityCategoriePartSocial(KEntity entityCategoriePartSocial) {
        this.entityCategoriePartSocial = entityCategoriePartSocial;
    }
}
