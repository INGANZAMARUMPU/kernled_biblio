/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PeriodiciteDaoBean;
import com.mediasofthome.biblio.entities.Periodicite;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class PeriodiciteServiceBean extends GenericServiceBean<Periodicite, Long> implements PeriodiciteServiceBeanLocal {

    @EJB
    private PeriodiciteDaoBean dao;

    @Override
    protected GenericDAOBean<Periodicite, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Periodicite e) {
        return e.getId();
    }

    @Override
    public Periodicite getByCode(String code) {
        return dao.getByCode(code);
    }

    @Override
    public Periodicite getAnotherWithout(Periodicite periodicite, String code) {
        return dao.getAnotherWithout(periodicite, code);
    }
}
