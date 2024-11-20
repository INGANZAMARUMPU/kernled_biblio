/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CategorieDaoBean;
import com.mediasofthome.biblio.entities.Categorie;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class CategorieServiceBean extends GenericServiceBean<Categorie, Integer> implements CategorieServiceBeanLocal {
    
    @EJB
    private CategorieDaoBean dao;

    @Override
    protected GenericDAOBean<Categorie, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Categorie e) {
        return e.getId();
    }
    
}
