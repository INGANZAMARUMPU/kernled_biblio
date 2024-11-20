/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.HistorisationDaoBean;
import com.mediasofthome.biblio.entities.Historisation;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.service.TypeOperationServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.time.LocalDateTime;

/**
 *
 * @author TOKPE Kossi Voltaire
 * Cette classe contient les appels des requetes définies pour historisation des modifications des informations des adhésions des membres 
 */
@Stateless
public class HistorisationServiceBean extends GenericServiceBean<Historisation, Long> implements HistorisationServiceBeanLocal {

    @EJB
    private HistorisationDaoBean dao;
    @EJB
    private TypeOperationServiceBeanLocal typeOperationService;
    
    @Override
    protected GenericDAOBean<Historisation, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Historisation e) {
        return e.getId();
    }

    @Override
    public void ajouterHistorique(String label, String status, LocalDateTime dateOperation, KEntity entity, User user, String code) {
        Historisation historique = new Historisation();
        historique.setStatus(status); 
        historique.setLabel(label);
        historique.setDateOperation(dateOperation); 
        historique.setTypeOperation(this.typeOperationService.getOne(code)); 
        historique.setUser(user);
        historique.setEntity(entity);
        this.dao.addOne(historique);
    }
    
}