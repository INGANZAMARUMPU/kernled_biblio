/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CategoriePartSocialeDaoBean;
import com.mediasofthome.biblio.entities.CategoriePartSociale;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class CategoriePartSocialeServiceBean extends GenericServiceBean<CategoriePartSociale, Integer> implements CategoriePartSocialeServiceBeanLocal {

    @EJB
    private CategoriePartSocialeDaoBean dao;

    @Override
    protected GenericDAOBean<CategoriePartSociale, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(CategoriePartSociale e) {
        return e.getId();
    }

}
