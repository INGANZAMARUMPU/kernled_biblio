/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MembreGroupementDaoBean;
import com.mediasofthome.biblio.entities.MembreGroupement;
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
public class MembreGroupementServiceBean extends GenericServiceBean<MembreGroupement, Long> implements MembreGroupementServiceBeanLocal {

    @EJB
    private MembreGroupementDaoBean dao;

    @Override
    protected GenericDAOBean<MembreGroupement, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(MembreGroupement e) {
        return e.getId();
    }

    @Override
    public MembreGroupement getOne(Long id, String... attributs) {
        return this.dao.getOne(id, attributs);
    }

    @Override
    public List<MembreGroupement> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public MembreGroupement getById(Long id) {
        return this.dao.getById(id);
    }

    @Override
    public List<MembreGroupement> selectionnerTout() {
        return this.dao.SelectionnerTout();
    }
    
    @Override
    public Long membreGroupementVise(){
        return this.dao.membreGroupementVise();
    }

    @Override
    public MembreGroupement getByEnityAndNumeroMembre(KEntity entity, String numeroMembre) {
        return this.dao.getByEnityAndNumeroMembre(entity, numeroMembre);
    }
    
}
