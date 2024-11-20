/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeActifDaoBean;
import com.mediasofthome.biblio.entities.EtapeActif;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeActifServiceBean extends GenericServiceBean<EtapeActif, Long> implements 
        EtapeActifServiceBeanLocal{
    @EJB
    private EtapeActifDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeActif, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeActif e) {
        return e.getId();
    }
}