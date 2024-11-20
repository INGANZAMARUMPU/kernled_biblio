/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.google.common.base.Strings;
import com.mediasofthome.biblio.dao.SousTypeMembrePartSocialeDaoBean;
import com.mediasofthome.biblio.entities.SousTypeMembre;
import com.mediasofthome.biblio.entities.SousTypeMembrePartSociale;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.omnifaces.util.Messages;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Stateless
public class SousTypeMembrePartSocialeServiceBean extends GenericServiceBean<SousTypeMembrePartSociale, Integer>
        implements SousTypeMembrePartSocialeServiceBeanLocal {

    @EJB
    private SousTypeMembrePartSocialeDaoBean dao;

    @Override
    protected GenericDAOBean<SousTypeMembrePartSociale, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(SousTypeMembrePartSociale e) {
        return e.getId();
    }

    @Override
    public List<SousTypeMembrePartSociale> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public List<SousTypeMembrePartSociale> selectionnerTout() {
        return this.dao.getAllPartSociale();
    }

    @Override
    public List<SousTypeMembrePartSociale> getAllBySousTypeMembre(SousTypeMembre stm) {
        return this.dao.getAllBySousTypeMembre(stm);
    }

    @Override
    public boolean canAddSousTypeMembrePartSocial(SousTypeMembrePartSociale stmps) {
        if (Objects.isNull(stmps)
                || Objects.isNull(stmps.getPartSociale())
                || Strings.isNullOrEmpty(stmps.getCompte())
                || stmps.getDroitEntree() == null
                || stmps.getMaximum() == null
                || stmps.getMinimum() == null) {
            Messages.addGlobalError("Erreur! Veuillez renseigner les champs vides de la part sociale à ajouter.");
            return false;
        } else if (stmps.getMinimum() >= stmps.getMaximum()) {
            Messages.addGlobalError("Erreur! Le maximum doit être supérieur au minimum.");
            return false;
        } else if (stmps.getDroitEntree() < 0) {
            Messages.addGlobalError("Veuillez saisir une somme supérieure ou égale à zéro.");
            return false;
        }
        return true;
    }

}
