/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CommercialDaoBean;
import com.mediasofthome.biblio.entities.Commercial;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author admin
 */
@Stateless
public class CommercialServiceBean extends GenericServiceBean<Commercial, Long> implements CommercialServiceBeanLocal{
    @EJB
    private CommercialDaoBean dao;

    @Override
    protected GenericDAOBean<Commercial,Long> getDAO(){
      return this.dao;
    }
    
    @Override
    public Long getId(Commercial c) {
        return c.getId();
    }

    @Override
    public List<Commercial> findByEntite(Entite entite) {
        return this.dao.getAll(FilterParams.from(
                   "entite", entite));
    }
    
}
