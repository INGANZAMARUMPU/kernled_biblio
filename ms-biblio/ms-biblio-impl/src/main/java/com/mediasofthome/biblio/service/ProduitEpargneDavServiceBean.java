/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargneDavDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargneDav;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class ProduitEpargneDavServiceBean extends GenericServiceBean<ProduitEpargneDav, Long> implements ProduitEpargneDavServiceLocalBean{
    
    @EJB
    private ProduitEpargneDavDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargneDav, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargneDav e) {
        return e.getId();
    }
    
}
