/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.GrilleDaoBean;
import com.mediasofthome.biblio.entities.Grille;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class GrilleServiceBean extends GenericServiceBean<Grille, Long> implements GrilleServiceBeanLocal {

    @EJB
    private GrilleDaoBean dao;

    @Override
    protected GenericDAOBean<Grille, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Grille e) {
        return e.getId();
    }

}
