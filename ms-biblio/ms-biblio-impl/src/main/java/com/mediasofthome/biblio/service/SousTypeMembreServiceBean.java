/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SousTypeMembreDaoBean;
import com.mediasofthome.biblio.entities.SousTypeMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class SousTypeMembreServiceBean extends GenericServiceBean<SousTypeMembre, Integer> 
        implements SousTypeMembreServiceBeanLocal {

    @EJB
    private SousTypeMembreDaoBean dao;

    @Override
    protected GenericDAOBean<SousTypeMembre, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(SousTypeMembre e) {
        return e.getId();
    }

    @Override
    public List<SousTypeMembre> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public boolean sousTypeMembreExist(SousTypeMembre stm) {
        List<SousTypeMembre> stms = this.dao.getAll(FilterParams.from(
                FilterParam.from("entity", stm.getEntity()),
                FilterParam.from("typeMembre", stm.getTypeMembre()),
                FilterParam.from("libelle", stm.getLibelle())));
        return !stms.isEmpty();
    }

}
