/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProduitEpargneBloqueDaoBean;
import com.mediasofthome.biblio.entities.ProduitEpargneBloque;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProduitEpargneBloqueServiceBean extends GenericServiceBean<ProduitEpargneBloque, Long> implements ProduitEpargneBloqueServiceBeanLocal {
    
    @EJB
    private ProduitEpargneBloqueDaoBean dao;

    @Override
    protected GenericDAOBean<ProduitEpargneBloque, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProduitEpargneBloque e) {
        return e.getId();
    }
    
}
