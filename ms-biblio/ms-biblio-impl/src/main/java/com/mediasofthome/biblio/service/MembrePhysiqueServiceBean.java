/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MembrePhysiqueDaoBean;
import com.mediasofthome.biblio.entities.MembrePhysique;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class MembrePhysiqueServiceBean extends GenericServiceBean<MembrePhysique, Long> implements MembrePhysiqueServiceBeanLocal {

    @EJB
    private MembrePhysiqueDaoBean dao;

    @Override
    protected GenericDAOBean<MembrePhysique, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(MembrePhysique e) {
        return e.getId();
    }

    @Override
    public MembrePhysique getOne(Long id, String... attributs) {
        return this.dao.getOne(id, attributs);
    }

    @Override
    public List<MembrePhysique> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public MembrePhysique getById(Long id) {
        return this.dao.getById(id);
    }

    @Override
    public List<MembrePhysique> selectionnerTout() {
        return this.dao.SelectionnerTout();
    }
    
    @Override
    public Long membrePhysiqueVise(){
        return this.dao.membrePhysiqueVise();
    }

    @Override
    public MembrePhysique getByEnityAndNumeroMembre(KEntity entity, String numeroMembre) {
       return this.dao.getByEnityAndNumeroMembre(entity, numeroMembre);
    }
    
}
