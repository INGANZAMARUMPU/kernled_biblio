/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MembreEntrepriseDaoBean;
import com.mediasofthome.biblio.entities.MembreEntreprise;
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
public class MembreEntrepriseServiceBean extends GenericServiceBean<MembreEntreprise, Long> implements MembreEntrepriseServiceBeanLocal {

    @EJB
    private MembreEntrepriseDaoBean dao;

    @Override
    protected GenericDAOBean<MembreEntreprise, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(MembreEntreprise e) {
        return e.getId();
    }

    @Override
    public MembreEntreprise getOne(Long id, String... attributs) {
        return this.dao.getOne(id, attributs);
    }

    @Override
    public List<MembreEntreprise> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public MembreEntreprise getById(Long id) {
        return this.dao.getById(id);
    }
    
    @Override
    public List<MembreEntreprise> selectionnerTout() {
        return this.dao.SelectionnerTout();
    }
    
    @Override
    public Long membreEntrepriseVise(){
        return this.dao.membreEntrepriseVise();
    }

    @Override
    public MembreEntreprise getByEnityAndNumeroMembre(KEntity entity, String numeroMembre) {
        return this.dao.getByEnityAndNumeroMembre(entity, numeroMembre);
    }
    
}
