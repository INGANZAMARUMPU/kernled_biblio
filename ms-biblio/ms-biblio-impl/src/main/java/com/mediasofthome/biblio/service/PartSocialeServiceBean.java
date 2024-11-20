/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PartSocialeDaoBean;
import com.mediasofthome.biblio.entities.PartSociale;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
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
public class PartSocialeServiceBean extends GenericServiceBean<PartSociale, Integer> implements PartSocialeServiceBeanLocal {

    @EJB
    private PartSocialeDaoBean dao;

    @Override
    protected GenericDAOBean<PartSociale, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(PartSociale e) {
        return e.getId();
    }

    @Override
    public List<PartSociale> findByAfficherBiblio() {
        return this.dao.getAll(FilterParams.from(FilterParam.from("afficherBiblio", true)));
    }
    @Override
    public List<PartSociale> findByAfficherBiblio(KEntity entite) {
        return this.dao.getAll(FilterParams.from(FilterParam.from("kentity", entite), 
                FilterParam.from("afficherBiblio", true)));
    }

    @Override
    public PartSociale getByEntityAndCodeOrLibelle(KEntity entity, String code, String libelle) {
        return this.dao.getByEntityAndCodeOrLibelle(entity, code, libelle);
    }
    
    @Override
    public PartSociale getAnotherWithout(PartSociale partSociale, KEntity entity, String code, String libelle) {
     return this.dao.getAnotherWithout(partSociale,entity,code, libelle);
    }

    
    
}
