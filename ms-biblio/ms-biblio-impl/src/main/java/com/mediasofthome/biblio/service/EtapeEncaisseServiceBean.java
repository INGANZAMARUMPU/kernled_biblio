/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;
import com.mediasofthome.biblio.dao.EtapeEncaisseDaoBean;
import com.mediasofthome.biblio.entities.EtapeEncaisse;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeEncaisseServiceBean extends GenericServiceBean<EtapeEncaisse, Long> implements 
        EtapeEncaisseServiceBeanLocal{
    @EJB
    private EtapeEncaisseDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeEncaisse, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeEncaisse e) {
        return e.getId();
    }
}