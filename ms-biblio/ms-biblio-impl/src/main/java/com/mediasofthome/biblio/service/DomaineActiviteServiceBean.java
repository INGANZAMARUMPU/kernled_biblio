/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.DomaineActiviteDaoBean;
import com.mediasofthome.biblio.entities.DomaineActivite;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class DomaineActiviteServiceBean extends GenericServiceBean<DomaineActivite, Integer> implements DomaineActiviteServiceBeanLocal {

    @EJB
    private DomaineActiviteDaoBean dao;

    @Override
    protected GenericDAOBean<DomaineActivite, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(DomaineActivite e) {
        return e.getId();
    }

}
