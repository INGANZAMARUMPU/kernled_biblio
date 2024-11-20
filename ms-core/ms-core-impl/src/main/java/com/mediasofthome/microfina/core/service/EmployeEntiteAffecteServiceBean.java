/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.dao.EmployeEntiteAffecteDaoBean;
import com.mediasofthome.biblio.core.entities.Employe;
import com.mediasofthome.biblio.core.entities.EmployeEntiteAffecte;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class EmployeEntiteAffecteServiceBean extends GenericServiceBean<EmployeEntiteAffecte, Long> implements EmployeEntiteAffecteServiceBeanLocal {

    @EJB
    private EmployeEntiteAffecteDaoBean dao;

    @Override
    protected GenericDAOBean<EmployeEntiteAffecte, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EmployeEntiteAffecte e) {
        return e.getId();
    }

    @Override
    public List<KEntity> chargerEntiteParEmploye(Employe employe) {
        return this.dao.chargerEntitesParEmploye(employe);
    }

    @Override
    public List<KEntity> chargerEntites(FilterParams filterParams) {
        return this.dao.chargerEntites(filterParams);
    }

}
