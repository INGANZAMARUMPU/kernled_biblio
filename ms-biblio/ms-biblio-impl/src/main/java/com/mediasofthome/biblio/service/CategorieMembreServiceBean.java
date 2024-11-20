/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CategorieMembreDaoBean;
import com.mediasofthome.biblio.entities.CategorieMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class CategorieMembreServiceBean extends GenericServiceBean<CategorieMembre, Integer> implements CategorieMembreServiceBeanLocal {

    private @EJB CategorieMembreDaoBean dao;
    
    @Override
    protected GenericDAOBean<CategorieMembre, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(CategorieMembre e) {
        return e.getId();
    }
    
}
