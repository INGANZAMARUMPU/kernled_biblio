/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargneDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitEpargneServiceBean extends GenericServiceBean<ProduitEpargne, Long> implements ProduitEpargneServiceBeanLocal {
    
    @EJB
    private ProduitEpargneDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargne e) {
        return e.getId();
    }
    
}
