/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeEnregistreDaoBean;
import com.mediasofthome.biblio.entities.EtapeEnregistre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeEnregistreServiceBean extends GenericServiceBean<EtapeEnregistre, Long> implements EtapeEnregistreServiceBeanLocal{
    @EJB
    private EtapeEnregistreDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeEnregistre, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeEnregistre e) {
        return e.getId();
    }
}