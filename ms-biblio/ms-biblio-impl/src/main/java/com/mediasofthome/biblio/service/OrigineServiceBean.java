/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.OrigineDaoBean;
import com.mediasofthome.biblio.entities.Origine;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class OrigineServiceBean extends GenericServiceBean<Origine, Long> implements OrigineServiceBeanLocal {

    @EJB
    private OrigineDaoBean dao;

    @Override
    protected GenericDAOBean<Origine, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Origine e) {
        return e.getId();
    }

}
