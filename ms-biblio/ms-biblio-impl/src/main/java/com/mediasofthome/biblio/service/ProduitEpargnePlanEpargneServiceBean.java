/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargnePlanEpargneDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargnePlanEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitEpargnePlanEpargneServiceBean extends GenericServiceBean<ProduitEpargnePlanEpargne, Long> implements ProduitEpargnePlanEpargneServiceBeanLocal {
    
    @EJB
    private ProduitEpargnePlanEpargneDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargnePlanEpargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargnePlanEpargne e) {
        return e.getId();
    }
    
}
