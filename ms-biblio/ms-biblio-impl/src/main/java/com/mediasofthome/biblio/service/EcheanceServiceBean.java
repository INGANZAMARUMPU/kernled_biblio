/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EcheanceDaoBean;
import com.mediasofthome.biblio.entities.Echeance;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class EcheanceServiceBean extends GenericServiceBean<Echeance, Long> implements EcheanceServiceBeanLocal {
    
    @EJB
    private EcheanceDaoBean dao;

    @Override
    protected GenericDAOBean<Echeance, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Echeance e) {
        return e.getId();
    }
    
}
