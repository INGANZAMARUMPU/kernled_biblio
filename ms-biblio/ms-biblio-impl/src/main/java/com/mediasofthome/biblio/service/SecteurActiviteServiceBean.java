/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SecteurActiviteDaoBean;
import com.mediasofthome.biblio.entities.SecteurActivite;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class SecteurActiviteServiceBean extends GenericServiceBean<SecteurActivite, Integer> implements SecteurActiviteServiceBeanLocal {
    
    @EJB
    private SecteurActiviteDaoBean dao;

    @Override
    protected GenericDAOBean<SecteurActivite, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(SecteurActivite e) {
        return e.getId();
    }
    
}
