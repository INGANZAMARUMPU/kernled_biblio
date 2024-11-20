/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitDaoBean;
import com.mediasofthome.biblio.entities.Produit;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitServiceBean extends GenericServiceBean<Produit, Long> implements ProduitServiceBeanLocal {

    @EJB
    private ProduitDaoBean dao;
    
    @Override
    protected GenericDAOBean<Produit, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Produit e) {
        return e.getId();
    }
    
}
