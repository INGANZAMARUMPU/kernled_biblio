/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeInactifDaoBean;
import com.mediasofthome.biblio.entities.EtapeInactif;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author admin
 */
@Stateless
public class EtapeInactifServiceBean extends GenericServiceBean<EtapeInactif, Long> implements 
        EtapeInactifServiceBeanLocal{
    @EJB
    private EtapeInactifDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeInactif, Long> getDAO() {
        return this.dao;
    }
    
    @Override
    public Long getId(EtapeInactif e) {
        return e.getId();
    }
}
