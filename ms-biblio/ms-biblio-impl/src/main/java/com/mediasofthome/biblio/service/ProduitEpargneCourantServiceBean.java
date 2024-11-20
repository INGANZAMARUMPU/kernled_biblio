/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargneCourantDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargneCourant;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitEpargneCourantServiceBean extends GenericServiceBean<ProduitEpargneCourant, Long> implements ProduitEpargneCourantServiceBeanLocal {
    
    @EJB
    private ProduitEpargneCourantDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargneCourant, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargneCourant e) {
        return e.getId();
    }
    
}
