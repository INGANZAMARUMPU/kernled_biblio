/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EpargneDaoBean;
import com.mediasofthome.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.entities.Epargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author mawuli
 */
@Stateless
public class EpargneServiceBean extends GenericServiceBean<Epargne, Long> implements EpargneServiceBeanLocal {

    @EJB
    private EpargneDaoBean dao;

    @Override
    protected GenericDAOBean<Epargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Epargne e) {
        return e.getId();
    }
    @Override
    public Epargne creerDepot(CompteEpargne compte, TypeOperation operation, 
            String numeroPiece, String libelle, LocalDate dateOperation, 
            BigDecimal montant, BigDecimal soldeNouveau, Acteur mandataire) {
        Epargne epargne = new Epargne();
        epargne.setAgence(compte.getAgence());
        epargne.setCompteEpargne(compte);
        epargne.setTypeOperation(operation);
        epargne.setNumeroPiece(numeroPiece);
        epargne.setLibelle(libelle);
        epargne.setDateOperation(dateOperation);
        epargne.setMontantCredit(montant);
        epargne.setSoldeNouveau(soldeNouveau);
        return epargne;
    }
    
    @Override
    public Epargne creerRetrait(CompteEpargne compte, BigDecimal montant) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
