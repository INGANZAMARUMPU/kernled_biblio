/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;
import com.mediasofthome.biblio.dao.EtapeEncaissementDaoBean;
import com.mediasofthome.biblio.entities.EtapeEncaissement;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author admin
 */
@Stateless
public class EtapeEncaissementServiceBean extends GenericServiceBean<EtapeEncaissement, Long> implements 
        EtapeEncaissementServiceBeanLocal{
    @EJB
    private EtapeEncaissementDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeEncaissement, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeEncaissement e) {
        return e.getId();
    }
}