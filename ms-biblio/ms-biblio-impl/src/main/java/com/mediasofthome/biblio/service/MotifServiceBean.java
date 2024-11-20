/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MotifDaoBean;
import com.mediasofthome.biblio.entities.Motif;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author sama
 */
@Stateless
public class MotifServiceBean extends GenericServiceBean<Motif, Long> implements MotifServiceBeanLocal {
    
    @EJB
    private MotifDaoBean dao;

    @Override
    protected GenericDAOBean<Motif, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Motif e) {
        return e.getId();
    }
    
}
