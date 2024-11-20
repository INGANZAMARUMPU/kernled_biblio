/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargneDatDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargneDat;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitEpargneDatServiceBean extends GenericServiceBean<ProduitEpargneDat, Long> implements ProduitEpargneDatServiceBeanLocal {
    
    @EJB
    private ProduitEpargneDatDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargneDat, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargneDat e) {
        return e.getId();
    }
    
}
