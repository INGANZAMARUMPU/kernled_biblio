/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeRejeteDaoBean;
import com.mediasofthome.biblio.entities.EtapeRejete;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeRejeteServiceBean extends GenericServiceBean<EtapeRejete, Long> implements 
        EtapeRejeteServiceBeanLocal{
    @EJB
    private EtapeRejeteDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeRejete, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeRejete e) {
        return e.getId();
    }
}
