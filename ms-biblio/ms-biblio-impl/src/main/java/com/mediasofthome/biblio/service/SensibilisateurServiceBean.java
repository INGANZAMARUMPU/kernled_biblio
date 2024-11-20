/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SensibilisateurDaoBean;
import com.mediasofthome.biblio.entities.Sensibilisateur;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author admin
 */
@Stateless
public class SensibilisateurServiceBean extends GenericServiceBean<Sensibilisateur, Long> implements SensibilisateurServiceBeanLocal{
     @EJB
    private SensibilisateurDaoBean dao;

    @Override
    protected GenericDAOBean<Sensibilisateur,Long> getDAO(){
      return this.dao;
    }
    
    @Override
    public Long getId(Sensibilisateur s) {
        return s.getId();
    }

    @Override
    public List<Sensibilisateur> findByEntite(Entite entite) {
        return this.dao.getAll(FilterParams.from(
                   "entite", entite));
    }
    
}
