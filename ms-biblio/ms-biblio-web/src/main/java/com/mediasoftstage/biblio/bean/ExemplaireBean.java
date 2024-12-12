/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import java.util.Arrays;
import java.util.List;
import org.omnifaces.util.Messages;

import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Exemplaire;
import com.mediasoftstage.biblio.entities.Livre;
import com.mediasoftstage.biblio.entities.STATES;
import com.mediasoftstage.biblio.service.ExemplaireBeanLocal;
import com.mediasoftstage.biblio.service.LivreBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
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
public class ExemplaireBean extends GenericBean<Exemplaire, Integer> {

    @EJB
    protected ExemplaireBeanLocal service;

    @EJB
    protected LivreBeanLocal livre_bean;

    protected Integer livre_id;

    
    private List<Livre> livres;
    
    private List<STATES> states;
    
    public Integer getLivre_id() {
        return livre_id;
    }

    public void setLivre_id(Integer livre_id) {
        this.livre_id = livre_id;
    }
    
    public List<STATES> getStates() {
        return states;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    @PostConstruct
    public void initList() {
        super.initList();
    }
    
    @Override
    public void initAdd() {
        this.entity = new Exemplaire();
        if (livre_id != null) {
            this.entity = new Exemplaire();
            this.entity.setLivre(livre_bean.getOne(livre_id));
        }
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
    public GenericServiceBeanLocal<Exemplaire, Integer> getService() {
        return this.service;
    }

    @Override
    public boolean canAccessDetails() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_DETAILS) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_LIVRE_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    public void initUpdate() {
        this.entity = this.getService().getOne(this.entityId);
    }
    
    public boolean isAdding() {
        return this.entityId == null;
    }

    public void initEntity() {
        this.setLivres(livre_bean.getAll());
        this.states = Arrays.asList(STATES.values());
        if (this.isAdding()) {
            this.initAdd();
        } else {
            this.initUpdate();
        }
    }

    public void initDetails() {
        if (this.entityId != null) {
            this.entity = this.getService().getOne(this.entityId);
        }
    }
    
    public String add() {
        try {
            this.getService().addOne(this.entity);
            this.initList();
            Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
            return "list?faces-redirect=true";
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de l'ajout.");
            return null;
        }
    }
    
    public String update() {
        try {
            this.entity = this.getService().updateOne(this.entity);
            this.updateEntity();
            Messages.addFlashGlobalInfo("Mise à jour effectuée avec succès.");
            return "list?faces-redirect=true";
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de la mise à jour.");
            return null;
        }
    }

    public String delete() {
        try {
            this.getService().deleteOne(this.entity);
            Messages.addFlashGlobalInfo("Suppression effectuée avec succès.");
        } catch (BusinessException ex) {
            Messages.addFlashGlobalError(ex.getMessage());
        } catch (Exception ex) {
            Messages.addFlashGlobalError("Une erreur est survenue lors de la suppression. " + ex.getMessage());
        }
        return "list?faces-redirect=true";
    }

    public String disponibiliser(Exemplaire exemplaire) {
        try {
            exemplaire.setSituation(STATES.DISPONIBLE.value);
            this.getService().updateOne(exemplaire);
            Messages.addFlashGlobalInfo(exemplaire.toString() + " est desormais disponible!");
        } catch (BusinessException ex) {
            Messages.addFlashGlobalError(ex.getMessage());
        } catch (Exception ex) {
            Messages.addFlashGlobalError("Une erreur est survenue lors de la disponibilisation. " + ex.getMessage());
        }
        return "list?faces-redirect=true";
    }
    
    public String reinitialiser(){
        Exemplaire empty = new Exemplaire();
        empty.setId(this.entityId);
        this.entity = empty;
        return "edit";

    }

    public String cancel() {
        return "list?faces-redirect=true";
    }

    public String gotoNew() {
        return "edit?faces-redirect=true";
    }

}
