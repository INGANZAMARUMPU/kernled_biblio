/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SexeDaoBean;
import com.mediasofthome.biblio.entities.Sexe;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class SexeServiceBean extends GenericServiceBean<Sexe, Integer> implements SexeServiceBeanLocal {

    @EJB
    private SexeDaoBean dao;

    @Override
    protected GenericDAOBean<Sexe, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Sexe e) {
        return e.getId();
    }
}
